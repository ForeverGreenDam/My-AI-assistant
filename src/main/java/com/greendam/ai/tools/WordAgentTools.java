package com.greendam.ai.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * <h3>Word 解析工具 - Agent专用</h3>
 * <p>提供 Agent 使用的 Word 文件读取功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class WordAgentTools {

    private static final long MAX_FILE_SIZE = 52428800L;

    /**
     * <h3>读取 Word 全部文本内容</h3>
     *
     * @param filePath Word 文件路径（支持 .doc 和 .docx）
     * @return 文本内容
     */
    @Tool("读取指定路径的 Word 文件全部文本内容（支持 .doc 和 .docx 格式）")
    public String readWordText(String filePath) {
        try {
            log.info("Agent读取Word文件: {}", filePath);
            
            File file = checkFile(filePath);
            if (file == null) {
                return "文件不存在或无法访问: " + filePath;
            }
            
            if (!checkFileSize(file)) {
                return "文件超过 50MB 限制，无法读取";
            }
            
            String extension = getFileExtension(filePath).toLowerCase();
            
            if ("docx".equals(extension)) {
                return readDocx(file);
            } else if ("doc".equals(extension)) {
                return readDoc(file);
            } else {
                return "不支持的文件格式，请使用 .doc 或 .docx 文件";
            }
        } catch (Exception e) {
            log.error("读取Word失败: {}", filePath, e);
            return "读取Word失败: " + e.getMessage();
        }
    }

    private String readDocx(File file) {
        try (InputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {
            
            StringBuilder text = new StringBuilder();
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            
            for (XWPFParagraph paragraph : paragraphs) {
                text.append(paragraph.getText()).append("\n");
            }
            
            log.info("DOCX读取成功，段落数: {}", paragraphs.size());
            return text.toString();
        } catch (Exception e) {
            log.error("读取DOCX失败", e);
            throw new RuntimeException("读取DOCX失败: " + e.getMessage(), e);
        }
    }

    private String readDoc(File file) {
        try (InputStream fis = new FileInputStream(file);
             HWPFDocument document = new HWPFDocument(fis);
             WordExtractor extractor = new WordExtractor(document)) {
            
            String text = extractor.getText();
            log.info("DOC读取成功");
            return text;
        } catch (Exception e) {
            log.error("读取DOC失败", e);
            throw new RuntimeException("读取DOC失败: " + e.getMessage(), e);
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

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }
}
