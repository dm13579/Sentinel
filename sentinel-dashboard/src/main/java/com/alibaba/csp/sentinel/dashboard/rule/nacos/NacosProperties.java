package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("sentinel.nacos.config")
@Component
public class NacosProperties {

    /**
     * 同步/推送nacos地址
     */
    public String serverAddr = "localhost:8848";

    /**
     * 同步/推送nacos地址
     */
    public int timeout = 3000;

    /**
     * sentinel配置在nacos上分组
     */
    public String groupId = "SENTINEL";

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
