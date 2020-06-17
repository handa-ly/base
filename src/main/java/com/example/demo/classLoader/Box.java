package com.example.demo.classLoader;

import java.util.Iterator;

/**
    *@ClassName: Box
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/2 16:57
    */
    
    
public class Box {
    public static void main(String[] args) {
        String ss= "{\n" +
                "\t\"info\": {\n" +
                "\t\t\"ipBaseNo\": \" 1612000004974\",\n" +
                "\t\t\"ipType\": \"\"\n" +
                "\t},\n" +
                "\t\"agreementGroups\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"agreements\": [],\n" +
                "\t\t\t\"groupName\": \" PER\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"names\": [\n" +
                "\t\t{\t\n" +
                "\t\t\t\"nameType\": \" PA\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        iterationJosn(ss);
//        System.out.println(ss.toString());
    }

    public static String iterationJosn(String json){
      /*  Iterator it = null;
        if(JSONObject.isValidObject(json)){
            it= JSONObject.parseObject(json).values().iterator();

        }else if(JSONObject.isValidArray(json)){
            it = JSONObject.parseArray(json).iterator();
        }else
        if(JSONObject.isValid(json)){
            json.trim();
            System.out.println(json);
        }
        while (it !=null && it.hasNext()){
            Object next = it.next();
            if(next != null){
                String trimJson = iterationJosn(next.toString());
            }
        }*/

        return json;
    }


}
