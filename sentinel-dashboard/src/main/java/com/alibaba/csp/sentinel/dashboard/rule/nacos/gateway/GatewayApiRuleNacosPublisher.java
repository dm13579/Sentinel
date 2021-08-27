package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.dashboard.util.ConvertUtils;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关api规则纳科的出版商
 *
 * @author dm
 * @date 2021/08/27
 */
@Component("gatewayApiRuleNacosPublisher")
public class GatewayApiRuleNacosPublisher implements DynamicRulePublisher<List<ApiDefinitionEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<ApiDefinitionEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        // 推送规则到Nacos配置中心
        configService.publishConfig(app + NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX, nacosProperties.groupId, ConvertUtils.entityConvertToRule(rules));
    }
}