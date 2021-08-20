package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 热点参数规则拉取
 *
 * @author dm
 * @date 2021/08/20
 */
@Component("paramRuleNacosProvider")
public class ParamRuleNacosProvider implements DynamicRuleProvider<List<ParamFlowRuleEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public List<ParamFlowRuleEntity> getRules(String appName, String ip, Integer port) throws Exception {
        // 从Nacos配置中心拉取配置
        String rules = configService.getConfig(appName + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX, nacosProperties.groupId, nacosProperties.timeout);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        List<ParamFlowRule> list = JSON.parseArray(rules, ParamFlowRule.class);
        // 转化规则到ParamFlowRuleEntity
        return list.stream().map(rule -> ParamFlowRuleEntity.fromParamFlowRule(appName, ip, port, rule)).collect(Collectors.toList());
    }
}