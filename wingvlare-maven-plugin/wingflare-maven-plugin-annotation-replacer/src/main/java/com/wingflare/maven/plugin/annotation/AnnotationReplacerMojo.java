package com.wingflare.maven.plugin.annotation;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Maven 插件用于将指定注解替换为另一个包下的同名注解
 */
@Mojo(name = "replace", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class AnnotationReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * 需要被替换的注解的全限定名（例如：com.old.Annotation）
     */
    @Parameter(required = true)
    private String sourceAnnotation;

    /**
     * 替换后的注解的全限定名（例如：com.new.Annotation）
     */
    @Parameter(required = true)
    private String targetAnnotation;

    /**
     * 源代码目录
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;

    /**
     * 测试源代码目录
     */
    @Parameter(defaultValue = "${project.build.testSourceDirectory}", required = true)
    private File testSourceDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Starting annotation replacement...");
        getLog().info("Source annotation: " + sourceAnnotation);
        getLog().info("Target annotation: " + targetAnnotation);

        try {
            // 处理主源代码
            processDirectory(sourceDirectory);

            // 处理测试源代码
            processDirectory(testSourceDirectory);

            getLog().info("Annotation replacement completed successfully");
        } catch (Exception e) {
            getLog().error("Error during annotation replacement", e);
            throw new MojoExecutionException("Failed to replace annotations", e);
        }
    }

    private void processDirectory(File directory) throws IOException {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            getLog().debug("Directory does not exist or is not a directory: " +
                    (directory != null ? directory.getAbsolutePath() : "null"));
            return;
        }

        getLog().info("Processing sources in: " + directory.getAbsolutePath());

        // 查找所有 Java 文件
        List<File> javaFiles = findJavaFiles(directory.toPath());

        getLog().info("Found " + javaFiles.size() + " Java files to process in directory");

        // 处理每个 Java 文件
        for (File javaFile : javaFiles) {
            processJavaFile(javaFile);
        }
    }

    private List<File> findJavaFiles(Path startPath) throws IOException {
        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
    }

    private void processJavaFile(File javaFile) throws IOException {
        getLog().debug("Processing file: " + javaFile.getAbsolutePath());

        // 解析 Java 文件
        JavaParser parser = new JavaParser();
        try (FileInputStream in = new FileInputStream(javaFile)) {
            ParseResult<CompilationUnit> result = parser.parse(in);

            if (result.isSuccessful() && result.getResult().isPresent()) {
                CompilationUnit cu = result.getResult().get();

                // 替换注解和导入
                AnnotationReplacementVisitor visitor = new AnnotationReplacementVisitor(cu);
                cu.accept(visitor, null);

                // 如果文件被修改，保存更改
                if (visitor.isFileModified()) {
                    try (FileWriter writer = new FileWriter(javaFile)) {
                        writer.write(cu.toString());
                        getLog().info("Modified file: " + javaFile.getAbsolutePath());
                    }
                }
            } else {
                getLog().warn("Failed to parse file: " + javaFile.getAbsolutePath());
                result.getProblems().forEach(problem ->
                        getLog().warn("Parse problem: " + problem.getMessage()));
            }
        }
    }

    private class AnnotationReplacementVisitor extends ModifierVisitor<Void> {
        private final CompilationUnit compilationUnit;
        private boolean fileModified = false;
        private final String sourceAnnotationSimpleName;
        private final String targetAnnotationSimpleName;

        public AnnotationReplacementVisitor(CompilationUnit cu) {
            this.compilationUnit = cu;
            this.sourceAnnotationSimpleName = getSimpleName(sourceAnnotation);
            this.targetAnnotationSimpleName = getSimpleName(targetAnnotation);
        }

        private String getSimpleName(String qualifiedName) {
            int lastDotIndex = qualifiedName.lastIndexOf('.');
            return lastDotIndex >= 0 ? qualifiedName.substring(lastDotIndex + 1) : qualifiedName;
        }

        @Override
        public Node visit(ImportDeclaration importDecl, Void arg) {
            // 替换导入语句
            String importName = importDecl.getNameAsString();
            if (importName.equals(sourceAnnotation) ||
                    (importName.endsWith("." + sourceAnnotationSimpleName) &&
                            importName.equals(sourceAnnotation))) {

                // 创建新的导入声明
                ImportDeclaration newImport = new ImportDeclaration(
                        new Name(targetAnnotation),
                        importDecl.isStatic(),
                        importDecl.isAsterisk()
                );

                fileModified = true;
                return newImport;
            }

            return super.visit(importDecl, arg);
        }

        private String resolveAnnotationQualifiedName(AnnotationExpr annotation) {
            String annotationName = annotation.getNameAsString();

            // 如果是全限定名，直接使用
            if (annotationName.contains(".")) {
                return annotationName;
            }

            // 否则，尝试从导入语句中解析
            Optional<ImportDeclaration> matchingImport = compilationUnit.getImports().stream()
                    .filter(importDecl -> !importDecl.isAsterisk())
                    .filter(importDecl -> {
                        String importName = importDecl.getNameAsString();
                        return importName.equals(sourceAnnotation) ||
                                importName.endsWith("." + annotationName);
                    })
                    .findFirst();

            return matchingImport.map(importDecl -> importDecl.getNameAsString())
                    .orElse(annotationName); // 如果找不到导入，返回简单名称
        }

        private AnnotationExpr createReplacementAnnotation(AnnotationExpr original) {
            // 根据原注解类型创建相应的新注解
            if (original instanceof NormalAnnotationExpr) {
                NormalAnnotationExpr normalAnnotation = (NormalAnnotationExpr) original;
                return new NormalAnnotationExpr(
                        new Name(targetAnnotationSimpleName),
                        normalAnnotation.getPairs()
                );
            } else if (original instanceof SingleMemberAnnotationExpr) {
                SingleMemberAnnotationExpr singleAnnotation = (SingleMemberAnnotationExpr) original;
                return new SingleMemberAnnotationExpr(
                        new Name(targetAnnotationSimpleName),
                        singleAnnotation.getMemberValue()
                );
            } else {
                // 标记注解（没有参数）
                return new MarkerAnnotationExpr(new Name(targetAnnotationSimpleName));
            }
        }

        public boolean isFileModified() {
            return fileModified;
        }
    }
}
