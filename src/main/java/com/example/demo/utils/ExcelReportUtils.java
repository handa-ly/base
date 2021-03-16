package com.example.demo.utils;

import com.example.demo.result.MustException;
import com.example.demo.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: hanDa
 * @Date: 2020/8/5 10:27
 * @Version:1.0
 * @Description:
 */
@Slf4j
public class ExcelReportUtils {

    private static final String XLSX = ".xlsx";

    /***
     * @Description:
     * @Param: [response, templateName:导出名称, list：导出数据, titleArray：导出标题, keyArray：导出数据列--与titleArray一一对应]
     * @return: void
     * @Author: hanDa
     * @Date: 2020/8/5 10:30
     */
    public static <T> void excelReport(HttpServletResponse response, String templateName, List<T> list, String[] titleArray, String[] keyArray) {
        //导出数据标题信息和对应关系
        GenerateExcelUtil<Map<String, Object>> generateExcelUtil;
        generateExcelUtil = new GenerateExcelUtil(titleArray, keyArray, list);
        generateExcelUtil.initNoTitleMerge();
        Workbook workbook = generateExcelUtil.getWorkbook();
        try {
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(new String(templateName.concat(".xlsx").getBytes("utf-8")), "UTF-8"));
            response.setContentType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * @Description:
     * @Param:
     * @return:
     * @Author: hanDa
     * @Date: 2021/2/4 10:07
     */
    public static <T> void excelFileGenerate(String fileName, List<T> list, String[] titleArray, String[] keyArray) throws IOException {
        //导出数据标题信息和对应关系
        GenerateExcelUtil<T> generateExcelUtil = new GenerateExcelUtil<>(titleArray,
                keyArray, list);
        generateExcelUtil.initNoTitleMerge();
        Workbook workbook = generateExcelUtil.getWorkbook();
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
           throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static class ReadExcel {
        // 总行数
        private int totalRows = 0;
        // 总条数
        private int totalCells = 0;
        // 错误信息接收器
        private String errorMsg;

        // 构造方法
        public ReadExcel() {
        }

        // 获取总行数
        public int getTotalRows() {
            return totalRows;
        }

        // 获取总列数
        public int getTotalCells() {
            return totalCells;
        }

        // 获取错误信息
        public String getErrorInfo() {
            return errorMsg;
        }

        /**
         * 读EXCEL文件，获取信息集合,舍弃空行及其之后的数据，已提高效率
         *
         * @param mFile
         * @return
         */
        public List<Map<String, Object>> getExcelInfo(MultipartFile mFile) {
            String fileName = mFile.getOriginalFilename();// 获取文件名
//        List<Map<String, Object>> userList = new LinkedList<Map<String, Object>>();
            try {
                if (!validateExcel(fileName)) {// 验证文件名是否合格
                    return null;
                }
                boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
                if (isExcel2007(fileName)) {
                    isExcel2003 = false;
                }
                return createExcel(mFile.getInputStream(), isExcel2003);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MustException(ResultCode.Unknown_Exception.getCode(), "读EXCEL文件异常！");
            }
        }

        /**
         * 根据excel里面的内容读取客户信息
         *
         * @param is          输入流
         * @param isExcel2003 excel是2003还是2007版本
         * @return
         * @throws IOException
         */
        public List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003) {
            try {
                Workbook wb = null;
                if (isExcel2003) {// 当excel是2003时,创建excel2003
                    wb = new HSSFWorkbook(is);
                } else {// 当excel是2007时,创建excel2007
                    wb = new XSSFWorkbook(is);
                }
                return readExcelValue(wb);// 读取Excel里面客户的信息
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 读取Excel里面
         *
         * @param wb
         * @return
         */
        private List<Map<String, Object>> readExcelValue(Workbook wb) {
            // 得到第一个shell
            Sheet sheet = wb.getSheetAt(0);
            // 得到Excel的行数,舍弃空行及其往后的行
            getTotalNotNULLRows(sheet);
            // 得到Excel的列数(前提是有行数)
            if (totalRows > 1 && sheet.getRow(0) != null) {
                this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }

            List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
            List<String> titleList = new ArrayList<>();
            Row row0 = sheet.getRow(0);
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row0.getCell(c);
                if (null != cell) {
                    titleList.add(getValue(cell));
                }
            }
            // 循环Excel行数
            for (int r = 1; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                boolean isNULL = true;
                for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
                    Cell cell = row.getCell(c);
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        isNULL = false;
                    }
                }
                if (isNULL) {
                    continue;
                }
                // 循环Excel的列
                Map<String, Object> map = new HashMap<>();
                for (int c = 0; c < this.totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (null != cell) {
                        map.put(titleList.get(c), getValue(cell));
                    }
                }
                // 添加到list
                userList.add(map);
            }
            return userList;
        }

        private void getTotalNotNULLRows(Sheet sheet) {
            this.totalRows = sheet.getPhysicalNumberOfRows();
        }

        //解决excel类型问题，获得数值
        private String getValue(Cell cell) {
            String value = "";
            if (null == cell) {
                return value;
            }
            switch (cell.getCellType()) {
                //数值型
                case NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //如果是date类型则 ，获取该cell的date值
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        value = format.format(date);
                    } else {// 纯数字
                        BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                        value = big.toString();
                        //解决1234.0  去掉后面的.0
                        if (null != value && !"".equals(value.trim())) {
                            String[] item = value.split("[.]");
                            if (1 < item.length && "0".equals(item[1])) {
                                value = item[0];
                            }
                        }
                    }
                    break;
                //字符串类型
                case STRING:
                    value = cell.getStringCellValue().toString();
                    break;
                // 公式类型
                case FORMULA:
                    //读公式计算值
                    value = String.valueOf(cell.getNumericCellValue());
                    if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                        value = cell.getStringCellValue().toString();
                    }
                    break;
                // 布尔类型
                case BOOLEAN:
                    value = " " + cell.getBooleanCellValue();
                    break;
                // 空值
                case BLANK:
                    value = "";
//                    LogUtil.getLogger().error("excel出现空值");
                    break;
                // 故障
                case ERROR:
                    value = "";
//                    LogUtil.getLogger().error("excel出现故障");
                    break;
                default:
                    value = cell.getStringCellValue().toString();
            }
            if ("null".endsWith(value.trim())) {
                value = "";
            }
            return value;
        }

        /**
         * 验证EXCEL文件
         *
         * @param filePath
         * @return
         */
        public boolean validateExcel(String filePath) {
            if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
                errorMsg = "文件名不是excel格式";
                return false;
            }
            return true;
        }

        // @描述：是否是2003的excel，返回true是2003
        public boolean isExcel2003(String filePath) {
            return filePath.matches("^.+\\.(?i)(xls)$");
        }

        // @描述：是否是2007的excel，返回true是2007
        public boolean isExcel2007(String filePath) {
            return filePath.matches("^.+\\.(?i)(xlsx)$");
        }

    }
    public static void downloadXlsx(HttpServletResponse response,String fileName,String filePath){
        download(response,fileName,filePath,XLSX);
    }
    public static void download(HttpServletResponse response,String fileName,String filePath,String fileType){
        File file = new File(filePath);
        if(!file.exists()){
            throw new MustException(ResultCode.Unknown_Exception.getCode(),"下载文件不存在！");
        }
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(new String(fileName.concat(fileType).getBytes("utf-8")), "UTF-8"));
            response.setContentType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("{}",e);
            throw new MustException(ResultCode.Unknown_Exception.getCode(),"下载失败");
        }
    }
}