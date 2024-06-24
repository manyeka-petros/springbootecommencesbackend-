package com.myschool.school.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomException extends IllegalArgumentException{

    public CustomException(String msg){
        super(msg);
    }
}
