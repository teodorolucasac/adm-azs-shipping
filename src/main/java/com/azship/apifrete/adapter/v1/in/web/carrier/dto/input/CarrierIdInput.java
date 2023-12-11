package com.azship.apifrete.adapter.v1.in.web.carrier.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrierIdInput {

    @NotNull
    private Long id;
}
