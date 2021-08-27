package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 提供者网关流动法则纳科
 *
 * @author dm
 * @date 2021/08/27
 */
@Component("gatewayFlowRuleNacosProvider")
public class GatewayFlowRuleNacosProvider implements DynamicRuleProvider<List<GatewayFlowRuleEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public List<GatewayFlowRuleEntity> getRules(String appName, String ip, Integer port) throws Exception {
        // 从Nacos配置中心拉取配置
        String rules = configService.getConfig(appName + NacosConfigUtil.GATEWAY_FLOW_DATA_ID_POSTFIX, nacosProperties.groupId, nacosProperties.timeout);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        List<GatewayFlowRule> list = JSON.parseArray(rules, GatewayFlowRule.class);
        // 转化规则到GatewayFlowRule
        return list.stream().map(rule -> GatewayFlowRuleEntity.fromGatewayFlowRule(appName, ip, port, rule)).collect(Collectors.toList());
    }
}