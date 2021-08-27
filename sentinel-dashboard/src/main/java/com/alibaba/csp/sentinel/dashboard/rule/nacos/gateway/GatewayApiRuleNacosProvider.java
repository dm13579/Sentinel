package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
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
 * 网关api规则纳科提供者
 *
 * @author dm
 * @date 2021/08/27
 */
@Component("gatewayApiRuleNacosProvider")
public class GatewayApiRuleNacosProvider implements DynamicRuleProvider<List<ApiDefinitionEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public List<ApiDefinitionEntity> getRules(String appName, String ip, Integer port) throws Exception {
        // 从Nacos配置中心拉取配置
        String rules = configService.getConfig(appName + NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX, nacosProperties.groupId, nacosProperties.timeout);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        List<ApiDefinition> list = JSON.parseArray(rules, ApiDefinition.class);
        // 转化规则到ApiDefinition
        return list.stream().map(rule -> ApiDefinitionEntity.fromApiDefinition(appName, ip, port, rule)).collect(Collectors.toList());
    }
}