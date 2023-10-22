package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class TaskPriceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskPriceException(String msg) {
        super(msg);
    }

    public TaskPriceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
