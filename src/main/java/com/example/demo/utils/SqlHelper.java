package com.example.demo.utils;
import com.example.demo.result.MustException;
import com.example.demo.result.ResultCode;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;


/**
 * @Author: hanDa
 * @Date: 2020/7/28 17:05
 * @Version:1.0
 * @Description:
 */
@Component
public class SqlHelper {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    private final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();




/**
 * 反射对象，增加对低版本Mybatis的支持
 *
 * @param object 反射对象
 * @return
 */
    public MetaObject forObject(Object object) {
        return  MetaObject.forObject(object,DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,null);
    }


/**
 * 通过接口获取sql
 *
 * @param mapper
 * @param methodName
 * @param args
 * @return
 */
    public String getMapperSql(Object mapper, String methodName, Object... args) {
        MetaObject metaObject = forObject(mapper);
        SqlSession session = (SqlSession) metaObject.getValue("h.sqlSession");
        Class<?> mapperInterface = (Class<?>) metaObject.getValue("h.mapperInterface");
        String fullMethodName = mapperInterface.getCanonicalName() + "." + methodName;
        if (args == null || args.length == 0) {
            return getNamespaceSql(fullMethodName, null);
        } else {
            return getMapperSql(mapperInterface, methodName, args);
        }
    }


/**
 * 通过Mapper方法名获取sql
 *
 * @param fullMapperMethodName
 * @param args
 * @return
 */
    public String getMapperSql(String fullMapperMethodName, Object... args) {


        if (args == null || args.length == 0) {
            return getNamespaceSql(fullMapperMethodName, null);
        }
        String methodName = fullMapperMethodName.substring(fullMapperMethodName.lastIndexOf('.') + 1);
        Class<?> mapperInterface = null;
        try {
            mapperInterface = Class.forName(fullMapperMethodName.substring(0, fullMapperMethodName.lastIndexOf('.')));
            return getMapperSql(mapperInterface, methodName, args);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("参数" + fullMapperMethodName + "无效！");
        }
    }


/**
 * 通过Mapper接口和方法名
 *
 * @param mapperInterface
 * @param methodName
 * @param args
 * @return
 */
    public String getMapperSql(Class<?> mapperInterface, String methodName, Object... args) {
        String fullMapperMethodName = mapperInterface.getCanonicalName() + "." + methodName;
        if (args == null || args.length == 0) {
            return getNamespaceSql(fullMapperMethodName, null);
        }
        Method method = getDeclaredMethods(mapperInterface, methodName);
        Map<String,Object> params = new HashMap<>();
        final Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; i++) {
            if (!RowBounds.class.isAssignableFrom(argTypes[i]) && !ResultHandler.class.isAssignableFrom(argTypes[i])) {
                String paramName = "param" + String.valueOf(params.size() + 1);
                paramName = getParamNameFromAnnotation(method, i, paramName);
                params.put(paramName, i >= args.length ? null : args[i]);
            }
        }
        if (args != null && args.length == 1) {
            Object _params = wrapCollection(args[0]);
            if (_params instanceof Map) {
                params.putAll((Map<String,Object>) _params);
            }
        }
        return getNamespaceSql(fullMapperMethodName, params);
    }


/**
 * 通过命名空间方式获取sql
 *
 * @param session
 * @param namespace
 * @return
 */
    public String getNamespaceSql(SqlSession session, String namespace) {
        return getNamespaceSql(namespace, null);
    }


