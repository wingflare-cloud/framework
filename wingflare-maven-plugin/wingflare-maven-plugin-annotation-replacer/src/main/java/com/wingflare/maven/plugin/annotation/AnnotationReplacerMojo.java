package com.wingflare.maven.plugin.annotation;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Mojo(name = "replace-annotations", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class AnnotationReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(required = true)
    private List<AnnotationReplacement> replacements;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;

    private JavaParser javaParser;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Starting annotation replacement...");
        getLog().info("Found " + replacements.size() + " replacement rules");

        // 初始化JavaParser实例
        javaParser = new JavaParser();

        if (!sourceDirectory.exists()) {
            getLog().warn("Source directory does not exist: " + sourceDirectory.getAbsolutePath());
            return;
        }

        processDirectory(sourceDirectory);

        getLog().info("Annotation replacement completed successfully");
    }

    private void processDirectory(File directory) throws MojoExecutionException {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                processDirectory(file);
            } else if (file.getName().endsWith(".java")) {
                processJavaFile(file);
            }
        }
    }

    private void processJavaFile(File file) throws MojoExecutionException {
        try (FileInputStream in = new FileInputStream(file)) {
            ParseResult<CompilationUnit> result = javaParser.parse(in);

            if (result.isSuccessful() && result.getResult().isPresent()) {
                CompilationUnit cu = result.getResult().get();
                LexicalPreservingPrinter.setup(cu);

                boolean modified = replaceAnnotations(cu);

                if (modified) {
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(LexicalPreservingPrinter.print(cu));
                        getLog().info("Modified file: " + file.getAbsolutePath());
                    }
                }
            } else {
                getLog().warn("Could not parse file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Error processing file: " + file.getAbsolutePath(), e);
        }
    }


    private boolean replaceAnnotations(Node node) {
        boolean modified = false;

        // 处理当前节点如果它有注解
        if (node instanceof NodeWithAnnotations<?>) {
            NodeWithAnnotations<?> nodeWithAnnotations = (NodeWithAnnotations<?>) node;
            NodeList<AnnotationExpr> annotations = nodeWithAnnotations.getAnnotations();

            // 创建新的注解列表，替换匹配的注解
            NodeList<AnnotationExpr> newAnnotations = new NodeList<>();
            for (AnnotationExpr annotation : annotations) {
                String annotationName = getAnnotationName(annotation);
                AnnotationReplacement replacement = findReplacement(annotationName);

                if (replacement != null) {
                    // 使用实例调用parseAnnotation方法，而不是静态调用
                    ParseResult<AnnotationExpr> annotationResult = javaParser.parseAnnotation(replacement.getNewAnnotation());
                    if (annotationResult.isSuccessful() && annotationResult.getResult().isPresent()) {
                        AnnotationExpr newAnnotation = annotationResult.getResult().get();
                        newAnnotations.add(newAnnotation);
                        modified = true;
                        getLog().debug("Replaced annotation: " + annotationName + " with " + replacement.getNewAnnotation());
                    } else {
                        getLog().warn("Could not parse new annotation: " + replacement.getNewAnnotation());
                        newAnnotations.add(annotation); // 解析失败时保留原注解
                    }
                } else {
                    newAnnotations.add(annotation);
                }
            }

            // 如果有修改，设置新的注解列表
            if (modified) {
                nodeWithAnnotations.setAnnotations(newAnnotations);
            }
        }

        // 递归处理子节点
        for (Node child : node.getChildNodes()) {
            if (replaceAnnotations(child)) {
                modified = true;
            }
        }

        return modified;
    }

    private String getAnnotationName(AnnotationExpr annotation) {
        String name = annotation.getNameAsString();
        // 检查是否是简单名称还是全限定名
        if (name.contains(".")) {
            return name;
        }
        // 对于简单名称，尝试获取导入的包名（这里简化处理，只返回简单名称）
        return name;
    }

    private AnnotationReplacement findReplacement(String annotationName) {
        for (AnnotationReplacement replacement : replacements) {
            String oldAnnotation = replacement.getOldAnnotation();
            // 检查全匹配或简单名称匹配
            if (annotationName.equals(oldAnnotation) ||
                    annotationName.endsWith("." + oldAnnotation)) {
                return replacement;
            }
        }
        return null;
    }

}
