package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 生成excel工具
 *
 * @param <T>
 * @author zhanglei
 */
public class GenerateExcelUtil<T> {
    protected SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
    CellStyle contentStyle;
    CellStyle titleStyle;

    {
        contentStyle = ExcelStyle.createCellStyle(workbook);
        titleStyle = ExcelStyle.createCellStyleTitle(workbook);
    }

    protected String[] title;
    protected String[] titleKey;
    private List<T> list;
    private String sheetName = "Sheet1";

    Map<String, List<T>> listMap;

    public GenerateExcelUtil(String[] title, String[] titleKey, List<T> list) {
        this.title = title;
        this.titleKey = titleKey;
        this.list = list;
    }

    /**
     * @param titleKey
     * @param compressTempFiles 是否设置压缩
     */
    public GenerateExcelUtil(String[] title, String[] titleKey, boolean compressTempFiles) {
        this.title = title;
        this.titleKey = titleKey;
        workbook.setCompressTempFiles(compressTempFiles);
    }

    public GenerateExcelUtil() {

    }


    public GenerateExcelUtil(Map<String, List<T>> outExcelList) {
        listMap = outExcelList;

    }

    /**
     * 初始化表头，与init()方法冲突，不能一同使用，直接使用add();
     *
     * @return 返回最后一行行数
     */
    public int initTitle(Sheet sheet) {
        setDetailTitle(sheet);
        return sheet.getLastRowNum();
    }

    public void init() {
        setDetail();
    }

    public void initNoTitleMerge() {
        setDetailNoTitleMerge();
    }

    public void init(List<T> list) {
        this.list = list;
        setDetail();
    }

    public void initByMap(Class<T> classz) {

        Field[] fields = classz.getDeclaredFields();
        //初始化表头
        title = new String[fields.length];
        titleKey = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (name.contains("this")) {
                continue;
            }
            title[i] = name;
            titleKey[i] = name;
        }

        for (Entry<String, List<T>> item : listMap.entrySet()) {
            Sheet sheet = workbook.createSheet(item.getKey());
            int rowNum = sheet.getLastRowNum();
            List<T> value = item.getValue();

            setDetailTitle(sheet);
            //初始化值
            for (T t : value) {
                JSONObject json = JSONObject.parseObject(JSON.toJSONString(t));
                Row row = sheet.createRow(++rowNum);
                // 明细
                for (int i = 0; i < titleKey.length; i++) {
                    String key = titleKey[i];
                    Cell cell = row.createCell(i);
                    if (json.containsKey(key)) {
                        cell.setCellValue(json.getString(key));
                    } else {
                        cell.setCellValue("");
                    }

                    cell.setCellStyle(contentStyle);
                }

            }
        }
    }


    private int setDetail() {
        Sheet sheet = workbook.createSheet(sheetName);
        setDetailTitle(sheet);
        return setDetail(sheet);

    }

    private int setDetailNoTitleMerge() {
        Sheet sheet = workbook.getSheet(sheetName);
        if(null == sheet) {
            sheet = workbook.createSheet(sheetName);
        }
        setDetailTitleNoTitleMerge(sheet);
        return setDetail(sheet);

    }

    public int setDetail(Sheet sheet) {
        int rowNum = sheet.getLastRowNum();
        String sheetName = sheet.getSheetName();
        int sheetNum = 1;
        for (T t : list) {
            if(rowNum > SpreadsheetVersion.EXCEL2007.getLastRowIndex() - 1) {
                // 超过最大行了，重新定义个sheet
                String newSheetName = String.format("%s_%d", sheetName, ++sheetNum);
                sheet = workbook.getSheet(newSheetName);
                if(null == sheet) {
                    sheet = workbook.createSheet(newSheetName);
                    rowNum = initTitle(sheet);
                }

            }
            Row row = sheet.createRow(++rowNum);
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(t, SerializerFeature.WriteDateUseDateFormat));
            // 明细
            for (int i = 0; i < titleKey.length; i++) {
                String key = titleKey[i];
                String valueString = json.getString(key);
                if (valueString != null && valueString.length() > SpreadsheetVersion.EXCEL2007.getMaxTextLength()) {
                    valueString = valueString.substring(0, SpreadsheetVersion.EXCEL2007.getMaxTextLength() - 4) + "...";
                }
                Cell cell = row.createCell(i);
                cell.setCellValue(valueString);
                cell.setCellStyle(contentStyle);
            }
        }
        return rowNum;
    }

    /**
     * 初始化标题
     *
     * @param sheet
     */
    private void setDetailTitle(Sheet sheet) {
        Row row = sheet.createRow(0);
        // sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 4048);
            Cell cell = row.createCell(i);
            String val = title[i];
            cell.setCellValue(val);
            cell.setCellStyle(titleStyle);
        }

    }

    /**
     * 初始化标题
     *
     * @param sheet
     */
    private void setDetailTitleNoTitleMerge(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 4048);
            Cell cell = row.createCell(i);
            cell.setCellStyle(titleStyle);
            String val = title[i];
            cell.setCellValue(val);
        }

    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void add(List<T> list) {
        addDetail(list);
    }

    public void add(List<T> list, int rowNum) {
        addDetail(list, rowNum);
    }

    public void add(String sheetName, List<T> list) {
        this.sheetName = sheetName;
        addDetail(list);
    }

    private void addDetail(List<T> list) {
        Sheet sheet = workbook.getSheet(sheetName);
        if(null == sheet) {
            sheet = workbook.createSheet(sheetName);
            setDetailTitleNoTitleMerge(sheet);
        }
        int rowNum = sheet.getLastRowNum();
        for (T t : list) {
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(t));
            Row row = sheet.createRow(++rowNum);
            // 明细
            for (int i = 0; i < titleKey.length; i++) {
                String key = titleKey[i];
                Cell cell = row.createCell(i);
                String valueString = json.getString(key);
                if(StringUtils.isEmpty(valueString)) {
                    continue;
                }
                if (valueString.length() > SpreadsheetVersion.EXCEL2007.getMaxTextLength()) {
                    valueString = valueString.substring(0, SpreadsheetVersion.EXCEL2007.getMaxTextLength() - 4) + "...";
                }
                cell.setCellValue(valueString);
                cell.setCellStyle(contentStyle);
            }
        }
    }

    private void addDetail(List<T> list, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        for (T t : list) {
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(t));
            Row row = sheet.createRow(++rowNum);
            // 明细
            for (int i = 0; i < titleKey.length; i++) {
                String key = titleKey[i];
                Cell cell = row.createCell(i);
                String valueString = json.getString(key);
                if (valueString.length() > SpreadsheetVersion.EXCEL2007.getMaxTextLength()) {
                    valueString = valueString.substring(0, SpreadsheetVersion.EXCEL2007.getMaxTextLength() - 4) + "...";
                }
                cell.setCellValue(valueString);
                cell.setCellStyle(contentStyle);
            }
        }
    }

    /**
     * 解析.xlsx格式的excel文件
     *
     * @return
     * @throws IOException
     */
