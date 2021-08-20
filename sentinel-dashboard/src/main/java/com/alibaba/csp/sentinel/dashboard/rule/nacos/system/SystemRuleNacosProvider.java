package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统规则拉取
 *
 * @author dm
 * @date 2021/08/20
 */
@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {

    @Autowired
    private NacosProperties nacosProperties;

    @Autowired
    private ConfigService configService;

    @Override
    public List<SystemRuleEntity> getRules(String appName, String ip, Integer port) throws Exception {
        // 从Nacos配置中心拉取配置
        String rules = configService.getConfig(appName + NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX, nacosProperties.groupId, nacosProperties.timeout);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        List<SystemRule> list = JSON.parseArray(rules, SystemRule.class);
        // 转化规则到SystemRuleEntity
        return list.stream().map(rule -> SystemRuleEntity.fromSystemRule(appName, ip, port, rule)).collect(Collectors.toList());
    }
}