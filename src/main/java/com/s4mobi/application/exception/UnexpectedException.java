package com.s4mobi.application.exception;

public class UnexpectedException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public UnexpectedException() {
        super("Ops, ocorreu um erro inesperado. Tente novamente mais tarde.");
    }

}
