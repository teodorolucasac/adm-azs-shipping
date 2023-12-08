package com.azship.apifrete.apirest.v1.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CarrierIdInput {

    @NotNull
    private Long id;
}
