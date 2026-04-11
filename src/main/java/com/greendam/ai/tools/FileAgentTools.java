package com.greendam.ai.tools;

import cn.hutool.core.io.FileUtil;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <h3>文件操作工具 - Agent专用</h3>
 * <p>提供Agent使用的文件读写、目录操作等功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class FileAgentTools {

    /**
     * <h3>读取文件内容</h3>
     *
     * @param filePath 文件路径
     * @return 文件内容字符串
     */
    @Tool("读取指定路径的文件内容")
    public String readFile(String filePath) {
        try {
            log.info("Agent读取文件: {}", filePath);
            return FileUtil.readString(filePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("读取文件失败: {}", filePath, e);
            return "读取文件失败: " + e.getMessage();
        }
    }

    /**
     * <h3>按行读取文件</h3>
     *
     * @param filePath 文件路径
     * @return 文件内容列表（每行一个元素）
     */
    @Tool("按行读取指定路径的文件，返回每行内容的列表")
    public List<String> readFileLines(String filePath) {
        try {
            log.info("Agent按行读取文件: {}", filePath);
            return FileUtil.readLines(filePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("按行读取文件失败: {}", filePath, e);
            return List.of("读取文件失败: " + e.getMessage());
        }
    }

    /**
     * <h3>写入内容到文件</h3>
     *
     * @param filePath 文件路径
     * @param content  要写入的内容
     * @return 操作结果
     */
    @Tool("将内容写入指定路径的文件，会覆盖原有内容")
    public String writeFile(String filePath, String content) {
        try {
            log.info("Agent写入文件: {}", filePath);
            FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
            return "文件写入成功: " + filePath;
        } catch (Exception e) {
            log.error("写入文件失败: {}", filePath, e);
            return "写入文件失败: " + e.getMessage();
        }
    }

    /**
     * <h3>追加内容到文件</h3>
     *
     * @param filePath 文件路径
     * @param content  要追加的内容
     * @return 操作结果
     */
    @Tool("将内容追加到指定路径的文件末尾")
    public String appendFile(String filePath, String content) {
        try {
            log.info("Agent追加文件: {}", filePath);
            FileUtil.appendString(content, filePath, StandardCharsets.UTF_8);
            return "文件追加成功: " + filePath;
        } catch (Exception e) {
            log.error("追加文件失败: {}", filePath, e);
            return "追加文件失败: " + e.getMessage();
        }
    }

    /**
     * <h3>检查文件是否存在</h3>
     *
     * @param filePath 文件路径
     * @return true-存在 false-不存在
     */
    @Tool("检查指定路径的文件或目录是否存在")
    public boolean fileExists(String filePath) {
        return FileUtil.exist(filePath);
    }

    /**
     * <h3>创建目录</h3>
     *
     * @param dirPath 目录路径
     * @return 操作结果
     */
    @Tool("创建指定路径的目录，如果父目录不存在会自动创建")
    public String createDirectory(String dirPath) {
        try {
            log.info("Agent创建目录: {}", dirPath);
            FileUtil.mkdir(dirPath);
            return "目录创建成功: " + dirPath;
        } catch (Exception e) {
            log.error("创建目录失败: {}", dirPath, e);
            return "创建目录失败: " + e.getMessage();
        }
    }

    /**
     * <h3>删除文件或目录</h3>
     *
     * @param filePath 文件或目录路径
     * @return 操作结果
     */
    @Tool("删除指定路径的文件或目录（谨慎使用！）")
    public String deleteFile(String filePath) {
        try {
            log.info("Agent删除文件: {}", filePath);
            FileUtil.del(filePath);
            return "文件删除成功: " + filePath;
        } catch (Exception e) {
            log.error("删除文件失败: {}", filePath, e);
            return "删除文件失败: " + e.getMessage();
        }
    }

    /**
     * <h3>获取文件扩展名</h3>
     *
     * @param filePath 文件路径
     * @return 文件扩展名
     */
    @Tool("获取指定文件的扩展名")
    public String getFileExtension(String filePath) {
        return FileUtil.extName(filePath);
    }

    /**
     * <h3>获取文件大小</h3>
     *
     * @param filePath 文件路径
     * @return 文件大小（字节）
     */
    @Tool("获取指定文件的大小，单位为字节")
    public long getFileSize(String filePath) {
        return FileUtil.size(new File(filePath));
    }

    /**
     * <h3>列出目录内容</h3>
     *
     * @param dirPath 目录路径
     * @return 目录内的文件和目录列表
     */
    @Tool("列出指定目录下的所有文件和子目录")
    public List<String> listDirectory(String dirPath) {
        try {
            File[] files = FileUtil.ls(dirPath);
            return java.util.Arrays.stream(files)
                    .map(File::getName)
                    .toList();
        } catch (Exception e) {
            log.error("列出目录失败: {}", dirPath, e);
            return List.of("列出目录失败: " + e.getMessage());
        }
    }
}
