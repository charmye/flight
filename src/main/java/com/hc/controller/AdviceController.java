package com.hc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(Exception.class)
    public Object myExceptionHandler(Exception e){
        e.printStackTrace();
        return "mse";

    }



}
