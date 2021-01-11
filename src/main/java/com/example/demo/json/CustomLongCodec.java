package com.example.demo.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.LongCodec;

import java.io.IOException;
import java.lang.reflect.Type;

/**
    *@ClassName: CustomLongCodec
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/3 16:49
    */
    
    
public class CustomLongCodec extends LongCodec {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        super.write(serializer, object, fieldName, fieldType, features);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
//        String longStr = parser.getLexer().stringVal();
         return super.deserialze(parser, clazz, fieldName);
    }
}
