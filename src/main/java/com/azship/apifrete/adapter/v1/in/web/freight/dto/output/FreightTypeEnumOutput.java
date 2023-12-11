package com.azship.apifrete.apirest.v1.dto.output;

public enum FreightTypeEnumOutput {
    GROUND("Terrestre"),
    AIR("Aéreo"),
    MARITIME("Marítimo");

    private String description;

    FreightTypeEnumOutput(String description) {
        this.description = description;
    }
}
