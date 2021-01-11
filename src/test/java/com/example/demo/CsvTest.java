package com.example.demo;

import com.example.demo.csv.CsvParse;
import com.example.demo.csv.YouTuBeCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2020/11/10 19:52
 * @Version:1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class CsvTest {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\handa_ly\\Desktop\\test.csv";
        List<YouTuBeCsv> list = CsvParse.getCsvDataByCsvHeaderName(filePath, YouTuBeCsv.class);
        list.forEach(ytbc -> {
            System.out.println(ytbc.getResourceId());
        });
    }
    public void test(){

    }

}