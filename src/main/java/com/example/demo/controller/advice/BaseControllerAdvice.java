package com.example.demo.controller.advice;

import com.example.demo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: hanDa
 * @Date: 2020/9/25 9:42
 * @Version:1.0
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e){
          log.error(e.getMessage(),e);
      return ResponseEntity.ok(new Result<>(500,e.getMessage()));
    }

    @InitBinder
    public  void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor=new CustomDateEditor(sdf, false);
        webDataBinder.registerCustomEditor(Date.class, editor);
    }

    @ModelAttribute
    public void modelAttribute(Model model){
        model.addAttribute("global","全局变量");
    }
}