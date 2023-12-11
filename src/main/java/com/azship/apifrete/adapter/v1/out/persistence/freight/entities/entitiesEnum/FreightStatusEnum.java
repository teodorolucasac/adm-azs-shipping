package com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum FreightStatusEnum {

    ORDER("Criado"),
    SENT("Confirmado", ORDER),
    DELIVERED("Entregue", SENT),
    CANCELED("Cancelado", ORDER);

    private String description;
    private List<FreightStatusEnum> oldStatus;

    FreightStatusEnum(String description, FreightStatusEnum... oldStatus) {
        this.description = description;
        this.oldStatus = Arrays.asList(oldStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean CannotChangeTo(FreightStatusEnum newStatus) {
        return !newStatus.oldStatus.contains(this);
    }

    public boolean CanChangeTo(FreightStatusEnum newStatus) {
        return !CannotChangeTo(newStatus);
    }
}
