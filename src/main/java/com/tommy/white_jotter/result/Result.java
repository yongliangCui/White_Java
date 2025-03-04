package com.tommy.white_jotter.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object result;
}
