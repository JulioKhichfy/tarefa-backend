package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class TaskInvalidDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskInvalidDateException(String msg) {
        super(msg);
    }

    public TaskInvalidDateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
