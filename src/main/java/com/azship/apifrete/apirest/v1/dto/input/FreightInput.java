package com.azship.apifrete.apirest.v1.dto.input;

import com.azship.apifrete.domain.entities.entitiesEnum.FreightTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FreightInput {

    @NotNull
    @Enumerated(EnumType.STRING)
    private FreightTypeEnum type;

    @PositiveOrZero
    private double weight;

    @PositiveOrZero
    private double cubage;

    @NotBlank
    private String originAddress;

    @NotBlank
    private String destinationAddress;

    @NotNull
    @PositiveOrZero
    private BigDecimal cost;

//    private OffsetDateTime orderDate;

    private OffsetDateTime shipmentDate;

    private OffsetDateTime arrivalDate;

//    private String status;

    @Valid
    @NotNull
    private CarrierIdInput carrier;

//    private String carrierContact;

    @NotBlank
    private String paymentMethod;

    private String notes;

}
