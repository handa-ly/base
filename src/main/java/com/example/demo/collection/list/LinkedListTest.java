package com.example.demo.collection.list;/**
 * @Author handa
 * Description:
 * Date: Created in 10:36 2020/1/20
 * Modified By:
 */

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/20 10:36
 */
public class LinkedListTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        LinkedList linkedList = new LinkedList();
//        linkedList.addToTail("ss");
//        linkedList.addToTail("sss");
//        linkedList.addToTail("ssss");
//        System.out.println(linkedList);
        /*====================Araylist初始值为0，添加一个元素后扩容为10，每次扩容原容量的0.5倍（/取整）=======================*/
        /*Object[] elementData*/
        List<Integer> iList = new ArrayList<>();
        Field field = ArrayList.class.getDeclaredField("DEFAULTCAPACITY_EMPTY_ELEMENTDATA");
        field.setAccessible(true);
        Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = (Object[]) field.get(iList);
        System.out.println("DEFAULTCAPACITY_EMPTY_ELEMENTDATA size:" + DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length);
        Field field1 = ArrayList.class.getDeclaredField("EMPTY_ELEMENTDATA");
        field1.setAccessible(true);
        Object[] EMPTY_ELEMENTDATA = (Object[]) field1.get(iList);
        System.out.println("EMPTY_ELEMENTDATA size:" + EMPTY_ELEMENTDATA.length);
        Field f = ArrayList.class.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] dataElement = (Object[]) f.get(iList);
        System.out.println(dataElement.length);
        iList.add(2);
        dataElement = (Object[]) f.get(iList);
        System.out.println(dataElement.length);

        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        EMPTY_ELEMENTDATA = (Object[]) field1.get(iList);
        System.out.println("EMPTY_ELEMENTDATA size:" + EMPTY_ELEMENTDATA.length);
        DEFAULTCAPACITY_EMPTY_ELEMENTDATA = (Object[]) field.get(iList);
        System.out.println("DEFAULTCAPACITY_EMPTY_ELEMENTDATA size:" + DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length);
        dataElement = (Object[]) f.get(iList);
        System.out.println(dataElement.length);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        iList.add(2);
        EMPTY_ELEMENTDATA = (Object[]) field1.get(iList);
        System.out.println("EMPTY_ELEMENTDATA size:" + EMPTY_ELEMENTDATA.length);
        DEFAULTCAPACITY_EMPTY_ELEMENTDATA = (Object[]) field.get(iList);
        System.out.println("DEFAULTCAPACITY_EMPTY_ELEMENTDATA size:" + DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length);
        dataElement = (Object[]) f.get(iList);
        System.out.println(dataElement.length);
        iList.add(2);
        iList.add(2);
        dataElement = (Object[]) f.get(iList);
        System.out.println(dataElement.length);
    }
}
