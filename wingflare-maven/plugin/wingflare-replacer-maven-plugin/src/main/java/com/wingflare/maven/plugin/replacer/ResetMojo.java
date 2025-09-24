package com.wingflare.maven.plugin.replacer;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Mojo(name = "reset", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class ResetMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {
        File baseDir = project.getFile().getParentFile();
        File backPomFile = new File(baseDir, "pom.xml.back");
        File newPomFile = new File(baseDir, "pom.xml");

        if (backPomFile.exists()) {
            newPomFile.deleteOnExit();
            try {
                copySourceToTempDirectory(backPomFile, new File(baseDir, "pom.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

}
