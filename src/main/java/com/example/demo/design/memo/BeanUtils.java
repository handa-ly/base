package com.example.demo.design.memo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
    *@ClassName: BeanUtils
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/22 11:22
    */
    
    
public class BeanUtils {
  public static HashMap<String,Object> backPro(Object bean){
    HashMap<String,Object> result = new HashMap<>();
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
      PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor descriptor : descriptors) {
             String fieldName = descriptor.getName();
             Method getter=descriptor.getReadMethod();
             Object fieldValue = getter.invoke(bean,new Object[]{});
             if(!fieldName.equalsIgnoreCase("class")){
               result.put(fieldName,fieldValue);
             }
      }
    } catch (IntrospectionException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return result;
  }
  public static void restorePro(Object bean, HashMap<String,Object> proMap){
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
        String fieldName = propertyDescriptor.getName();
        if(proMap.containsKey(fieldName)){
          Method setter = propertyDescriptor.getWriteMethod();
          setter.invoke(bean,new Object[]{proMap.get(fieldName)});
        }
      }

    } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
