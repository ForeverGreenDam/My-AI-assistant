package com.greendam.ai.tools;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * <h3>日期时间工具 - Agent专用</h3>
 * <p>提供Agent使用的日期时间格式化、解析、计算等功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class DateAgentTools {

    /**
     * <h3>获取当前时间</h3>
     *
     * @return 当前时间字符串（格式: yyyy-MM-dd HH:mm:ss）
     */
    @Tool("获取当前系统时间，格式为 yyyy-MM-dd HH:mm:ss")
    public String getCurrentTime() {
        log.info("Agent获取当前时间");
        return DateUtil.now();
    }

    /**
     * <h3>获取当前日期</h3>
     *
     * @return 当前日期字符串（格式: yyyy-MM-dd）
     */
    @Tool("获取当前系统日期，格式为 yyyy-MM-dd")
    public String getCurrentDate() {
        log.info("Agent获取当前日期");
        return DateUtil.today();
    }

    /**
     * <h3>格式化日期</h3>
     *
     * @param dateStr 日期字符串（支持多种常见格式）
     * @param pattern 目标格式，例如：yyyy-MM-dd HH:mm:ss
     * @return 格式化后的日期字符串
     */
    @Tool("将日期字符串格式化为指定格式，pattern例如：yyyy-MM-dd HH:mm:ss")
    public String formatDate(String dateStr, String pattern) {
        try {
            log.info("Agent格式化日期: {} -> {}", dateStr, pattern);
            Date date = DateUtil.parse(dateStr);
            return DateUtil.format(date, pattern);
        } catch (Exception e) {
            log.error("格式化日期失败: {}", dateStr, e);
            return "格式化日期失败: " + e.getMessage();
        }
    }

    /**
     * <h3>解析日期</h3>
     *
     * @param dateStr 日期字符串
     * @return 解析后的日期对象的字符串表示
     */
    @Tool("解析日期字符串，支持多种常见格式自动识别")
    public String parseDate(String dateStr) {
        try {
            log.info("Agent解析日期: {}", dateStr);
            Date date = DateUtil.parse(dateStr);
            return DateUtil.formatDateTime(date);
        } catch (Exception e) {
            log.error("解析日期失败: {}", dateStr, e);
            return "解析日期失败: " + e.getMessage();
        }
    }

    /**
     * <h3>计算两个日期之间的天数差</h3>
     *
     * @param startDateStr 开始日期字符串
     * @param endDateStr   结束日期字符串
     * @return 天数差
     */
    @Tool("计算两个日期之间相差的天数")
    public String daysBetween(String startDateStr, String endDateStr) {
        try {
            log.info("Agent计算日期差: {} 到 {}", startDateStr, endDateStr);
            Date startDate = DateUtil.parse(startDateStr);
            Date endDate = DateUtil.parse(endDateStr);
            long days = DateUtil.between(startDate, endDate, DateUnit.DAY);
            return "两个日期相差 " + days + " 天";
        } catch (Exception e) {
            log.error("计算日期差失败", e);
            return "计算日期差失败: " + e.getMessage();
        }
    }

    /**
     * <h3>日期加减天数</h3>
     *
     * @param dateStr 原始日期字符串
     * @param days    要加减的天数（正数加，负数减）
     * @return 计算后的日期
     */
    @Tool("对日期进行加减天数操作，days为正数表示加，负数表示减")
    public String addDays(String dateStr, int days) {
        try {
            log.info("Agent日期加减: {} {} 天", dateStr, days);
            Date date = DateUtil.parse(dateStr);
            Date result = DateUtil.offsetDay(date, days);
            return DateUtil.formatDateTime(result);
        } catch (Exception e) {
            log.error("日期加减失败", e);
            return "日期加减失败: " + e.getMessage();
        }
    }

    /**
     * <h3>日期加减月数</h3>
     *
     * @param dateStr 原始日期字符串
     * @param months  要加减的月数（正数加，负数减）
     * @return 计算后的日期
     */
    @Tool("对日期进行加减月数操作，months为正数表示加，负数表示减")
    public String addMonths(String dateStr, int months) {
        try {
            log.info("Agent日期加减: {} {} 月", dateStr, months);
            Date date = DateUtil.parse(dateStr);
            Date result = DateUtil.offsetMonth(date, months);
            return DateUtil.formatDateTime(result);
        } catch (Exception e) {
            log.error("日期加减失败", e);
            return "日期加减失败: " + e.getMessage();
        }
    }

    /**
     * <h3>判断是否为同一天</h3>
     *
     * @param dateStr1 第一个日期字符串
     * @param dateStr2 第二个日期字符串
     * @return 是否为同一天
     */
    @Tool("判断两个日期是否为同一天")
    public boolean isSameDay(String dateStr1, String dateStr2) {
        try {
            log.info("Agent判断同一天: {} 和 {}", dateStr1, dateStr2);
            Date date1 = DateUtil.parse(dateStr1);
            Date date2 = DateUtil.parse(dateStr2);
            return DateUtil.isSameDay(date1, date2);
        } catch (Exception e) {
            log.error("判断同一天失败", e);
            return false;
        }
    }

    /**
     * <h3>获取当前时间戳（秒）</h3>
     *
     * @return 秒级时间戳
     */
    @Tool("获取当前时间的秒级时间戳")
    public long getCurrentTimestampSeconds() {
        log.info("Agent获取秒级时间戳");
        return System.currentTimeMillis() / 1000;
    }

    /**
     * <h3>获取当前时间戳（毫秒）</h3>
     *
     * @return 毫秒级时间戳
     */
    @Tool("获取当前时间的毫秒级时间戳")
    public long getCurrentTimestampMillis() {
        log.info("Agent获取毫秒级时间戳");
        return System.currentTimeMillis();
    }

    /**
     * <h3>时间戳转日期</h3>
     *
     * @param timestamp 时间戳（毫秒）
     * @return 格式化的日期字符串
     */
    @Tool("将毫秒级时间戳转换为日期字符串")
    public String timestampToDate(long timestamp) {
        try {
            log.info("Agent时间戳转日期: {}", timestamp);
            Date date = new Date(timestamp);
            return DateUtil.formatDateTime(date);
        } catch (Exception e) {
            log.error("时间戳转日期失败", e);
            return "时间戳转日期失败: " + e.getMessage();
        }
    }
}
