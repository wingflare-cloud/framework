package com.wingflare.maven.plugin.replacer.annotation;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ClassExpr;
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
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Mojo(name = "replace", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class AnnotationReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    private List<ReplacementRule> replacementRules;

    private List<AnnotationReplacement> replacementAnnotations;

    private List<ClassesReplacement> replacementClasses;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/wingflare/main-java", required = true)
    private File tempDirectory;

    private JavaParser javaParser;
    private static final Pattern ANNOTATION_PATTERN = Pattern.compile("^(@[^(]+)(\\(.*\\))?$");

    @Override
    public void execute() throws MojoExecutionException {
        if (project.getProperties().getProperty("wingflare.replace", "false").equals("true")) {
            getLog().info("=== Starting annotation replacement ===");
            MavenPomUpdater mavenPomUpdater;

            try {
                loadReplacements();
                mavenPomUpdater = new MavenPomUpdater(replacementRules);
                mavenPomUpdater.setSourceDirectory(getRelativePath(new File(tempDirectory.getAbsolutePath()), project.getFile().getParentFile()));
                // 准备临时目录
                prepareTempDirectory();
            } catch (Exception e) {
                getLog().error("解析配置出错: " + e.getMessage());
                return;
            }

            getLog().info("Found " + replacementAnnotations.size() + " replacementAnnotation rules");
            getLog().info("Found " + replacementClasses.size() + " replacementClass rules");

            javaParser = new JavaParser();

            if (!sourceDirectory.exists()) {
                getLog().warn("Source directory does not exist: " + sourceDirectory.getAbsolutePath());
                return;
            }

            try {
                // 复制源代码到临时目录（修复多级目录问题）
                copySourceToTempDirectory(sourceDirectory, tempDirectory);

                // 处理临时目录中的代码
                processDirectory(tempDirectory);

                mavenPomUpdater.updatePomDependencies(project.getFile());

                getLog().info("=== Annotation replacement setup completed successfully ===");
            } catch (Exception e) {
                throw new MojoExecutionException("Error during annotation replacement process", e);
            }
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
     * 计算目标文件相对于基准目录的相对路径
     * @param targetFile 目标文件
     * @param baseDir 基准目录（必须是目录，否则计算可能出错）
     * @return 相对路径字符串
     */
    private static String getRelativePath(File targetFile, File baseDir) {
        try {
            // 1. 获取绝对路径（处理符号链接、相对路径等问题）
            String targetAbsPath = targetFile.getCanonicalPath(); // 规范路径（更准确）
            String baseAbsPath = baseDir.getCanonicalPath();

            // 2. 分割路径为目录数组（处理不同系统的路径分隔符）
            String separator = File.separator;
            String[] targetParts = targetAbsPath.split(separator.replace("\\", "\\\\")); // 处理Windows反斜杠
            String[] baseParts = baseAbsPath.split(separator.replace("\\", "\\\\"));

            // 3. 找到共同父目录的索引
            int commonIndex = 0;
            while (commonIndex < targetParts.length && commonIndex < baseParts.length) {
                if (!targetParts[commonIndex].equals(baseParts[commonIndex])) {
                    break;
                }
                commonIndex++;
            }

            // 4. 构建相对路径
            List<String> relativeParts = new ArrayList<>();
            // 基准目录中，从共同父目录到基准目录的部分：用".."回退
            for (int i = commonIndex; i < baseParts.length; i++) {
                relativeParts.add("..");
            }
            // 拼接目标文件中，从共同父目录到目标文件的部分
            for (int i = commonIndex; i < targetParts.length; i++) {
                relativeParts.add(targetParts[i]);
            }

            // 5. 组合路径（用系统默认分隔符）
            if (relativeParts.isEmpty()) {
                return "."; // 目标文件就是基准目录本身
            }
            return String.join(separator, relativeParts);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // 异常时返回null
        }
    }

    /**
     * 递归删除目录
     */
    private void deleteDirectory(File directory) {
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

                boolean isModified = false;
                // 先处理import语句
                boolean importModified = processImports(cu);
                // 再处理注解使用
                boolean annotationModified = replaceAnnotations(cu);

                boolean classModified = replaceClassReferences(cu);

                // 合并修改标记
                isModified = importModified || annotationModified || classModified;

                if (isModified) {
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(LexicalPreservingPrinter.print(cu));
                        getLog().debug("Modified file: " + file.getAbsolutePath());
                    }
                } else {
                    getLog().debug("File not Modify");
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

    /**
     * 处理类名替换（全场景覆盖：类引用、枚举、导入、注解参数等）
     */
    private boolean replaceClassReferences(CompilationUnit cu) {
        boolean isModified = false;
        if (replacementClasses == null || replacementClasses.isEmpty()) {
            return false;
        }

        // 遍历所有类名替换规则，逐个执行替换
        for (ClassesReplacement replacement : replacementClasses) {
            ClassNameReplacerVisitor visitor = new ClassNameReplacerVisitor(
                    replacement.getOldClass(),  // 旧类全类名
                    replacement.getNewClass(),   // 新类全类名
                    getLog()
            );
            // 执行AST遍历替换
            visitor.visit(cu, null);

            if (visitor.isModified()) {
                isModified = true;
            }

        }
        return isModified;
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

    // ============================== 类名替换访问者：全场景覆盖 ==============================
    /**
     * 自定义AST访问者：遍历并替换所有旧类引用（支持类声明、枚举、导入、注解参数等）
     */
    private static class ClassNameReplacerVisitor extends ModifierVisitor<Void> {
        private final String oldClassFullName;  // 旧类全类名（如com.wingflare...RequestMethod）
        private final String newClassFullName;  // 新类全类名（如org.springframework...RequestMethod）
        private final String oldClassSimpleName;// 旧类简单类名（如RequestMethod）
        private final String newClassSimpleName;// 新类简单类名（如RequestMethod）
        private final Log log;
        private boolean isModified = false;     // 是否有修改

        // 构造器：初始化全类名和简单类名
        public ClassNameReplacerVisitor(String oldClassFullName, String newClassFullName, Log log) {
            this.oldClassFullName = oldClassFullName;
            this.newClassFullName = newClassFullName;
            this.oldClassSimpleName = getClassSimpleName(oldClassFullName);
            this.newClassSimpleName = getClassSimpleName(newClassFullName);
            this.log = log;
        }

        // 工具方法：提取简单类名
        private static String getClassSimpleName(String fullClassName) {
            if (fullClassName == null || fullClassName.isEmpty()) {
                return "";
            }
            int lastDotIndex = fullClassName.lastIndexOf('.');
            return lastDotIndex == -1 ? fullClassName : fullClassName.substring(lastDotIndex + 1);
        }

        // Getter：是否有修改
        public boolean isModified() {
            return isModified;
        }

        // 1. 处理类/接口声明（如public class RequestMethod → 极少用，用于类重命名）
        @Override
        public Visitable visit(ClassOrInterfaceDeclaration node, Void arg) {
            if (node.getNameAsString().equals(oldClassSimpleName)) {
                node.setName(newClassSimpleName);
                isModified = true;
                this.log.debug("[类声明替换] 旧类名：" + oldClassSimpleName + " → 新类名：" + newClassSimpleName);
            }
            return super.visit(node, arg);
        }

        // 2. 处理类/接口类型引用（如变量类型、返回值、参数类型等）
        @Override
        public Visitable visit(ClassOrInterfaceType node, Void arg) {
            String nodeTypeName = node.getNameAsString(); // 如RequestMethod
            if (nodeTypeName.equals(oldClassSimpleName)) {
                node.setName(newClassSimpleName);
                isModified = true;
                this.log.debug("[类型引用替换] 旧类型：" + oldClassSimpleName + " → 新类型：" + newClassSimpleName);
            }
            return super.visit(node, arg);
        }

        // 3. 处理枚举常量/静态字段引用（如RequestMethod.GET、Math.PI）
        @Override
        public Visitable visit(FieldAccessExpr node, Void arg) {
            // 场景1：scope是旧类简单类名（如RequestMethod.GET → 替换RequestMethod）
            if (node.getScope() instanceof NameExpr) {
                NameExpr scopeNameExpr = (NameExpr) node.getScope();
                if (scopeNameExpr.getNameAsString().equals(oldClassSimpleName)) {
                    scopeNameExpr.setName(newClassSimpleName);
                    isModified = true;
                    this.log.debug("[枚举引用替换] 旧枚举类：" + oldClassSimpleName + " → 新枚举类：" + newClassSimpleName);
                }
            }
            // 场景2：scope是旧类全类名（如com.wingflare...RequestMethod.GET → 极少用）
            else if (node.getScope().toString().equals(oldClassFullName)) {
                node.setScope(new NameExpr(newClassFullName));
                isModified = true;
                this.log.debug("[枚举引用替换] 旧枚举类：" + oldClassFullName + " → 新枚举类：" + newClassFullName);
            }
            return super.visit(node, arg);
        }

        // 4. 处理普通名称引用（如Class<?> cls = RequestMethod.class中的RequestMethod）
        @Override
        public Visitable visit(NameExpr node, Void arg) {
            String nodeName = node.getNameAsString();
            if (nodeName.equals(oldClassSimpleName) || nodeName.equals(oldClassFullName)) {
                String newName = nodeName.contains(".") ? newClassFullName : newClassSimpleName;
                node.setName(newName);
                isModified = true;
                this.log.debug("[名称引用替换] 旧名称：" + nodeName + " → 新名称：" + newName);
            }
            return super.visit(node, arg);
        }

        // 5. 处理导入声明（补充processImports未覆盖的场景，如静态导入单个常量）
        @Override
        public Node visit(ImportDeclaration node, Void arg) {
            String importFullName = node.getNameAsString();
            // 场景1：普通导入旧类（如com.wingflare...RequestMethod）
            if (importFullName.equals(oldClassFullName)) {
                node.setName(newClassFullName);
                isModified = true;
                this.log.debug("[导入补充替换] 旧导入：" + importFullName + " → 新导入：" + newClassFullName);
            }
            // 场景2：静态导入旧类的单个常量（如com.wingflare...RequestMethod.GET）
            else if (node.isStatic() && importFullName.startsWith(oldClassFullName + ".")) {
                String newImportFullName = importFullName.replace(oldClassFullName + ".", newClassFullName + ".");
                node.setName(newImportFullName);
                isModified = true;
                this.log.debug("[导入补充替换] 旧静态导入：" + importFullName + " → 新静态导入：" + newImportFullName);
            }
            return super.visit(node, arg);
        }

        // 6. 处理类字面量（如RequestMethod.class）
        @Override
        public Visitable visit(ClassExpr node, Void arg) {
            String typeName = node.getTypeAsString();
            if (typeName.equals(oldClassSimpleName)) {
                node.setType(newClassSimpleName);
                isModified = true;
                this.log.debug("[类字面量替换] 旧类字面量：" + typeName + ".class → 新类字面量：" + newClassSimpleName + ".class");
            } else if (typeName.equals(oldClassFullName)) {
                node.setType(newClassFullName);
                isModified = true;
                this.log.debug("[类字面量替换] 旧类字面量：" + typeName + ".class → 新类字面量：" + newClassFullName + ".class");
            }
            return super.visit(node, arg);
        }
    }

    private File getRootProjectDirectory(MavenProject project) {
        // 从当前项目开始向上查找，直到找到没有父项目的根项目
        MavenProject rootProject = project;

        while (rootProject.getParent() != null) {
            rootProject = rootProject.getParent();
        }
        // 返回根项目的基础目录
        return rootProject.getBasedir();
    }

    /**
     * 从.mvn/extensions.xml加载替换规则，修复Xpp3DomBuilder参数问题
     */
    private void loadReplacements() throws IOException {
        File extensionsFile = new File(getRootProjectDirectory(project), "/wingflare.xml");

        replacementAnnotations = new ArrayList<>();
        replacementClasses = new ArrayList<>();
        replacementRules = new ArrayList<>();

        getLog().info("====项目:"+ project.getName() +"=====配置目录:" + extensionsFile.getAbsolutePath() + "========");

        if (!extensionsFile.exists() || !extensionsFile.isFile()) {
            getLog().warn("配置文件不存在");
            return;
        }

        // 使用try-with-resources确保流正确关闭
        try (InputStream is = new FileInputStream(extensionsFile)) {
            Xpp3Dom rootDom = Xpp3DomBuilder.build(is, "UTF-8");

            Xpp3Dom annotationsDom = rootDom.getChild("annotations");
            if (annotationsDom != null) {
                Xpp3Dom[] annotationDom = annotationsDom.getChildren("replacement");

                for (Xpp3Dom annotation : annotationDom) {
                    AnnotationReplacement replacement = new AnnotationReplacement();
                    replacement.setOldAnnotation(getChildValue(annotation, "oldAnnotation"));
                    replacement.setNewAnnotation(getChildValue(annotation, "newAnnotation"));
                    replacementAnnotations.add(replacement);
                }
            } else {
                getLog().warn("annotations为空或读取失败");
            }

            Xpp3Dom classesDom = rootDom.getChild("classes");

            if (classesDom != null) {
                Xpp3Dom[] classDoms = classesDom.getChildren("replacement");

                for (Xpp3Dom classItem : classDoms) {
                    ClassesReplacement replacement = new ClassesReplacement();
                    replacement.setOldClass(getChildValue(classItem, "oldClass"));
                    replacement.setNewClass(getChildValue(classItem, "newClass"));
                    replacementClasses.add(replacement);
                }
            } else {
                getLog().warn("classes为空或读取失败");
            }

            Xpp3Dom dependenciesDom = rootDom.getChild("dependencies");

            if (dependenciesDom != null) {
                Xpp3Dom[] dependenciesDoms = dependenciesDom.getChildren("replacement");

                for (Xpp3Dom dependencyItem : dependenciesDoms) {
                    ReplacementRule replacement = new ReplacementRule();
                    replacement.setSourceDepId(getChildValue(dependencyItem, "sourceDepId"));
                    replacement.setTargetDepId(getChildValue(dependencyItem, "targetDepId"));
                    replacementRules.add(replacement);
                }
            } else {
                getLog().warn("dependencies为空或读取失败");
            }
        } catch (XmlPullParserException e) {
            throw new IOException("解析wingflare.xml失败: " + e.getMessage(), e);
        }
    }

    private String getChildValue(Xpp3Dom parent, String childName) {
        Xpp3Dom child = parent.getChild(childName);
        return (child != null) ? child.getValue() : null;
    }

}
