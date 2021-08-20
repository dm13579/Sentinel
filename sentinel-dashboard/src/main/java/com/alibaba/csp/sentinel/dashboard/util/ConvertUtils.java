package com.alibaba.csp.sentinel.dashboard.util;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换工具类
 *
 * @author dm
 * @date 2021/08/20
 */
public class ConvertUtils {

    /**
     * 转换为规则
     * RuleEntity----->Rule
     *
     * @param entities 实体
     * @return {@link String}
     */
    public static String entityConvertToRule(List<? extends RuleEntity> entities) {
        return JSON.toJSONString(entities.stream().map(RuleEntity::toRule).collect(Collectors.toList()));
    }
}
