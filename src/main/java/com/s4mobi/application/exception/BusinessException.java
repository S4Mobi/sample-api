package com.s4mobi.application.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 8614511473685769535L;

    public BusinessException(String message) {
        super(message);
    }
}
