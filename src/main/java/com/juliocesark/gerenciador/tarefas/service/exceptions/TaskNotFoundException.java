package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class TaskNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(String msg) {
        super(msg);
    }

    public TaskNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
