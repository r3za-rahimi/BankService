package com.asan.bank.aspects;

import lombok.Data;

@Data
public class LogModel {

    private String id;
    private String methodName;
    private Object request;
    private Object response;
    private String errorTrace;
}
