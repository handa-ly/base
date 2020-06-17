package com.example.demo.service.${packagePath}.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.pojo.${packagePath}.${className};
import com.example.demo.mapper.${packagePath}.${className}Mapper;
import com.example.demo.service.BaseServiceImpl;
import com.example.demo.service.${packagePath}.${className}Service;

@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {

	private final ${className}Mapper ${objectName}Mapper;

    @Autowired
    public ${className}ServiceImpl(${className}Mapper ${objectName}Mapper) {
        super(${objectName}Mapper);
        this.${objectName}Mapper = ${objectName}Mapper;
    }
}