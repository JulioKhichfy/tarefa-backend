package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class InvalidDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String msg) {
        super(msg);
    }

    public InvalidDateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
