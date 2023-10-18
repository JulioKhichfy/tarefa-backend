package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class DateFormatterException  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DateFormatterException(String msg) {
        super(msg);
    }

    public DateFormatterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
