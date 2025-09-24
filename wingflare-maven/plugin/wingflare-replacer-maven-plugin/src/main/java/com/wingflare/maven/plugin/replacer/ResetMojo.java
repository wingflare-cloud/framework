package com.wingflare.maven.plugin.replacer;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;


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
            if (newPomFile.exists() && newPomFile.delete()) {
                backPomFile.renameTo(new File(baseDir, "pom.xml"));
            }
        }
    }

}
