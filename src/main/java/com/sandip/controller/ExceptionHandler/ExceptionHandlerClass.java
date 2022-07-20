package com.sandip.controller.ExceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {
    


    @ExceptionHandler(Exception.class)
    public String firstException(Exception e, Model model){
        return "access_denied";
    }




}
