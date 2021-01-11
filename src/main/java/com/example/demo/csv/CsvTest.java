package com.example.demo.csv;

import java.util.List;

/**
 * @Author: hanDa
 * @Date: 2020/11/10 20:06
 * @Version:1.0
 * @Description:
 */
public class CsvTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\handa_ly\\Desktop\\Report_YouTube_must_TW_202010_20201107195634_masterlist.csv";
        List<YouTuBeCsv> list = CsvParse.getCsvDataByCsvHeaderName(filePath, YouTuBeCsv.class);
        list.forEach(ytbc -> {
            System.out.println(ytbc.toString());
        });
    }
}