package com.azship.apifrete.config.customexception;

public class NoPropertyFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NoPropertyFoundException(String message) {
        super(message);
    }
}
