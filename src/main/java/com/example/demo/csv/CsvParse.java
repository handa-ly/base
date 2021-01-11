package com.example.demo.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2020/11/10 19:58
 * @Version:1.0
 * @Description:
 */
public class CsvParse {
    public static <T> List<T> getCsvDataByCsvHeaderName(String filePath, Class<T> clazz, Map<String, String> columnMapping) {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(getInputStream(new FileInputStream(new File(filePath))), "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        HeaderColumnNameTranslateMappingStrategy<T> strategy =
                new HeaderColumnNameTranslateMappingStrategy<T>();
        strategy.setType(clazz);
        strategy.setColumnMapping(columnMapping);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
                .withQuoteChar('\"')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

    /**
     * 解析csv文件并转成bean
     *
     * @param filePath csv文件
     * @param clazz    类
     * @param <T>      泛型
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvDataByCsvHeaderName(String filePath, Class<T> clazz) {
        Map<String, String> columnMapping = createCsvHeaderToBeanMap();
        return getCsvDataByCsvHeaderName(filePath, clazz, columnMapping);
    }
    /**
     * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
     * (opencsv 按列名获取bean对象，第一列缺失的情况)
     * InputStreamReader is = new InputStreamReader(
     * CsvUtil.getInputStream(new  FileInputStream(fileName)), "utf-8");
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(InputStream in) throws IOException {

        PushbackInputStream testin = new PushbackInputStream(in);
        int ch = testin.read();
        if (ch != 0xEF) {
            testin.unread(ch);
        } else if ((ch = testin.read()) != 0xBB) {
            testin.unread(ch);
            testin.unread(0xef);
        } else if ((ch = testin.read()) != 0xBF) {
            throw new IOException("错误的UTF-8格式文件");
        } else {
        }
        return testin;
    }


    private static Map<String, String> createCsvHeaderToBeanMap() {
        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("ID", "resourceId");//csv中的header1对应bean的header1
        columnMapping.put("TITLE", "title");
        columnMapping.put("ARTIST", "artist");
        columnMapping.put("ALBUM", "album");
        columnMapping.put("LABEL", "label");
        columnMapping.put("ISRC", "isrc");
        columnMapping.put("COMP_ID", "compId");
        columnMapping.put("COMP_TITLE", "compTitle");
        columnMapping.put("COMP_ISWC", "compIswc");
        columnMapping.put("COMP_WRITERS", "compWriters");
        columnMapping.put("COMP_CUSTOM_ID", "compCustomId");
        columnMapping.put("VIEWS", "views");
        columnMapping.put("QUANTILE", "quantile");
        return columnMapping;
    }
}