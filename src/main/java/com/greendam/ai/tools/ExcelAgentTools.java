package com.greendam.ai.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Excel 解析工具 - Agent专用</h3>
 * <p>提供 Agent 使用的 Excel 文件读取功能</p>
 *
 * @author ForeverGreenDam
 */
@Slf4j
public class ExcelAgentTools {

    private static final long MAX_FILE_SIZE = 52428800L;

    /**
     * <h3>读取 Excel 全部内容</h3>
     *
     * @param filePath Excel 文件路径（支持 .xls 和 .xlsx）
     * @return 纯文本表格格式的内容
     */
    @Tool("读取指定路径的 Excel 文件全部内容，输出为纯文本表格格式")
    public String readExcelText(String filePath) {
        try {
            log.info("Agent读取Excel文件: {}", filePath);
            
            File file = checkFile(filePath);
            if (file == null) {
                return "文件不存在或无法访问: " + filePath;
            }
            
            if (!checkFileSize(file)) {
                return "文件超过 50MB 限制，无法读取";
            }
            
            String extension = getFileExtension(filePath).toLowerCase();
            
            if ("xlsx".equals(extension)) {
                return readXlsxAll(file);
            } else if ("xls".equals(extension)) {
                return readXlsAll(file);
            } else {
                return "不支持的文件格式，请使用 .xls 或 .xlsx 文件";
            }
        } catch (Exception e) {
            log.error("读取Excel失败: {}", filePath, e);
            return "读取Excel失败: " + e.getMessage();
        }
    }

    /**
     * <h3>读取 Excel 指定工作表内容</h3>
     *
     * @param filePath   Excel 文件路径
     * @param sheetIndex 工作表索引（从 0 开始）
     * @return 纯文本表格格式的内容
     */
    @Tool("读取指定路径的 Excel 文件中指定工作表的内容（工作表索引从 0 开始）")
    public String readExcelSheet(String filePath, int sheetIndex) {
        try {
            log.info("Agent读取Excel工作表: {} 索引 {}", filePath, sheetIndex);
            
            File file = checkFile(filePath);
            if (file == null) {
                return "文件不存在或无法访问: " + filePath;
            }
            
            if (!checkFileSize(file)) {
                return "文件超过 50MB 限制，无法读取";
            }
            
            String extension = getFileExtension(filePath).toLowerCase();
            
            if ("xlsx".equals(extension)) {
                return readXlsxSheet(file, sheetIndex);
            } else if ("xls".equals(extension)) {
                return readXlsSheet(file, sheetIndex);
            } else {
                return "不支持的文件格式，请使用 .xls 或 .xlsx 文件";
            }
        } catch (Exception e) {
            log.error("读取Excel工作表失败: {}", filePath, e);
            return "读取Excel失败: " + e.getMessage();
        }
    }

    /**
     * <h3>获取 Excel 所有工作表名称</h3>
     *
     * @param filePath Excel 文件路径
     * @return 工作表名称列表
     */
    @Tool("获取指定 Excel 文件的所有工作表名称列表")
    public List<String> getExcelSheetNames(String filePath) {
        try {
            log.info("Agent获取Excel工作表名称: {}", filePath);
            
            File file = checkFile(filePath);
            if (file == null) {
                return new ArrayList<>();
            }
            
            String extension = getFileExtension(filePath).toLowerCase();
            List<String> sheetNames = new ArrayList<>();
            
            if ("xlsx".equals(extension)) {
                try (InputStream fis = new FileInputStream(file);
                     XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
                    
                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        sheetNames.add(workbook.getSheetName(i));
                    }
                }
            } else if ("xls".equals(extension)) {
                try (InputStream fis = new FileInputStream(file);
                     HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
                    
                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        sheetNames.add(workbook.getSheetName(i));
                    }
                }
            }
            
            log.info("Excel工作表名称: {}", sheetNames);
            return sheetNames;
        } catch (Exception e) {
            log.error("获取Excel工作表名称失败: {}", filePath, e);
            return new ArrayList<>();
        }
    }

    private String readXlsxAll(File file) {
        try (InputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            StringBuilder result = new StringBuilder();
            DataFormatter formatter = new DataFormatter();
            
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = workbook.getSheetName(sheetIndex);
                result.append("=== 工作表: ").append(sheetName).append(" ===\n");
                appendSheetContent(result, sheet, formatter);
                result.append("\n");
            }
            
            log.info("XLSX读取成功，工作表数: {}", workbook.getNumberOfSheets());
            return result.toString();
        } catch (Exception e) {
            log.error("读取XLSX失败", e);
            throw new RuntimeException("读取XLSX失败: " + e.getMessage(), e);
        }
    }

    private String readXlsAll(File file) {
        try (InputStream fis = new FileInputStream(file);
             HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
            
            StringBuilder result = new StringBuilder();
            DataFormatter formatter = new DataFormatter();
            
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = workbook.getSheetName(sheetIndex);
                result.append("=== 工作表: ").append(sheetName).append(" ===\n");
                appendSheetContent(result, sheet, formatter);
                result.append("\n");
            }
            
            log.info("XLS读取成功，工作表数: {}", workbook.getNumberOfSheets());
            return result.toString();
        } catch (Exception e) {
            log.error("读取XLS失败", e);
            throw new RuntimeException("读取XLS失败: " + e.getMessage(), e);
        }
    }

    private String readXlsxSheet(File file, int sheetIndex) {
        try (InputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            if (sheetIndex < 0 || sheetIndex >= workbook.getNumberOfSheets()) {
                return "工作表索引无效，总工作表数: " + workbook.getNumberOfSheets();
            }
            
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String sheetName = workbook.getSheetName(sheetIndex);
            StringBuilder result = new StringBuilder();
            result.append("=== 工作表: ").append(sheetName).append(" ===\n");
            DataFormatter formatter = new DataFormatter();
            appendSheetContent(result, sheet, formatter);
            
            log.info("XLSX工作表读取成功: {}", sheetName);
            return result.toString();
        } catch (Exception e) {
            log.error("读取XLSX工作表失败", e);
            throw new RuntimeException("读取XLSX工作表失败: " + e.getMessage(), e);
        }
    }

    private String readXlsSheet(File file, int sheetIndex) {
        try (InputStream fis = new FileInputStream(file);
             HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
            
            if (sheetIndex < 0 || sheetIndex >= workbook.getNumberOfSheets()) {
                return "工作表索引无效，总工作表数: " + workbook.getNumberOfSheets();
            }
            
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String sheetName = workbook.getSheetName(sheetIndex);
            StringBuilder result = new StringBuilder();
            result.append("=== 工作表: ").append(sheetName).append(" ===\n");
            DataFormatter formatter = new DataFormatter();
            appendSheetContent(result, sheet, formatter);
            
            log.info("XLS工作表读取成功: {}", sheetName);
            return result.toString();
        } catch (Exception e) {
            log.error("读取XLS工作表失败", e);
            throw new RuntimeException("读取XLS工作表失败: " + e.getMessage(), e);
        }
    }

    private void appendSheetContent(StringBuilder result, Sheet sheet, DataFormatter formatter) {
        for (Row row : sheet) {
            StringBuilder rowContent = new StringBuilder();
            boolean firstCell = true;
            
            for (Cell cell : row) {
                if (!firstCell) {
                    rowContent.append("\t");
                }
                String cellValue = formatter.formatCellValue(cell);
                rowContent.append(cellValue);
                firstCell = false;
            }
            
            result.append(rowContent).append("\n");
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
