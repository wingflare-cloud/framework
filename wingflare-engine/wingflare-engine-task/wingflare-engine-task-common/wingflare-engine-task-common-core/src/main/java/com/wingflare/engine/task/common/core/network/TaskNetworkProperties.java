package com.wingflare.engine.task.common.core.network;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 奶嘴
 * @date 2025-07-31
 */
public class TaskNetworkProperties {

    /**
     * 优先使用的网络段列表，
     * 支持CIDR表示法，如：192.168.1.0/16
     * 支持前缀匹配: 192、192.168 等
     * 支持正则匹配: 192.168.*.* 等价于 192.168.1.0/16
     *
     */
    private List<String> preferredNetworks;

    /**
     * 是否优先使用IPv4地址
     */
    private boolean preferIpv4 = true;

    /**
     * 是否优先私有地址
     * 如：
     * 10.0.0.0/8
     * 172.16.0.0/12
     * 192.168.0.0/16
     */
    private boolean preferSiteLocalAddress = true;

    /**
     * 忽略网卡信息
     */
    private List<String> ignoredInterfaces = new ArrayList<>();


    public List<String> getPreferredNetworks() {
        return preferredNetworks;
    }

    public void setPreferredNetworks(List<String> preferredNetworks) {
        this.preferredNetworks = preferredNetworks;
    }

    public boolean isPreferIpv4() {
        return preferIpv4;
    }

    public void setPreferIpv4(boolean preferIpv4) {
        this.preferIpv4 = preferIpv4;
    }

    public boolean isPreferSiteLocalAddress() {
        return preferSiteLocalAddress;
    }

    public void setPreferSiteLocalAddress(boolean preferSiteLocalAddress) {
        this.preferSiteLocalAddress = preferSiteLocalAddress;
    }

    public List<String> getIgnoredInterfaces() {
        return ignoredInterfaces;
    }

    public void setIgnoredInterfaces(List<String> ignoredInterfaces) {
        this.ignoredInterfaces = ignoredInterfaces;
    }
}
