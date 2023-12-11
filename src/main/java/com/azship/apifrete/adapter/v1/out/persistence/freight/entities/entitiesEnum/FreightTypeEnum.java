package com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum;

import lombok.Getter;

@Getter
public enum FreightTypeEnum {
    GROUND("Terrestre"),
    AIR("Aéreo"),
    MARITIME("Marítimo");

    private String description;

    FreightTypeEnum(String description) {
        this.description = description;
    }
}