/**
 * 通过命名空间方式获取sql
 *
 * @param namespace
 * @param params
 * @return
 */
    public String getNamespaceSql(String namespace, Object params) {
        params = wrapCollection(params);
        Configuration configuration = sqlSessionFactory.openSession().getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace);
        TypeHandlerRegistry typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        BoundSql boundSql = mappedStatement.getBoundSql(params);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (params == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(params.getClass())) {
                        value = params;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(params);
                        value = metaObject.getValue(propertyName);
                    }
                    JdbcType jdbcType = parameterMapping.getJdbcType();
                    if  (value == null && jdbcType == null) {
                        jdbcType = configuration.getJdbcTypeForNull();
                    }
                    sql = replaceParameter(sql, value, jdbcType, parameterMapping.getJavaType());
                }
            }
        }
        return sql;
    }

    public String renameColumn(String sql, Map<String, String> aliasMap){
        if (StringUtils.isBlank(sql)){
            return null;
        }
        int fromIndex = sql.toUpperCase().indexOf("SELECT")+"SELECT".length();
        int toIndex = sql.toUpperCase().indexOf("FROM");
        String subSql = sql.substring(fromIndex,toIndex);
        String[] array = subSql.split(",");
        List<String> aliasList = new ArrayList<>();
        for (String s : array) {
            String alias = aliasMap.get(s.trim().replace("`",""));
            aliasList.add(s.concat(" as ").concat(alias).concat(" "));
        }
        String start = sql.substring(0,fromIndex);
        String end = sql.substring(toIndex);
        String mid = StringUtils.join(aliasList.toArray(),",");
        sql = start.concat(mid).concat(end);
        return sql;
    }

/**
 * 根据类型替换参数
 * 仅作为数字和字符串两种类型进行处理，需要特殊处理的可以继续完善这里
 *
 * @param sql
 * @param value
 * @param jdbcType
 * @param javaType
 * @return
 */
    private String replaceParameter(String sql, Object value, JdbcType jdbcType, Class<?> javaType) {
        String strValue = String.valueOf(value);
        if (jdbcType != null) {
            switch (jdbcType) {
// 数字


                case BIT:
                case TINYINT:
                case SMALLINT:
                case INTEGER:
                case BIGINT:
                case FLOAT:
                case REAL:
                case DOUBLE:
                case NUMERIC:
                case DECIMAL:
                    break;
// 日期


                case DATE:
                case TIME:
                case TIMESTAMP:
// 其他，包含字符串和其他特殊类型


                default:
                    strValue = "'" + strValue + "'";


            }
        } else if (Number.class.isAssignableFrom(javaType)) {
// 不加单引号


        } else {
            strValue = "'" + strValue + "'";
        }
        return sql.replaceFirst("\\?", strValue);
    }


/**
 * 获取指定的方法
 *
 * @param clazz
 * @param methodName
 * @return
 */
    private Method getDeclaredMethods(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("方法" + methodName + "不存在！");
    }


/**
 * 获取参数注解名
 *
 * @param method
 * @param i
 * @param paramName
 * @return
 */
    private String getParamNameFromAnnotation(Method method, int i, String paramName) {
        final Object[] paramAnnos = method.getParameterAnnotations()[i];
        for (Object paramAnno : paramAnnos) {
            if (paramAnno instanceof Param) {
                paramName = ((Param) paramAnno).value();
            }
        }
        return paramName;
    }


/**
 * 简单包装参数
 *
 * @param object
 * @return
 */
    private Object wrapCollection(final Object object) {
        if (object instanceof List) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", object);
            return map;
        } else if (object != null && object.getClass().isArray()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("array", object);
            return map;
        }
        return object;
    }


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public  static String wrapperSql(String sql, Map<String,Object> params){
        if (org.apache.commons.lang3.StringUtils.isBlank(sql)){
            throw new MustException(ResultCode.Unknown_Exception.getCode(),"sql不能为空！");
        }
        if (sql.toUpperCase().indexOf("SELECT") == -1 || sql.toUpperCase().indexOf("FROM") == -1){
            throw new MustException(ResultCode.Unknown_Exception.getCode(),"sql不合法！");
        }
        if (MapUtils.isEmpty(params)){
            return sql;
        }else {
            for (Map.Entry<String, Object> k : params.entrySet()) {
                String param = "${".concat(k.getKey()).concat("}");
                String wrapperParam = "'".concat(Objects.isNull(k.getValue()) ? "" : k.getValue().toString()).concat("'");
                if (sql.contains(param)) {
                    sql = sql.replace(param, wrapperParam);
                }
            }
            return sql;
        }
    }

    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<>();
        params.put("host","172.18.0.3");
        params.put("port",8080);
        String sql = "select * from quartz_job_log where host =  ${host} and port = ${port}";
        String sqlInfo = wrapperSql(sql,params);
        System.out.println(sqlInfo);

    }
}