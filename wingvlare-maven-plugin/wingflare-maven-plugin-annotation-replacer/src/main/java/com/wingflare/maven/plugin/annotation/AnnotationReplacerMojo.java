package com.wingflare.maven.plugin.annotation;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * 注解替换Maven插件的主类
 * 在编译前执行，替换源代码中的指定注解
 */
@Mojo(name = "replace-annotations", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class AnnotationReplacerMojo extends AbstractMojo {

    /**
     * Maven项目对象
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * 源代码目录
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;

    /**
     * 注解替换规则列表
     */
    @Parameter(required = true)
    private List<AnnotationReplacement> replacements;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Starting annotation replacement process...");

        if (replacements == null || replacements.isEmpty()) {
            getLog().warn("No annotation replacements configured. Nothing to do.");
            return;
        }

        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            getLog().warn("Source directory does not exist: " + sourceDirectory.getAbsolutePath());
            return;
        }

        try {
            // 遍历所有Java源文件
            try (Stream<Path> stream = Files.walk(Paths.get(sourceDirectory.getAbsolutePath()))) {
                stream.filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".java"))
                        .forEach(this::processJavaFile);
            }

            getLog().info("Annotation replacement process completed successfully.");
        } catch (IOException e) {
            throw new MojoExecutionException("Error processing source files", e);
        }
    }

    /**
     * 处理单个Java文件，替换其中的注解
     */
    private void processJavaFile(Path filePath) {
        try {
            getLog().debug("Processing file: " + filePath);

            // 读取文件内容
            String content = new String(Files.readAllBytes(filePath));

            // 解析Java代码
            CompilationUnit cu = StaticJavaParser.parse(content);

            // 启用词法保留，确保格式化不受影响
            LexicalPreservingPrinter.setup(cu);

            // 替换注解
            boolean modified = replaceAnnotations(cu);

            // 如果有修改，保存文件
            if (modified) {
                try (FileWriter writer = new FileWriter(filePath.toFile())) {
                    writer.write(LexicalPreservingPrinter.print(cu));
                    getLog().info("Modified file: " + filePath);
                }
            }
        } catch (Exception e) {
            getLog().error("Error processing file " + filePath, e);
        }
    }

    /**
     * 在编译单元中替换注解
     */
    private boolean replaceAnnotations(CompilationUnit cu) {
        boolean modified = false;

        // 遍历所有带有注解的节点
        cu.findAll(NodeWithAnnotations.class).forEach(node -> {
            NodeList<AnnotationExpr> annotations = node.getAnnotations();

            // 检查每个注解是否需要替换
            for (int i = 0; i < annotations.size(); i++) {
                AnnotationExpr annotation = annotations.get(i);
                String annotationName = annotation.getNameAsString();

                // 查找匹配的替换规则
                for (AnnotationReplacement replacement : replacements) {
                    // 检查简单类名或全类名是否匹配
                    if (annotationName.equals(getSimpleName(replacement.getOldAnnotation())) ||
                            annotationName.equals(replacement.getOldAnnotation())) {

                        // 创建新注解
                        AnnotationExpr newAnnotation = StaticJavaParser.parseAnnotation(replacement.getNewAnnotation() + "");
                        annotations.set(i, newAnnotation);

                        getLog().debug("Replaced annotation: " + replacement.getOldAnnotation() +
                                " with: " + replacement.getNewAnnotation());
                        modified = true;
                        break;
                    }
                }
            }
        });

        return modified;
    }

    /**
     * 从全类名中获取简单类名
     */
    private String getSimpleName(String fullyQualifiedName) {
        if (fullyQualifiedName == null || fullyQualifiedName.isEmpty()) {
            return fullyQualifiedName;
        }
        int lastDotIndex = fullyQualifiedName.lastIndexOf('.');
        return lastDotIndex == -1 ? fullyQualifiedName : fullyQualifiedName.substring(lastDotIndex + 1);
    }
}
