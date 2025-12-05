package com.greendam.ai.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * <h3>国际化消息工具类</h3>
 * <p>提供获取国际化消息的静态方法，根据当前线程的区域设置返回相应的消息文本</p>
 *
 * @author ForeverGreenDam
 */
public class MessageUtil {
    /**
     * 消息源Bean，用于获取国际化消息
     */
    public static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);
    /**
     * <h3>获取国际化消息（带参数）</h3>
     * <p>根据消息代码和参数获取当前区域设置的国际化消息</p>
     *
     * @param code 消息代码，对应国际化配置文件中的键
     * @param args 消息参数数组，用于替换消息模板中的占位符
     * @return 格式化后的国际化消息文本
     */
    public static String getMessage(String code, Object[] args) {
        return MESSAGE_SOURCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }
    /**
     * <h3>获取国际化消息（无参数）</h3>
     * <p>根据消息代码获取当前区域设置的国际化消息</p>
     *
     * @param code 消息代码，对应国际化配置文件中的键
     * @return 国际化消息文本
     */
    public static String getMessage(String code) {
        return MESSAGE_SOURCE.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
