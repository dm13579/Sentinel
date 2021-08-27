/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
public final class NacosConfigUtil {
    // 流控
    public static final String FLOW_DATA_ID_POSTFIX = "-sentinel-flow";
    // 降级
    public static final String DEGRADE_DATA_ID_POSTFIX = "-sentinel-degrade";
    // 系统
    public static final String SYSTEM_DATA_ID_POSTFIX = "-sentinel-system";
    // 授权
    public static final String AUTHORITY_DATA_ID_POSTFIX = "-sentinel-authority";
    // 热点参数
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "-sentinel-param";

    public static final String GATEWAY_API_DATA_ID_POSTFIX = "-sentinel-param";
    public static final String GATEWAY_FLOW_DATA_ID_POSTFIX = "-sentinel-param";



    private NacosConfigUtil() {
    }
}
