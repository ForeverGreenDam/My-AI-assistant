package com.greendam.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * <h3>国际化配置类</h3>
 * <p>配置Spring MVC的国际化相关Bean，包括区域解析器</p>
 *
 * @author ForeverGreenDam
 */
@Configuration
public class I18nConfig {
    /**
     * <h3>配置区域解析器Bean</h3>
     * <p>注册自定义的LocaleResolverHandler作为Spring的区域解析器</p>
     * <p>该解析器会根据请求头中的Area字段来确定客户端的区域设置</p>
     *
     * @return LocaleResolver实例
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new LocaleResolverHandler();
    }
}
