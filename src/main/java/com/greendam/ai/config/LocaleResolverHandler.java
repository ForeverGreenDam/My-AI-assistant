package com.greendam.ai.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * <h3>国际化区域解析器</h3>
 * <p>实现LocaleResolver接口，根据HTTP请求头中的Area字段解析客户端的区域设置</p>
 * <p>Area字段格式：语言_国家，例如：zh_CN, en_US</p>
 *
 * @author ForeverGreenDam
 */
public class LocaleResolverHandler implements LocaleResolver {
    /**
     * <h3>解析请求的区域设置</h3>
     * <p>从请求头的Area字段解析区域信息，格式为"语言_国家"（如zh_CN）</p>
     * <p>如果Area字段不存在或格式不正确，则返回系统默认区域</p>
     *
     * @param request HTTP请求对象
     * @return 解析后的Locale对象，如果解析失败则返回系统默认区域
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String area = request.getHeader("Area");
        Locale defaultLocale = Locale.getDefault();
        if(area==null){
            return defaultLocale;
        }
        String[] args = area.split("_");
        if (args.length < 2){
            return defaultLocale;
        }
        String language = args[0];
        String country = args[1];
        return Locale.of(language, country);
    }

    /**
     * <h3>设置区域（未实现）</h3>
     * <p>本应用不支持在代码中动态设置区域，区域信息仅通过请求头传递</p>
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param locale 要设置的区域对象（未使用）
     */
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        //不在代码中设置区域
    }

}
