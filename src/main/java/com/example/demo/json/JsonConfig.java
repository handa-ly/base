package com.example.demo.json;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class JsonConfig{

    @Bean
    public HttpMessageConverters customConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 创建配置类
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为falseJ,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //是否输出值为null的字段,默认为false。
                SerializerFeature.WriteMapNullValue
        );

        //此处是全局处理方式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setCharset(Charset.forName("UTF-8"));
        fastConverter.setFastJsonConfig(config);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.ALL);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        //支持text 转string
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        return new HttpMessageConverters(fastConverter, stringHttpMessageConverter);
    }

}
