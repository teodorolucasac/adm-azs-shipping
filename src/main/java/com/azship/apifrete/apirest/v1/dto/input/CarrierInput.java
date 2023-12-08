package com.azship.apifrete.apirest.v1.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CarrierInput {

    @NotNull
    private String name;
}
