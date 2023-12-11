package com.azship.apifrete.config.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    INVALID_DATA("/dados-invalidos", "Dados inválidos"),
    SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
    INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
    INCOMPREHENSIVE_MESSAGE("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTITY_NOT_FOUND("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
    BUSINESS_ERROR("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "http://algafood.com.br" + path;
        this.title = title;
    }
}
