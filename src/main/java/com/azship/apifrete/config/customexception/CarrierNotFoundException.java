package com.azship.apifrete.config.customexception;

public class CarrierNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CarrierNotFoundException(String message) {
        super(message);
    }

    public CarrierNotFoundException(Long id) {
        this(String.format("Não existe cadastro de transportadora com código %d", id));
    }
}
