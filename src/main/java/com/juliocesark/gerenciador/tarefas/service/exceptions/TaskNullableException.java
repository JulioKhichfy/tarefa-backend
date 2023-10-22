package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class TaskNullableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskNullableException(String msg) {
        super(msg);
    }

    public TaskNullableException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
