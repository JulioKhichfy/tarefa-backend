package com.juliocesark.gerenciador.tarefas.service.exceptions;

public class PriceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PriceException(String msg) {
        super(msg);
    }

    public PriceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
