package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class TaskNameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskNameException(String msg) {
        super(msg);
    }

    public TaskNameException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
