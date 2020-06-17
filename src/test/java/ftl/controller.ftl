package com.example.demo.controller.${packagePath};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.${packagePath}.${className}Service;

@RestController
@RequestMapping("/${objectName}")
public class ${className}Controller {

	@Autowired
	private ${className}Service ${objectName}Service;

}
