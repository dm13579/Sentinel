package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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
 * 热点参数推送
 *
 * @author dm
 * @date 2021/08/20
 */
@Component("paramRuleNacosPublisher")
public class ParamRuleNacosPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        // 推送规则到Nacos配置中心
        configService.publishConfig(app + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX, nacosProperties.groupId, ConvertUtils.entityConvertToRule(rules));
    }
}