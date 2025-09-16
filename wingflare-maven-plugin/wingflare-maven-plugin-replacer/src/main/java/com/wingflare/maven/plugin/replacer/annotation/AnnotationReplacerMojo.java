package com.wingflare.maven.plugin.replacer.annotation;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Mojo(name = "replace", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class AnnotationReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(required = false)
    private List<AnnotationReplacement> replacementAnnotations;

    @Parameter(required = false)
    private List<ClassesReplacement> replacementClasses;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/annotation-replacer", required = true)
    private File tempDirectory;

    private JavaParser javaParser;
    private static final Pattern ANNOTATION_PATTERN = Pattern.compile("^(@[^(]+)(\\(.*\\))?$");

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("=== Starting annotation replacement ===");
        getLog().info("Found " + replacementAnnotations.size() + " replacementAnnotation rules");
        getLog().info("Found " + replacementClasses.size() + " replacementClass rules");

        javaParser = new JavaParser();

        if (!sourceDirectory.exists()) {
            getLog().warn("Source directory does not exist: " + sourceDirectory.getAbsolutePath());
            return;
        }

        try {
            // 准备临时目录
            prepareTempDirectory();

            // 复制源代码到临时目录（修复多级目录问题）
            copySourceToTempDirectory(sourceDirectory, tempDirectory);

            // 处理临时目录中的代码
            processDirectory(tempDirectory);

            // 替换项目的源目录为临时目录
            replaceSourceDirectoryInProject();

            getLog().info("=== Annotation replacement setup completed successfully ===");
        } catch (IOException e) {
            throw new MojoExecutionException("Error during annotation replacement process", e);
        }
    }

    /**
     * 准备临时目录 - 确保目录存在且为空
     */
    private void prepareTempDirectory() throws IOException {
        // 如果临时目录已存在，先删除
        if (tempDirectory.exists()) {
            deleteDirectory(tempDirectory);
        }

        // 创建新的临时目录
        if (!tempDirectory.mkdirs()) {
            throw new IOException("Failed to create temp directory: " + tempDirectory.getAbsolutePath());
        }
        getLog().info("Prepared temp directory: " + tempDirectory.getAbsolutePath());
    }

    /**
     * 递归删除目录
     */
    private void deleteDirectory(File directory) throws IOException {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        if (!directory.delete()) {
            getLog().warn("Could not delete file/directory: " + directory.getAbsolutePath());
        }
    }

    /**
     * 修复的目录复制方法 - 正确处理多级目录
     * @param source 源文件/目录
     * @param target 目标文件/目录
     */
    private void copySourceToTempDirectory(File source, File target) throws IOException {
        // 如果源是目录
        if (source.isDirectory()) {
            // 确保目标目录存在
            if (!target.exists() && !target.mkdirs()) {
                throw new IOException("Failed to create directory: " + target.getAbsolutePath());
            }

            getLog().debug("Copying directory: " + source.getAbsolutePath() + " to " + target.getAbsolutePath());

            // 获取源目录中的所有文件和子目录
            File[] files = source.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 构建目标文件/目录的路径
                    File targetFile = new File(target, file.getName());
                    // 递归复制
                    copySourceToTempDirectory(file, targetFile);
                }
            }
        }
        // 如果源是文件且是Java文件
        else if (source.isFile() && source.getName().endsWith(".java")) {
            // 复制文件到目标位置
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            getLog().debug("Copied file: " + source.getAbsolutePath() + " to " + target.getAbsolutePath());
        }
    }

    /**
     * 将项目的源目录替换为临时目录
     */
    private void replaceSourceDirectoryInProject() {
        // 移除原始源目录
        project.getCompileSourceRoots().remove(sourceDirectory.getAbsolutePath());
        getLog().info("Removed original source directory from compilation: " + sourceDirectory.getAbsolutePath());

        // 添加临时目录作为新的源目录
        project.addCompileSourceRoot(tempDirectory.getAbsolutePath());
        getLog().info("Added modified temp directory to compilation: " + tempDirectory.getAbsolutePath());
    }

    private void processDirectory(File directory) throws MojoExecutionException {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                getLog().debug("Processing directory: " + file.getPath());
                processDirectory(file);
            } else if (file.getName().endsWith(".java")) {
                getLog().debug("Processing file: " + file.getPath());
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

                // 先处理import语句
                boolean importModified = processImports(cu);
                // 再处理注解使用
                boolean annotationsModified = replaceAnnotations(cu);

                if (importModified || annotationsModified) {
                    try (FileWriter writer = new FileWriter(file)) {

                        if (replacementClasses != null) {
                            for (ClassesReplacement replacement: replacementClasses) {
                                // 创建修改器访问者并应用修改
                                ClassNameReplacerVisitor visitor = new ClassNameReplacerVisitor(replacement.getOldClass(), replacement.getNewClass(), getLog());
                                if (visitor.isModified()) {
                                    cu = (CompilationUnit) visitor.visit(cu, null);
                                }
                            }
                        }

                        writer.write(LexicalPreservingPrinter.print(cu));
                        getLog().debug("Modified file: " + file.getAbsolutePath());
                    }
                } else {
                    if (replacementClasses != null) {
                        for (ClassesReplacement replacement: replacementClasses) {
                            // 创建修改器访问者并应用修改
                            ClassNameReplacerVisitor visitor = new ClassNameReplacerVisitor(replacement.getOldClass(), replacement.getNewClass(), getLog());
                            if (visitor.isModified()) {
                                cu = (CompilationUnit) visitor.visit(cu, null);
                            }
                        }
                    }
                }
            } else {
                getLog().warn("Could not parse file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Error processing file: " + file.getAbsolutePath(), e);
        }
    }

    private boolean processImports(CompilationUnit cu) {
        boolean modified = false;
        NodeList<ImportDeclaration> imports = cu.getImports();

        for (int i = 0; i < imports.size(); i++) {
            ImportDeclaration importDecl = imports.get(i);
            String importName = importDecl.getNameAsString();

            Optional<AnnotationReplacement> replacementOpt = findImportReplacement(importName);
            if (replacementOpt.isPresent()) {
                AnnotationReplacement replacement = replacementOpt.get();

                imports.remove(i);
                i--;

                ImportDeclaration newImport = new ImportDeclaration(replacement.getNewAnnotation(),
                        importDecl.isStatic(),
                        importDecl.isAsterisk());
                imports.add(newImport);

                modified = true;
                getLog().debug("Replaced import: " + importName + " with " + replacement.getNewAnnotation());
            }
        }

        return modified;
    }

    private Optional<AnnotationReplacement> findImportReplacement(String importName) {
        for (AnnotationReplacement replacement : replacementAnnotations) {
            if (importName.equals(replacement.getOldAnnotation())) {
                return Optional.of(replacement);
            }
        }
        return Optional.empty();
    }

    private boolean replaceAnnotations(Node node) {
        boolean modified = false;

        if (node instanceof NodeWithAnnotations<?>) {
            NodeWithAnnotations<?> nodeWithAnnotations = (NodeWithAnnotations<?>) node;
            NodeList<AnnotationExpr> annotations = nodeWithAnnotations.getAnnotations();

            NodeList<AnnotationExpr> newAnnotations = new NodeList<>();
            for (AnnotationExpr annotation : annotations) {
                String annotationStr = annotation.toString();
                String annotationName = getAnnotationName(annotation);
                AnnotationReplacement replacement = findReplacement(annotationName);

                if (replacement != null) {
                    AnnotationExpr processedAnnotation = processAnnotationReplacement(annotation, annotationStr, replacement);
                    if (processedAnnotation != null) {
                        newAnnotations.add(processedAnnotation);
                        modified = true;
                    } else {
                        newAnnotations.add(annotation);
                    }
                } else {
                    newAnnotations.add(annotation);
                }
            }

            if (modified) {
                nodeWithAnnotations.setAnnotations(newAnnotations);
            }
        }

        for (Node child : node.getChildNodes()) {
            if (replaceAnnotations(child)) {
                modified = true;
            }
        }

        return modified;
    }

    private AnnotationExpr processAnnotationReplacement(AnnotationExpr originalAnnotation,
                                                        String originalAnnotationStr,
                                                        AnnotationReplacement replacement) {
        try {
            AnnotationExpr newAnnotation;

            if (originalAnnotation.getNameAsString().contains(".")) {
                String annotationParams = extractAnnotationParameters(originalAnnotationStr);
                String newAnnotationCode = "@" + replacement.getNewAnnotation() +
                        (annotationParams != null ? annotationParams : "");

                ParseResult<AnnotationExpr> annotationResult = javaParser.parseAnnotation(newAnnotationCode);
                if (!annotationResult.isSuccessful() || !annotationResult.getResult().isPresent()) {
                    getLog().warn("Could not parse new annotation: " + newAnnotationCode);
                    return null;
                }
                newAnnotation = annotationResult.getResult().get();
            } else {
                newAnnotation = originalAnnotation.clone();
            }

            if (replacement.getParamReplacements() != null && !replacement.getParamReplacements().isEmpty()) {
                boolean paramsModified = replaceAnnotationParameters(newAnnotation, replacement.getParamReplacements());
                if (paramsModified) {
                    getLog().debug("Modified parameters for annotation: " + replacement.getOldAnnotation());
                }
            }

            return newAnnotation;
        } catch (Exception e) {
            getLog().warn("Error processing annotation replacement: " + originalAnnotationStr + " - " + e.getMessage());
            return null;
        }
    }

    private boolean replaceAnnotationParameters(AnnotationExpr annotation,
                                                List<AnnotationParamReplacement> paramReplacements) {
        boolean modified = false;

        if (annotation instanceof SingleMemberAnnotationExpr) {
            handleSingleMemberAnnotation((SingleMemberAnnotationExpr) annotation, paramReplacements);
            modified = true;
        } else if (annotation instanceof NormalAnnotationExpr) {
            for (MemberValuePair pair : ((NormalAnnotationExpr) annotation).getPairs()) {
                String paramName = pair.getNameAsString();

                for (AnnotationParamReplacement paramReplacement : paramReplacements) {
                    if (paramName.equals(paramReplacement.getOldParamName())) {
                        pair.setName(paramReplacement.getNewParamName());
                        modified = true;
                        getLog().debug("Replaced parameter: " + paramName + " with " + paramReplacement.getNewParamName());
                        break;
                    }
                }
            }
        }

        return modified;
    }

    private void handleSingleMemberAnnotation(SingleMemberAnnotationExpr annotation,
                                              List<AnnotationParamReplacement> paramReplacements) {
        for (AnnotationParamReplacement paramReplacement : paramReplacements) {
            if ("value".equals(paramReplacement.getOldParamName())) {
                NormalAnnotationExpr normalAnnotation = new NormalAnnotationExpr(
                        annotation.getName(),
                        new NodeList<>(new MemberValuePair(paramReplacement.getNewParamName(), annotation.getMemberValue()))
                );
                annotation.replace(normalAnnotation);
                getLog().debug("Replaced single member annotation parameter 'value' with: " + paramReplacement.getNewParamName());
                break;
            }
        }
    }

    private String extractAnnotationParameters(String annotationStr) {
        Matcher matcher = ANNOTATION_PATTERN.matcher(annotationStr);
        if (matcher.matches()) {
            return matcher.group(2);
        }
        return null;
    }

    private String getAnnotationName(AnnotationExpr annotation) {
        return annotation.getNameAsString();
    }

    private AnnotationReplacement findReplacement(String annotationName) {
        for (AnnotationReplacement replacement : replacementAnnotations) {
            String oldAnnotation = replacement.getOldAnnotation();

            if (annotationName.equals(oldAnnotation)) {
                return replacement;
            }

            int lastDotIndex = oldAnnotation.lastIndexOf('.');
            if (lastDotIndex > 0) {
                String simpleName = oldAnnotation.substring(lastDotIndex + 1);
                if (annotationName.equals(simpleName)) {
                    return replacement;
                }
            }
        }
        return null;
    }

    /**
     * 自定义访问者，用于遍历AST并替换类名
     */
    private static class ClassNameReplacerVisitor extends ModifierVisitor<Void> {

        private final String originalClass;
        private final String targetClass;
        private boolean modified = false;
        private final Log log;

        public ClassNameReplacerVisitor(String originalClass, String targetClass, Log log) {
            this.originalClass = originalClass;
            this.targetClass = targetClass;
            this.log = log;
        }

        public boolean isModified() {
            return modified;
        }

        // 处理类或接口声明
        @Override
        public Visitable visit(ClassOrInterfaceDeclaration n, Void arg) {
            if (n.getNameAsString().equals(originalClass)) {
                n.setName(targetClass);
                modified = true;
                log.debug("Renamed class/interface: " + originalClass + " -> " + targetClass);
            }
            return super.visit(n, arg);
        }

        // 处理类或接口类型引用（如变量声明、返回类型等）
        @Override
        public Visitable visit(ClassOrInterfaceType n, Void arg) {
            if (n.getNameAsString().equals(originalClass)) {
                n.setName(targetClass);
                modified = true;
                log.debug("Replaced class reference: " + originalClass + " -> " + targetClass);
            }
            return super.visit(n, arg);
        }

        // 处理注解
        @Override
        public Visitable visit(NormalAnnotationExpr n, Void arg) {
            // 处理注解本身的类名
            if (n.getNameAsString().equals(originalClass)) {
                n.setName(targetClass);
                modified = true;
                log.debug("Replaced annotation class: " + originalClass + " -> " + targetClass);
            }

            // 处理注解参数中的类名引用
            for (MemberValuePair pair : n.getPairs()) {
                processAnnotationValue(pair.getValue());
            }

            return super.visit(n, arg);
        }

        // 处理注解参数值中的类名
        private void processAnnotationValue(Expression expr) {
            if (expr instanceof ClassExpr) {
                ClassExpr classExpr = (ClassExpr) expr;
                if (classExpr.getTypeAsString().equals(originalClass)) {
                    classExpr.setType(targetClass);
                    modified = true;
                    log.debug("Replaced class reference in annotation: " + originalClass + " -> " + targetClass);
                }
            } else if (expr instanceof NameExpr) {
                NameExpr nameExpr = (NameExpr) expr;
                if (nameExpr.getNameAsString().equals(originalClass)) {
                    nameExpr.setName(targetClass);
                    modified = true;
                }
            } else if (expr instanceof ArrayInitializerExpr) {
                ArrayInitializerExpr arrayExpr = (ArrayInitializerExpr) expr;
                arrayExpr.getValues().forEach(this::processAnnotationValue);
            }
        }

        // 处理枚举常量（可能作为注解参数）
        @Override
        public Visitable visit(FieldAccessExpr n, Void arg) {
            if (n.getNameAsString().equals(originalClass)) {
                n.setName(targetClass);
                modified = true;
            } else if (n.getScope().toString().equals(originalClass)) {
                n.setScope(new NameExpr(targetClass));
                modified = true;
            }
            return super.visit(n, arg);
        }

        // 处理类名作为方法调用的一部分
        @Override
        public Visitable visit(NameExpr n, Void arg) {
            if (n.getNameAsString().equals(originalClass)) {
                n.setName(targetClass);
                modified = true;
                log.debug("Replaced name reference: " + originalClass + " -> " + targetClass);
            }
            return super.visit(n, arg);
        }

        // 处理导入声明（包括静态导入）
        @Override
        public Node visit(ImportDeclaration n, Void arg) {
            String importName = n.getNameAsString();

            // 处理普通类导入
            if (importName.endsWith("." + originalClass)) {
                String newImportName = importName.replace("." + originalClass, "." + targetClass);
                n.setName(newImportName);
                modified = true;
                log.debug("Updated import: " + importName + " -> " + newImportName);
            }
            // 处理静态导入
            else if (n.isStatic() && importName.startsWith(originalClass + ".")) {
                String newImportName = importName.replace(originalClass + ".", targetClass + ".");
                n.setName(newImportName);
                modified = true;
                log.debug("Updated static import: " + importName + " -> " + newImportName);
            }
            // 处理通配符静态导入
            else if (n.isStatic() && importName.equals(originalClass + ".*")) {
                n.setName(targetClass + ".*");
                modified = true;
                log.debug("Updated static wildcard import: " + importName + " -> " + targetClass + ".*");
            }

            return super.visit(n, arg);
        }

    }

}
