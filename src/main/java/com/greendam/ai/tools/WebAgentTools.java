package com.greendam.ai.tools;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <h3>网络请求和搜索工具 - Agent专用</h3>
 * <p>提供Agent使用的HTTP请求和网页搜索功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class WebAgentTools {

    /**
     * 默认连接超时时间（毫秒）
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;

    /**
     * 默认读取超时时间（毫秒）
     */
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    /**
     * <h3>发送GET请求</h3>
     *
     * @param url 请求URL
     * @return 响应内容
     */
    @Tool("发送GET请求到指定URL，返回响应内容")
    public String httpGet(String url) {
        return httpGet(url, null);
    }

    /**
     * <h3>发送GET请求（带参数）</h3>
     *
     * @param url    请求URL
     * @param params 请求参数（JSON格式）
     * @return 响应内容
     */
    @Tool("发送GET请求到指定URL，params为JSON格式的请求参数")
    public String httpGet(String url, String params) {
        try {
            log.info("Agent发送GET请求: {}", url);
            HttpRequest request = HttpRequest.get(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT);

            if (params != null && !params.isEmpty()) {
                request.body(params);
            }

            HttpResponse response = request.execute();
            log.info("GET请求成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("GET请求失败: {}", url, e);
            return "请求失败: " + e.getMessage();
        }
    }

    /**
     * <h3>发送POST请求</h3>
     *
     * @param url  请求URL
     * @param body 请求体（JSON格式）
     * @return 响应内容
     */
    @Tool("发送POST请求到指定URL，body为JSON格式的请求体")
    public String httpPost(String url, String body) {
        try {
            log.info("Agent发送POST请求: {}", url);
            HttpRequest request = HttpRequest.post(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT)
                    .body(body);

            HttpResponse response = request.execute();
            log.info("POST请求成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("POST请求失败: {}", url, e);
            return "请求失败: " + e.getMessage();
        }
    }

    /**
     * <h3>发送PUT请求</h3>
     *
     * @param url  请求URL
     * @param body 请求体（JSON格式）
     * @return 响应内容
     */
    @Tool("发送PUT请求到指定URL，body为JSON格式的请求体")
    public String httpPut(String url, String body) {
        try {
            log.info("Agent发送PUT请求: {}", url);
            HttpRequest request = HttpRequest.put(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT)
                    .body(body);

            HttpResponse response = request.execute();
            log.info("PUT请求成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("PUT请求失败: {}", url, e);
            return "请求失败: " + e.getMessage();
        }
    }

    /**
     * <h3>发送DELETE请求</h3>
     *
     * @param url 请求URL
     * @return 响应内容
     */
    @Tool("发送DELETE请求到指定URL")
    public String httpDelete(String url) {
        try {
            log.info("Agent发送DELETE请求: {}", url);
            HttpRequest request = HttpRequest.delete(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT);

            HttpResponse response = request.execute();
            log.info("DELETE请求成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("DELETE请求失败: {}", url, e);
            return "请求失败: " + e.getMessage();
        }
    }

    /**
     * <h3>百度网页搜索</h3>
     *
     * @param keyword 搜索关键词
     * @return 搜索结果页面内容
     */
    @Tool("使用百度搜索关键词，返回搜索结果页面内容")
    public String webSearch(String keyword) {
        try {
            log.info("Agent搜索: {}", keyword);
            String encodedKeyword = java.net.URLEncoder.encode(keyword, "UTF-8");
            String url = "https://www.baidu.com/s?wd=" + encodedKeyword;
            
            HttpRequest request = HttpRequest.get(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

            HttpResponse response = request.execute();
            log.info("搜索成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("搜索失败: {}", keyword, e);
            return "搜索失败: " + e.getMessage();
        }
    }

    /**
     * <h3>获取网页内容</h3>
     *
     * @param url 网页URL
     * @return 网页内容
     */
    @Tool("获取指定URL的网页内容")
    public String fetchWebPage(String url) {
        try {
            log.info("Agent获取网页: {}", url);
            HttpRequest request = HttpRequest.get(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

            HttpResponse response = request.execute();
            log.info("获取网页成功 - 状态码: {}", response.getStatus());
            return response.body();
        } catch (Exception e) {
            log.error("获取网页失败: {}", url, e);
            return "获取网页失败: " + e.getMessage();
        }
    }

    /**
     * <h3>检查URL是否可达</h3>
     *
     * @param url 要检查的URL
     * @return true-可达 false-不可达
     */
    @Tool("检查指定URL是否可以访问")
    public boolean isUrlReachable(String url) {
        try {
            log.info("Agent检查URL可达性: {}", url);
            HttpRequest request = HttpRequest.head(url)
                    .timeout(5000);

            HttpResponse response = request.execute();
            int status = response.getStatus();
            log.info("URL检查结果 - 状态码: {}", status);
            return status >= 200 && status < 400;
        } catch (Exception e) {
            log.error("URL检查失败: {}", url, e);
            return false;
        }
    }

    /**
     * <h3>下载文件</h3>
     *
     * @param url      文件URL
     * @param savePath 保存路径
     * @return 操作结果
     */
    @Tool("从URL下载文件到指定路径")
    public String downloadFile(String url, String savePath) {
        try {
            log.info("Agent下载文件: {} -> {}", url, savePath);
            HttpRequest request = HttpRequest.get(url)
                    .timeout(DEFAULT_CONNECT_TIMEOUT)
                    .setReadTimeout(DEFAULT_READ_TIMEOUT * 2);

            HttpResponse response = request.execute();
            
            if (response.isOk()) {
                response.writeBody(savePath);
                log.info("文件下载成功: {}", savePath);
                return "文件下载成功: " + savePath;
            } else {
                log.error("文件下载失败 - 状态码: {}", response.getStatus());
                return "文件下载失败，状态码: " + response.getStatus();
            }
        } catch (Exception e) {
            log.error("文件下载失败: {}", url, e);
            return "文件下载失败: " + e.getMessage();
        }
    }
}
