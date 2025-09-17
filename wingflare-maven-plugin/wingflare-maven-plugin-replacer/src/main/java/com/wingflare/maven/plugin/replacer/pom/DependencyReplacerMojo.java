package com.wingflare.maven.plugin.replacer.pom;


import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.VersionRangeRequest;
import org.eclipse.aether.resolution.VersionRangeResolutionException;
import org.eclipse.aether.resolution.VersionRangeResult;
import org.eclipse.aether.version.Version;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mojo(name = "replace-dependency", defaultPhase = LifecyclePhase.INITIALIZE, requiresProject = true)
public class DependencyReplacerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter
    private List<ReplacementRule> replacementRules;

    @Parameter(defaultValue = "${project.build.directory}/modified-pom.xml")
    private File outputPomFile;

    @Component
    private RepositorySystem repositorySystem;

    @Parameter(defaultValue = "${project.remoteProjectRepositories}", readonly = true)
    private List<RemoteRepository> remoteRepositories;

    // 不直接注入RepositorySystemSession，而是从project中获取
    private RepositorySystemSession getRepositorySession() {
        return project.getProjectBuildingRequest().getRepositorySession();
    }

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Starting dependency replacement process...");

        if (replacementRules == null || replacementRules.isEmpty()) {
            getLog().warn("No replacement rules configured, skipping dependency replacement");
            return;
        }

        try {
            Model originalModel = project.getModel();
            Model modifiedModel = createModelCopy(originalModel);
            processDependencies(modifiedModel);
            saveModifiedPom(modifiedModel);
            project.setModel(modifiedModel);
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
        copy.setDependencies(original.getDependencies());
        copy.setParent(original.getParent());
        copy.setModules(original.getModules());
        copy.setProperties(original.getProperties());
        copy.setBuild(original.getBuild());
        copy.setProfiles(original.getProfiles());
        return copy;
    }

    private void processDependencies(Model model) throws MojoExecutionException {
        List<Dependency> originalDependencies = new ArrayList<>(model.getDependencies());
        List<Dependency> newDependencies = new ArrayList<>();

        // 收集所有可用的依赖管理信息（包括当前项目和父项目）
        List<DependencyManagement> allDependencyManagements = collectAllDependencyManagements(project);

        for (Dependency dep : originalDependencies) {
            String depId = dep.getGroupId() + ":" + dep.getArtifactId();
            boolean isReplaced = false;

            for (ReplacementRule rule : replacementRules) {
                if (rule.getSourceDepId().equals(depId)) {
                    getLog().info("Replacing dependency: " + depId);
                    String[] parts = rule.getTargetDepId().split(":");

                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Invalid dependency format: " + rule.getTargetDepId() +
                                ". Use groupId:artifactId[:version]");
                    }

                    Dependency newDep = new Dependency();
                    newDep.setGroupId(parts[0]);
                    newDep.setArtifactId(parts[1]);

                    // 处理版本
                    if (parts.length >= 3) {
                        newDep.setVersion(parts[2]);
                    } else {
                        // 从所有可用的依赖管理中查找版本
                        String managedVersion = findVersionInAllDependencyManagements(
                                allDependencyManagements, parts[0], parts[1]);

                        if (managedVersion != null) {
                            newDep.setVersion(managedVersion);
                            getLog().info("Using version " + managedVersion + " from dependency management");
                        } else {
                            // 通过Aether解析版本
                            try {
                                String resolvedVersion = resolveVersionFromRepositories(parts[0], parts[1]);
                                newDep.setVersion(resolvedVersion);
                                getLog().info("Resolved version " + resolvedVersion + " from repositories");
                            } catch (Exception e) {
                                getLog().warn("Could not resolve version for " + parts[0] + ":" + parts[1] +
                                        ", using original version");
                                newDep.setVersion(dep.getVersion());
                            }
                        }
                    }

                    // 处理其他属性
                    newDep.setScope(dep.getScope());
                    newDep.setExclusions(new ArrayList<>(dep.getExclusions()));
                    newDep.setClassifier(dep.getClassifier());

                    newDependencies.add(newDep);
                    isReplaced = true;
                    break;
                }
            }

            if (!isReplaced) {
                newDependencies.add(dep);
            }
        }

        // 去重处理
        Map<String, Dependency> uniqueDependencies = new HashMap<>();
        for (Dependency dep : newDependencies) {
            String depId = dep.getGroupId() + ":" + dep.getArtifactId() +
                    (dep.getClassifier() != null ? ":" + dep.getClassifier() : "") +
                    (dep.getScope() != null ? ":" + dep.getScope() : "");
            if (!uniqueDependencies.containsKey(depId)) {
                uniqueDependencies.put(depId, dep);
            } else {
                getLog().warn("Duplicate dependency found and skipped: " + depId);
            }
        }

        model.setDependencies(new ArrayList<>(uniqueDependencies.values()));
    }

    /**
     * 收集当前项目及所有父项目的dependencyManagement
     */
    private List<DependencyManagement> collectAllDependencyManagements(MavenProject project) {
        List<DependencyManagement> result = new ArrayList<>();
        MavenProject current = project;

        while (current != null) {
            DependencyManagement dm = current.getModel().getDependencyManagement();
            if (dm != null) {
                result.add(dm);
            }
            current = current.getParent();
        }

        return result;
    }

    /**
     * 从所有收集到的dependencyManagement中查找版本
     */
    private String findVersionInAllDependencyManagements(List<DependencyManagement> managements,
                                                         String groupId, String artifactId) {
        for (DependencyManagement dm : managements) {
            if (dm.getDependencies() != null) {
                for (Dependency dep : dm.getDependencies()) {
                    if (groupId.equals(dep.getGroupId()) && artifactId.equals(dep.getArtifactId())) {
                        return dep.getVersion();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 通过Aether从仓库解析版本
     */
    private String resolveVersionFromRepositories(String groupId, String artifactId)
            throws VersionRangeResolutionException {
        org.eclipse.aether.artifact.Artifact artifact = new DefaultArtifact(
                groupId, artifactId, null, "[0,)");

        VersionRangeRequest rangeRequest = new VersionRangeRequest();
        rangeRequest.setArtifact(artifact);
        rangeRequest.setRepositories(repositorySystem.newResolutionRepositories(
                getRepositorySession(), remoteRepositories));

        VersionRangeResult rangeResult = repositorySystem.resolveVersionRange(
                getRepositorySession(), rangeRequest);

        Version highestVersion = rangeResult.getHighestVersion();
        if (highestVersion != null) {
            return highestVersion.toString();
        }

        throw new VersionRangeResolutionException(rangeResult,
                "Could not resolve version for " + groupId + ":" + artifactId);
    }

    private void saveModifiedPom(Model model) throws IOException {
        if (!outputPomFile.getParentFile().exists()) {
            outputPomFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(outputPomFile)) {
            DefaultModelWriter modelWriter = new DefaultModelWriter();
            modelWriter.write(writer, null, model);
        }
    }
}
