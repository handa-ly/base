package com.example.demo;

import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.service.quartz.QuartzJobLogService;
import com.example.demo.utils.ExcelReportUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

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

    @Autowired
    private QuartzJobLogService quartzJobLogService;

    public static void main(String[] args) {
        String filePath = "C:\\Users\\handa_ly\\Desktop\\test——test.xlsx";

    }
    @Test
    public void test1() throws IOException {
        List<QuartzJobLog> quartzJobLogList = new ArrayList<>();
        for (int i =0;i<2000000;i++){
            QuartzJobLog quartzJobLog = new QuartzJobLog("172.18.0.3","8080");
            quartzJobLogList.add(quartzJobLog);
            if (quartzJobLogList.size() >100000){
                quartzJobLogService.addList(quartzJobLogList);
                quartzJobLogList.clear();
            }
        }
    }
    @Test
    public void test() throws IOException {
        String filePath = "C:\\Users\\handa_ly\\Desktop\\test——test.xlsx";
        List<QuartzJobLog> list = new ArrayList<>();
        List<QuartzJobLog> quartzJobLogList = quartzJobLogService.listAll();
        Map<String, Object> firstMap = obj2Map(quartzJobLogList.get(0));
        Set<String> set = firstMap.keySet();
        String[] titleArray = new String[set.size()];
        firstMap.keySet().toArray(titleArray);
        ExcelReportUtils.excelFileGenerate(filePath, quartzJobLogList, titleArray, titleArray);
    }
    private static Map<String, Object> obj2Map(Object obj) {

        Map<String, Object> map = new HashMap<>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }

}