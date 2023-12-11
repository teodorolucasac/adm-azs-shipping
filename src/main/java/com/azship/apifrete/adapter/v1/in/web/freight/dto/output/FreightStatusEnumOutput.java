package com.azship.apifrete.apirest.v1.dto.output;

import java.util.Arrays;
import java.util.List;

public enum FreightStatusEnumOutput {

    ORDER("Criado"),
    SENT("Confirmado", ORDER),
    DELIVERED("Entregue", SENT),
    CANCELED("Cancelado", ORDER);

    private String description;
    private List<FreightStatusEnumOutput> oldStatus;

    FreightStatusEnumOutput(String description, FreightStatusEnumOutput... oldStatus) {
        this.description = description;
        this.oldStatus = Arrays.asList(oldStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean CannotChangeTo(FreightStatusEnumOutput newStatus) {
        return !newStatus.oldStatus.contains(this);
    }

    public boolean CanChangeTo(FreightStatusEnumOutput newStatus) {
        return !CannotChangeTo(newStatus);
    }
}
