package com.azship.apifrete.domain.exception;

public class FreightNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public FreightNotFoundException(String message) {
        super(message);
    }

    public FreightNotFoundException(Long id) {
        this(String.format("Não existe cadastro de frete com código %d", id));
    }
}