/*    public static List<SS> readXLSX(String path)
            throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //    File file = new File("C:\\\\Users\\\\FB-017\\\\Desktop\\\\经典音乐广播-2020-06-01-22.xlsx");
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<SS> list = new ArrayList<>();
        // 循环工作表Sheet
        // 循环行Row
        int errorNum = 0;
        int okNum = 0;
        String errorMsg = "";
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (null != xssfRow) {
                // getLastCellNum获取最后一列
                SS ss = new SS();
                Class<? extends SS> clazz = ss.getClass();
                for (int k = 0; k < xssfRow.getLastCellNum(); k++) {

                    Cell cell = xssfRow.getCell(k);
                    // cell->double
                    String d = null;
                    if (null != xssfRow.getCell(k)) {
                        DecimalFormat df = new DecimalFormat("#.##");
                        switch (cell.getCellType()) {
                            case STRING:
                                d = cell.getRichStringCellValue().getString().trim();
                                break;
                            case NUMERIC:
                                d = df.format(cell.getNumericCellValue()).toString();
                                break;
                            case BOOLEAN:
                                d = String.valueOf(cell.getBooleanCellValue()).trim();
                                break;
                            case FORMULA:
                                d = cell.getCellFormula();
                                break;
                            default:
                                d = "";
                        }
                    } else {
                        d = "";
                    }
                    // double->int
                    Method method = clazz.getMethod("setS" + (k + 1), String.class);
                    method.invoke(ss, d);
                }
                list.add(ss);
            }
        }
        if (null != is) {
            is.close();
        }
	*//*	Set<String> titleList = list.stream().filter(ss -> StringUtils.isNotBlank(ss.getS7())).map(ss -> ss.getS7().trim()).collect(Collectors.toSet());
		String sss = toString(titleList);*//*
        return list;
    }*/

    public static void main(String[] args) throws Exception {
        //

//		readXLSX();
    }

    public static String toString(Object object) {
        return JSON.toJSONString(object,
                new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                        SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.UseISO8601DateFormat});
    }

    public int initTitle(Sheet sheet, int rowNum) {
        setDetailTitle(sheet, rowNum);
        return sheet.getLastRowNum();

    }

    private void setDetailTitle(Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 4048);
            Cell cell = row.createCell(i);
            String val = title[i];
            cell.setCellValue(val);
            cell.setCellStyle(titleStyle);
        }
    }
}
