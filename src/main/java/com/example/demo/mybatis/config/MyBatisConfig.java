package com.example.demo.mybatis.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
    *@ClassName: MyBatisConfig
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/9 10:01
    */

//@EnableTransactionManagement
@Configuration
public class MyBatisConfig {

  @Autowired
  private DataSource  dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml"));
    //驼峰命名法映射
    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setCallSettersOnNulls(true);
    sqlSessionFactoryBean.setConfiguration(configuration);
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager platformTransactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

}
