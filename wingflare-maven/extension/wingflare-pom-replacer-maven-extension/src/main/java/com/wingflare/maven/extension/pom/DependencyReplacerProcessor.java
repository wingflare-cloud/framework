package com.wingflare.maven.extension.pom;


import org.apache.maven.model.Model;
import org.apache.maven.model.building.ModelProcessor;
import org.apache.maven.model.io.ModelReader;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component(role = ModelProcessor.class, hint = "dependency-replacer")
public class DependencyReplacerProcessor implements ModelProcessor {

    @Requirement
    private ModelReader modelReader;

    private List<ReplacementRule> replacementRules;

    @Override
    public Model read(File file, Map<String, ?> options) throws IOException {
        return modelReader.read(file, options);
    }

    @Override
    public Model read(Reader reader, Map<String, ?> options) throws IOException {
        return modelReader.read(reader, options);
    }

    @Override
    public Model read(InputStream in, Map<String, ?> options) throws IOException {
        return modelReader.read(in, options);
    }

    /**
     * 从.mvn/extensions.xml加载替换规则，修复Xpp3DomBuilder参数问题
     */
    private void loadReplacements(File projectDirectory) throws IOException {
        if (replacementRules != null) {
            return;
        }

        replacementRules = new ArrayList<>();
        File extensionsFile = new File(projectDirectory, "mvn/extensions.xml");

        if (!extensionsFile.exists() || !extensionsFile.isFile()) {
            return;
        }

        // 使用try-with-resources确保流正确关闭
        try (InputStream is = new FileInputStream(extensionsFile)) {
             Xpp3Dom dom = Xpp3DomBuilder.build(is, "UTF-8");

            Xpp3Dom[] extensionDoms = dom.getChildren("extension");
            for (Xpp3Dom extensionDom : extensionDoms) {
                Xpp3Dom replacementsDom = extensionDom.getChild("dependencyReplacements");
                if (replacementsDom == null) {
                    continue;
                }

                Xpp3Dom[] replacementDoms = replacementsDom.getChildren("replacement");

                for (Xpp3Dom replacementDom : replacementDoms) {
                    ReplacementRule replacement = new ReplacementRule();
                    replacement.setSourceDepId(getChildValue(replacementDom, "sourceDepId"));
                    replacement.setTargetDepId(getChildValue(replacementDom, "targetDepId"));
                    replacementRules.add(replacement);
                }
            }
        } catch (XmlPullParserException e) {
            throw new IOException("解析extensions.xml失败: " + e.getMessage(), e);
        }
    }

    private String getChildValue(Xpp3Dom parent, String childName) {
        Xpp3Dom child = parent.getChild(childName);
        return (child != null) ? child.getValue() : null;
    }

    @Override
    public File locatePom(File projectDirectory) {

        try {
            loadReplacements();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!replacementRules.isEmpty()) {
            File targetDir = new File(projectDirectory, "target");

            if (!targetDir.exists()) {
                if (!targetDir.mkdirs()) {
                    try {
                        throw new IOException("Failed to create temp directory: " + targetDir.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                MavenPomUpdater mavenPomUpdater = new MavenPomUpdater(replacementRules);
                File modifiedPom = new File(targetDir, "modified-pom.xml");

                try {
                    mavenPomUpdater.updatePomDependencies(new File(projectDirectory, "pom.xml"), modifiedPom);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return modifiedPom;
            }
        }

        return new File(projectDirectory, "pom.xml");
    }
}