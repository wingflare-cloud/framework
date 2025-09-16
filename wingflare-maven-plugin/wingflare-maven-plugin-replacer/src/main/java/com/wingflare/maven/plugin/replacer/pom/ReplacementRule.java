package com.wingflare.maven.plugin.replacer.pom;

import java.util.ArrayList;
import java.util.List;

public class ReplacementRule {

    private List<String> sourceArtifacts = new ArrayList<>();
    private List<String> targetArtifacts = new ArrayList<>();

    public List<String> getSourceArtifacts() {
        return sourceArtifacts;
    }

    public void setSourceArtifacts(List<String> sourceArtifacts) {
        this.sourceArtifacts = sourceArtifacts;
    }

    public List<String> getTargetArtifacts() {
        return targetArtifacts;
    }

    public void setTargetArtifacts(List<String> targetArtifacts) {
        this.targetArtifacts = targetArtifacts;
    }
}
