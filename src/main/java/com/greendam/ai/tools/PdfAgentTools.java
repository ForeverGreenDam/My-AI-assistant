package com.greendam.ai.tools;

import cn.hutool.core.io.FileUtil;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * <h3>PDF 解析工具 - Agent专用</h3>
 * <p>提供 Agent 使用的 PDF 文件读取功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class PdfAgentTools {

    private static final long MAX_FILE_SIZE = 52428800L;

    /**
     * <h3>读取 PDF 全部文本内容</h3>
     *
     * @param filePath PDF 文件路径
     * @return 文本内容
     */
    @Tool("读取指定路径的 PDF 文件全部文本内容")
    public String readPdfText(String filePath) {
        try {
            log.info("Agent读取PDF文件: {}", filePath);
            
            File file = checkFile(filePath);
            if (file == null) {
                return "文件不存在或无法访问: " + filePath;
            }
            
            if (!checkFileSize(file)) {
                return "文件超过 50MB 限制，无法读取";
            }
            
            try (PDDocument document = Loader.loadPDF(file)) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                log.info("PDF读取成功，页数: {}", document.getNumberOfPages());
                return text;
            }
        } catch (Exception e) {
            log.error("读取PDF失败: {}", filePath, e);
            return "读取PDF失败: " + e.getMessage();
        }
    }

    /**
     * <h3>读取 PDF 指定页码范围内容</h3>
     *
     * @param filePath   PDF 文件路径
     * @param startPage  起始页码（从 1 开始）
     * @param endPage    结束页码
     * @return 指定范围的文本内容
     */
    @Tool("读取指定页码范围的 PDF 内容（页码从 1 开始）")
    public String readPdfPages(String filePath, int startPage, int endPage) {
        try {
            log.info("Agent读取PDF页码范围: {} 页 {} - {}", filePath, startPage, endPage);
            
            File file = checkFile(filePath);
            if (file == null) {
                return "文件不存在或无法访问: " + filePath;
            }
            
            if (!checkFileSize(file)) {
                return "文件超过 50MB 限制，无法读取";
            }
            
            try (PDDocument document = Loader.loadPDF(file)) {
                int totalPages = document.getNumberOfPages();
                
                if (startPage < 1 || endPage > totalPages || startPage > endPage) {
                    return "页码范围无效，总页数: " + totalPages;
                }
                
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setStartPage(startPage);
                stripper.setEndPage(endPage);
                String text = stripper.getText(document);
                log.info("PDF页码范围读取成功: {}-{}", startPage, endPage);
                return text;
            }
        } catch (Exception e) {
            log.error("读取PDF页码范围失败: {}", filePath, e);
            return "读取PDF失败: " + e.getMessage();
        }
    }

    /**
     * <h3>获取 PDF 总页数</h3>
     *
     * @param filePath PDF 文件路径
     * @return 总页数
     */
    @Tool("获取指定 PDF 文件的总页数")
    public int getPdfPageCount(String filePath) {
        try {
            log.info("Agent获取PDF页数: {}", filePath);
            
            File file = checkFile(filePath);
            if (file == null) {
                return -1;
            }
            
            try (PDDocument document = Loader.loadPDF(file)) {
                int pageCount = document.getNumberOfPages();
                log.info("PDF页数: {}", pageCount);
                return pageCount;
            }
        } catch (Exception e) {
            log.error("获取PDF页数失败: {}", filePath, e);
            return -1;
        }
    }

    private File checkFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return file;
    }

    private boolean checkFileSize(File file) {
        return file.length() <= MAX_FILE_SIZE;
    }
}
