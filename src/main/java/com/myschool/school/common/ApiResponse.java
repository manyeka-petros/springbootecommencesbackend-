package com.myschool.school.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class ApiResponse {


    private final boolean success;
    private final String message;


    private String getTimeStamp(
    ){
        return LocalDateTime.now().toString();
    }


}
