package com.wingflare.maven.plugin.replacer.annotation;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MavenPomUpdater {

    // Maven POM的XML命名空间
    private static final String MAVEN_NAMESPACE = "http://maven.apache.org/POM/4.0.0";

    private final List<ReplacementRule> rules;
    // 新增：存储自定义的sourceDirectory路径（默认null，不修改）
    private String sourceDirectory;

    // 原有构造函数
    public MavenPomUpdater(List<ReplacementRule> rules) {
        this.rules = rules;
    }

    // 新增：sourceDirectory的Setter方法（外部调用时设置自定义路径）
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    /**
     * 更新POM文件中的依赖 + 新增：更新sourceDirectory
     *
     * @param inputPom  输入POM文件
     * @throws Exception 处理过程中可能出现的异常
     */
    public void updatePomDependencies(File inputPom) throws Exception {
        if (rules == null || rules.isEmpty()) {
            // 即使无依赖规则，若有sourceDirectory配置仍需处理
        }

        // 1. 解析输入的POM文件
        Document doc = parsePomFile(inputPom);
        // 2. 处理依赖替换（原有逻辑）
        processDependencies(doc);
        // 新增：3. 处理sourceDirectory（若有配置则执行）
        if (sourceDirectory != null && !sourceDirectory.trim().isEmpty()) {
            processSourceDirectory(doc);
        }
        // 4. 保存修改后的POM文件

        File targetDir = inputPom.getParentFile();
        inputPom.renameTo(new File(targetDir, "pom.xml.back"));

        savePomFile(doc, new File(targetDir, "pom.xml"));
    }


    /**
     * 处理POM中的sourceDirectory：查找/创建<build>节点，设置自定义sourceDirectory
     * @param doc 解析后的POM DOM对象
     */
    private void processSourceDirectory(Document doc) {
        Element root = doc.getDocumentElement();

        // 步骤1：查找或创建<build>节点（Maven的build节点在根元素下）
        Element buildElement = getOrCreateChildElement(doc, root, "build");
        // 步骤2：查找或创建<sourceDirectory>节点，设置自定义路径
        setChildElementText(doc, buildElement, "sourceDirectory", sourceDirectory.trim());
    }

    /**
     * 新增：查找或创建指定名称的子元素（复用逻辑，避免重复代码）
     * @param doc  DOM对象（用于创建新元素）
     * @param parent 父元素
     * @param childName 子元素名称
     * @return 已存在或新创建的子元素
     */
    private Element getOrCreateChildElement(Document doc, Element parent, String childName) {
        NodeList nodeList = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        if (nodeList.getLength() > 0) {
            // 若子元素已存在，返回第一个（Maven中build/sourceDirectory通常唯一）
            return (Element) nodeList.item(0);
        } else {
            // 若子元素不存在，创建新元素并添加到父元素
            Element newChild = doc.createElementNS(MAVEN_NAMESPACE, childName);
            parent.appendChild(newChild);
            return newChild;
        }
    }

    private Document parsePomFile(File inputPom) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // 启用命名空间支持
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(inputPom);
    }

    private void processDependencies(Document doc) {
        Element root = doc.getDocumentElement();

        // 处理根级别的dependencies
        Set<String> rootDependencies = new HashSet<>();
        NodeList dependenciesNodes = root.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependencies");

        if (dependenciesNodes.getLength() > 0) {
            Element dependenciesElement = (Element) dependenciesNodes.item(0);
            rootDependencies = processDependencyList(doc, dependenciesElement);
        }

        // 处理profiles中的依赖
        processProfiles(doc, root, rootDependencies);
    }

    private void processProfiles(Document doc, Element root, Set<String> rootDependencies) {
        NodeList profilesNodes = root.getElementsByTagNameNS(MAVEN_NAMESPACE, "profiles");

        if (profilesNodes.getLength() > 0) {
            Element profilesElement = (Element) profilesNodes.item(0);
            NodeList profileList = profilesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "profile");

            List<Element> profilesToKeep = new ArrayList<>();

            for (int i = 0; i < profileList.getLength(); i++) {
                Element profile = (Element) profileList.item(i);
                NodeList profileDependenciesNodes = profile.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependencies");

                boolean profileHasContent = true;

                if (profileDependenciesNodes.getLength() > 0) {
                    Element profileDependencies = (Element) profileDependenciesNodes.item(0);
                    processProfileDependencyList(doc, profileDependencies, rootDependencies);

                    if (profileDependencies.getChildNodes().getLength() == 0) {
                        profile.removeChild(profileDependencies);
                        profileHasContent = hasSignificantContent(profile);
                    }
                }

                if (profileHasContent) {
                    profilesToKeep.add(profile);
                }
            }

            while (profilesElement.hasChildNodes()) {
                profilesElement.removeChild(profilesElement.getFirstChild());
            }

            for (Element profile : profilesToKeep) {
                profilesElement.appendChild(profile);
            }

            if (profilesElement.getChildNodes().getLength() == 0) {
                root.removeChild(profilesElement);
            }
        }
    }

    private boolean hasSignificantContent(Element element) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
            if (node.getNodeType() == Node.TEXT_NODE &&
                    !node.getTextContent().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Set<String> processDependencyList(Document doc, Element dependenciesElement) {
        Set<String> dependencyGavs = new HashSet<>();
        NodeList dependencyNodes = dependenciesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependency");

        List<Element> dependencies = new ArrayList<>();
        for (int i = 0; i < dependencyNodes.getLength(); i++) {
            dependencies.add((Element) dependencyNodes.item(i));
        }

        for (Element dependency : dependencies) {
            dependenciesElement.removeChild(dependency);
        }

        for (Element dependency : dependencies) {
            Element clonedDependency = (Element) dependency.cloneNode(true);

            String groupId = getChildElementText(clonedDependency, "groupId");
            String artifactId = getChildElementText(clonedDependency, "artifactId");
            String version = getChildElementText(clonedDependency, "version");

            if (groupId == null || artifactId == null) {
                continue;
            }

            String oldGav = createGavString(groupId, artifactId, version);
            String newGav = findMatchingMapping(oldGav, groupId, artifactId);

            if (newGav != null) {
                String[] newParts = newGav.split(":", 3);
                String newGroupId = newParts[0];
                String newArtifactId = newParts.length > 1 ? newParts[1] : artifactId;
                String newVersion = newParts.length > 2 ? newParts[2] : null;

                setChildElementText(doc, clonedDependency, "groupId", newGroupId);
                setChildElementText(doc, clonedDependency, "artifactId", newArtifactId);

                if (newVersion == null) {
                    removeChildElement(clonedDependency, "version");
                } else {
                    setChildElementText(doc, clonedDependency, "version", newVersion);
                }
            }

            String newGroupId = getChildElementText(clonedDependency, "groupId");
            String newArtifactId = getChildElementText(clonedDependency, "artifactId");
            String newVersion = getChildElementText(clonedDependency, "version");

            String normalizedGav = createGavString(newGroupId, newArtifactId, newVersion);

            if (!dependencyGavs.contains(normalizedGav)) {
                dependencyGavs.add(normalizedGav);
                dependenciesElement.appendChild(clonedDependency);
            }
        }

        return dependencyGavs;
    }

    private void processProfileDependencyList(Document doc, Element dependenciesElement, Set<String> rootDependencies) {
        NodeList dependencyNodes = dependenciesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependency");

        List<Element> dependencies = new ArrayList<>();
        for (int i = 0; i < dependencyNodes.getLength(); i++) {
            dependencies.add((Element) dependencyNodes.item(i));
        }

        for (Element dependency : dependencies) {
            dependenciesElement.removeChild(dependency);
        }

        for (Element dependency : dependencies) {
            Element clonedDependency = (Element) dependency.cloneNode(true);

            String groupId = getChildElementText(clonedDependency, "groupId");
            String artifactId = getChildElementText(clonedDependency, "artifactId");
            String version = getChildElementText(clonedDependency, "version");

            if (groupId == null || artifactId == null) {
                continue;
            }

            String oldGav = createGavString(groupId, artifactId, version);
            String newGav = findMatchingMapping(oldGav, groupId, artifactId);

            if (newGav != null) {
                String[] newParts = newGav.split(":", 3);
                String newGroupId = newParts[0];
                String newArtifactId = newParts.length > 1 ? newParts[1] : artifactId;
                String newVersion = newParts.length > 2 ? newParts[2] : null;

                setChildElementText(doc, clonedDependency, "groupId", newGroupId);
                setChildElementText(doc, clonedDependency, "artifactId", newArtifactId);

                if (newVersion == null) {
                    removeChildElement(clonedDependency, "version");
                } else {
                    setChildElementText(doc, clonedDependency, "version", newVersion);
                }
            }

            String newGroupId = getChildElementText(clonedDependency, "groupId");
            String newArtifactId = getChildElementText(clonedDependency, "artifactId");
            String newVersion = getChildElementText(clonedDependency, "version");

            String normalizedGav = createGavString(newGroupId, newArtifactId, newVersion);

            if (!rootDependencies.contains(normalizedGav)) {
                dependenciesElement.appendChild(clonedDependency);
            }
        }
    }

    private String createGavString(String groupId, String artifactId, String version) {
        StringBuilder sb = new StringBuilder();
        sb.append(groupId).append(":").append(artifactId);
        if (version != null && !version.trim().isEmpty()) {
            sb.append(":").append(version);
        }
        return sb.toString();
    }

    private String findMatchingMapping(String gavWithVersion, String groupId, String artifactId) {
        Map<String, String> mappings = new HashMap<>();
        rules.forEach((rule) -> mappings.put(rule.getSourceDepId(), rule.getTargetDepId()));

        if (mappings.containsKey(gavWithVersion)) {
            return mappings.get(gavWithVersion);
        }

        String gavWithoutVersion = groupId + ":" + artifactId;
        if (mappings.containsKey(gavWithoutVersion)) {
            return mappings.get(gavWithoutVersion);
        }

        return null;
    }

    private void removeChildElement(Element parent, String childName) {
        NodeList nodes = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        if (nodes.getLength() > 0) {
            parent.removeChild(nodes.item(0));
        }
    }

    private String getChildElementText(Element parent, String childName) {
        NodeList nodes = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        if (nodes.getLength() > 0) {
            String text = nodes.item(0).getTextContent();
            return text != null && !text.trim().isEmpty() ? text : null;
        }
        return null;
    }

    private void setChildElementText(Document doc, Element parent, String childName, String text) {
        if (text == null || text.trim().isEmpty()) {
            removeChildElement(parent, childName);
            return;
        }

        NodeList nodes = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        Element child;

        if (nodes.getLength() > 0) {
            child = (Element) nodes.item(0);
        } else {
            child = doc.createElementNS(MAVEN_NAMESPACE, childName);
            parent.appendChild(child);
        }

        child.setTextContent(text);
    }

    private void savePomFile(Document doc, File outputPom) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputPom);
        transformer.transform(source, result);
    }

}