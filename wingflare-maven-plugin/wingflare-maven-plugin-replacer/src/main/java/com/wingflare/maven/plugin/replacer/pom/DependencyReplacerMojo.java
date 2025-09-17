package com.wingflare.maven.plugin.replacer.pom;


import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Mojo(name = "replace-dependency", defaultPhase = LifecyclePhase.INITIALIZE, requiresProject = true)
public class DependencyReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter
    private List<ReplacementRule> replacementRules;

    @Parameter(defaultValue = "${project.build.directory}/modified-pom.xml")
    private File outputPomFile;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Starting dependency replacement process...");

        if (replacementRules == null || replacementRules.isEmpty()) {
            getLog().warn("No replacement rules configured, skipping dependency replacement");
            return;
        }

        try {
            // 读取原始POM模型
            Model originalModel = project.getModel();
            // 创建模型副本以避免修改原始模型
            Model modifiedModel = createModelCopy(originalModel);
            // 处理依赖替换
            processDependencies(modifiedModel);
            // 保存修改后的POM
            saveModifiedPom(modifiedModel);
            // 使用修改后的POM进行后续构建
            project.setFile(outputPomFile);

            getLog().info("Successfully replaced dependencies. Modified POM saved to: " + outputPomFile.getAbsolutePath());
        } catch (Exception e) {
            throw new MojoExecutionException("Error replacing dependencies", e);
        }
    }

    private Model createModelCopy(Model original) {
        Model copy = new Model();
        copy.setGroupId(original.getGroupId());
        copy.setArtifactId(original.getArtifactId());
        copy.setVersion(original.getVersion());
        copy.setPackaging(original.getPackaging());
        copy.setName(original.getName());
        copy.setDescription(original.getDescription());
        copy.setDependencies(new ArrayList<>(original.getDependencies()));
        copy.setDependencyManagement(original.getDependencyManagement());
        copy.setParent(original.getParent());
        copy.setModules(new ArrayList<>(original.getModules()));
        // 复制其他必要的模型属性
        return copy;
    }

    private void processDependencies(Model model) {
        List<Dependency> originalDependencies = new ArrayList<>(model.getDependencies());
        List<Dependency> newDependencies = new ArrayList<>();

        // 首先处理所有依赖，应用替换规则
        for (Dependency dep : originalDependencies) {
            String depId = dep.getGroupId() + ":" + dep.getArtifactId();

            // 检查是否需要替换当前依赖，同时记录规则索引
            for (int ruleIndex = 0; ruleIndex < replacementRules.size(); ruleIndex++) {
                ReplacementRule rule = replacementRules.get(ruleIndex);

                if (rule.getSourceDepId().contains(depId)) {
                    getLog().info("Replacing dependency: " + depId + " using rule at index: " + ruleIndex);
                    String[] parts = rule.getTargetDepId().split(":");

                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Invalid dependency format: " + depId + ". Use groupId:artifactId[:version]");
                    }

                    dep.setGroupId(parts[0]);
                    dep.setArtifactId(parts[1]);
                }

                newDependencies.add(dep);
            }
        }

        model.setDependencies(newDependencies);
    }

    private void saveModifiedPom(Model model) throws IOException {
        // 确保输出目录存在
        if (!outputPomFile.getParentFile().exists()) {
            outputPomFile.getParentFile().mkdirs();
        }

        // 使用Maven的模型写入器保存修改后的POM
        try (FileWriter writer = new FileWriter(outputPomFile)) {
            org.apache.maven.model.io.DefaultModelWriter modelWriter = new org.apache.maven.model.io.DefaultModelWriter();
            modelWriter.write(writer, null, model);
        }
    }

}
