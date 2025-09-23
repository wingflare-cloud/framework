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

    public MavenPomUpdater(List<ReplacementRule> rules) {
        this.rules = rules;
    }

    /**
     * 更新POM文件中的依赖
     *
     * @param inputPom 输入POM文件
     * @param outputPom 输出POM文件路径
     * @throws Exception 处理过程中可能出现的异常
     */
    public void updatePomDependencies(File inputPom, File outputPom) throws Exception {
        if (rules == null || rules.isEmpty()) {
            return;
        }

        // 解析输入的POM文件
        Document doc = parsePomFile(inputPom);
        // 处理依赖替换
        processDependencies(doc);
        // 保存修改后的POM文件
        savePomFile(doc, outputPom);
    }

    /**
     * 解析POM文件为Document对象
     */
    private Document parsePomFile(File inputPom) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // 启用命名空间支持
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(inputPom);
    }

    /**
     * 处理POM文件中的依赖
     */
    private void processDependencies(Document doc) {
        Element root = doc.getDocumentElement();

        // 首先处理根级别的dependencies
        Set<String> rootDependencies = new HashSet<>();
        NodeList dependenciesNodes = root.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependencies");

        if (dependenciesNodes.getLength() > 0) {
            Element dependenciesElement = (Element) dependenciesNodes.item(0);
            rootDependencies = processDependencyList(doc, dependenciesElement);
        }

        // 处理profiles中的依赖
        processProfiles(doc, root, rootDependencies);
    }

    /**
     * 处理profiles中的依赖
     */
    private void processProfiles(Document doc, Element root, Set<String> rootDependencies) {
        NodeList profilesNodes = root.getElementsByTagNameNS(MAVEN_NAMESPACE, "profiles");

        if (profilesNodes.getLength() > 0) {
            Element profilesElement = (Element) profilesNodes.item(0);
            NodeList profileList = profilesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "profile");

            // 收集所有需要保留的profile
            List<Element> profilesToKeep = new ArrayList<>();

            for (int i = 0; i < profileList.getLength(); i++) {
                Element profile = (Element) profileList.item(i);
                NodeList profileDependenciesNodes = profile.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependencies");

                boolean profileHasContent = true;

                if (profileDependenciesNodes.getLength() > 0) {
                    Element profileDependencies = (Element) profileDependenciesNodes.item(0);
                    processProfileDependencyList(doc, profileDependencies, rootDependencies);

                    // 如果profile中的dependencies为空，移除它
                    if (profileDependencies.getChildNodes().getLength() == 0) {
                        profile.removeChild(profileDependencies);

                        // 检查profile是否还有其他内容
                        profileHasContent = hasSignificantContent(profile);
                    }
                }

                if (profileHasContent) {
                    profilesToKeep.add(profile);
                }
            }

            // 清除现有profiles，添加需要保留的
            while (profilesElement.hasChildNodes()) {
                profilesElement.removeChild(profilesElement.getFirstChild());
            }

            for (Element profile : profilesToKeep) {
                profilesElement.appendChild(profile);
            }

            // 如果profiles为空，移除它
            if (profilesElement.getChildNodes().getLength() == 0) {
                root.removeChild(profilesElement);
            }
        }
    }

    /**
     * 检查元素是否有重要内容（非空白文本节点）
     */
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

    /**
     * 处理依赖列表，替换并去重
     */
    private Set<String> processDependencyList(Document doc, Element dependenciesElement) {
        Set<String> dependencyGavs = new HashSet<>();
        NodeList dependencyNodes = dependenciesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependency");

        // 使用临时列表存储要处理的节点，避免在迭代时修改DOM
        List<Element> dependencies = new ArrayList<>();
        for (int i = 0; i < dependencyNodes.getLength(); i++) {
            dependencies.add((Element) dependencyNodes.item(i));
        }

        // 先移除所有依赖
        for (Element dependency : dependencies) {
            dependenciesElement.removeChild(dependency);
        }

        // 处理并添加去重后的依赖
        for (Element dependency : dependencies) {
            // 深克隆依赖节点，避免修改原始节点
            Element clonedDependency = (Element) dependency.cloneNode(true);

            // 获取当前依赖的GAV组件
            String groupId = getChildElementText(clonedDependency, "groupId");
            String artifactId = getChildElementText(clonedDependency, "artifactId");
            String version = getChildElementText(clonedDependency, "version");

            if (groupId == null || artifactId == null) {
                // 无效的依赖，缺少groupId或artifactId，跳过
                continue;
            }

            // 生成用于匹配的GAV字符串（可能没有版本号）
            String oldGav = createGavString(groupId, artifactId, version);

            // 检查是否需要替换
            String newGav = findMatchingMapping(oldGav, groupId, artifactId);
            if (newGav != null) {
                // 解析新的GAV
                String[] newParts = newGav.split(":", 3);
                String newGroupId = newParts[0];
                String newArtifactId = newParts.length > 1 ? newParts[1] : artifactId;
                String newVersion = newParts.length > 2 ? newParts[2] : null;

                // 更新依赖信息
                setChildElementText(doc, clonedDependency, "groupId", newGroupId);
                setChildElementText(doc, clonedDependency, "artifactId", newArtifactId);

                // 如果新版本为null，则移除version元素
                if (newVersion == null) {
                    removeChildElement(clonedDependency, "version");
                } else {
                    setChildElementText(doc, clonedDependency, "version", newVersion);
                }
            }

            // 获取更新后的GAV组件
            String newGroupId = getChildElementText(clonedDependency, "groupId");
            String newArtifactId = getChildElementText(clonedDependency, "artifactId");
            String newVersion = getChildElementText(clonedDependency, "version");

            // 生成新的GAV字符串用于去重
            String normalizedGav = createGavString(newGroupId, newArtifactId, newVersion);

            // 检查是否已存在，如果不存在则添加
            if (!dependencyGavs.contains(normalizedGav)) {
                dependencyGavs.add(normalizedGav);
                dependenciesElement.appendChild(clonedDependency);
            }
        }

        return dependencyGavs;
    }

    /**
     * 处理profile中的依赖列表
     */
    private void processProfileDependencyList(Document doc, Element dependenciesElement, Set<String> rootDependencies) {
        NodeList dependencyNodes = dependenciesElement.getElementsByTagNameNS(MAVEN_NAMESPACE, "dependency");

        // 使用临时列表存储要处理的节点
        List<Element> dependencies = new ArrayList<>();
        for (int i = 0; i < dependencyNodes.getLength(); i++) {
            dependencies.add((Element) dependencyNodes.item(i));
        }

        // 先移除所有依赖
        for (Element dependency : dependencies) {
            dependenciesElement.removeChild(dependency);
        }

        // 处理并添加去重后的依赖
        for (Element dependency : dependencies) {
            // 深克隆依赖节点
            Element clonedDependency = (Element) dependency.cloneNode(true);

            String groupId = getChildElementText(clonedDependency, "groupId");
            String artifactId = getChildElementText(clonedDependency, "artifactId");
            String version = getChildElementText(clonedDependency, "version");

            if (groupId == null || artifactId == null) {
                // 无效的依赖，跳过
                continue;
            }

            // 生成用于匹配的GAV字符串
            String oldGav = createGavString(groupId, artifactId, version);

            // 检查是否需要替换
            String newGav = findMatchingMapping(oldGav, groupId, artifactId);
            if (newGav != null) {
                // 解析新的GAV
                String[] newParts = newGav.split(":", 3);
                String newGroupId = newParts[0];
                String newArtifactId = newParts.length > 1 ? newParts[1] : artifactId;
                String newVersion = newParts.length > 2 ? newParts[2] : null;

                // 更新依赖信息
                setChildElementText(doc, clonedDependency, "groupId", newGroupId);
                setChildElementText(doc, clonedDependency, "artifactId", newArtifactId);

                // 如果新版本为null，则移除version元素
                if (newVersion == null) {
                    removeChildElement(clonedDependency, "version");
                } else {
                    setChildElementText(doc, clonedDependency, "version", newVersion);
                }
            }

            // 获取更新后的GAV组件
            String newGroupId = getChildElementText(clonedDependency, "groupId");
            String newArtifactId = getChildElementText(clonedDependency, "artifactId");
            String newVersion = getChildElementText(clonedDependency, "version");

            // 生成新的GAV字符串
            String normalizedGav = createGavString(newGroupId, newArtifactId, newVersion);

            // 检查是否在根依赖中已存在，如果不存在则添加
            if (!rootDependencies.contains(normalizedGav)) {
                dependenciesElement.appendChild(clonedDependency);
            }
        }
    }

    /**
     * 创建GAV字符串，处理版本号可能不存在的情况
     */
    private String createGavString(String groupId, String artifactId, String version) {
        StringBuilder sb = new StringBuilder();
        sb.append(groupId).append(":").append(artifactId);
        if (version != null && !version.trim().isEmpty()) {
            sb.append(":").append(version);
        }
        return sb.toString();
    }

    /**
     * 查找匹配的映射，考虑版本号可能不存在的情况
     */
    private String findMatchingMapping(String gavWithVersion, String groupId, String artifactId) {

        Map<String, String> mappings = new HashMap<>();

        rules.forEach((rule) -> {
            mappings.put(rule.getSourceDepId(), rule.getTargetDepId());
        });

        // 首先尝试精确匹配（包含版本号）
        if (mappings.containsKey(gavWithVersion)) {
            return mappings.get(gavWithVersion);
        }

        // 如果没有精确匹配，尝试匹配不带版本号的形式
        String gavWithoutVersion = groupId + ":" + artifactId;

        if (mappings.containsKey(gavWithoutVersion)) {
            return mappings.get(gavWithoutVersion);
        }

        return null;
    }

    /**
     * 移除子元素
     */
    private void removeChildElement(Element parent, String childName) {
        NodeList nodes = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        if (nodes.getLength() > 0) {
            parent.removeChild(nodes.item(0));
        }
    }

    /**
     * 获取子元素的文本内容
     */
    private String getChildElementText(Element parent, String childName) {
        NodeList nodes = parent.getElementsByTagNameNS(MAVEN_NAMESPACE, childName);
        if (nodes.getLength() > 0) {
            String text = nodes.item(0).getTextContent();
            return text != null && !text.trim().isEmpty() ? text : null;
        }
        return null;
    }

    /**
     * 设置子元素的文本内容，如果子元素不存在则创建
     */
    private void setChildElementText(Document doc, Element parent, String childName, String text) {
        if (text == null || text.trim().isEmpty()) {
            // 如果文本为空，移除该元素
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

    /**
     * 保存Document对象到文件
     */
    private void savePomFile(Document doc, File outputPom) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputPom);
        transformer.transform(source, result);
    }

}