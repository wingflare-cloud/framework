package com.wingflare.maven.plugin.replacer.pom;

public class ReplacementRule {

    private String sourceDepId;
    private String targetDepId;

    public String getSourceDepId() {
        return sourceDepId;
    }

    public void setSourceDepId(String sourceDepId) {
        this.sourceDepId = sourceDepId;
    }

    public String getTargetDepId() {
        return targetDepId;
    }

    public void setTargetDepId(String targetDepId) {
        this.targetDepId = targetDepId;
    }

}
