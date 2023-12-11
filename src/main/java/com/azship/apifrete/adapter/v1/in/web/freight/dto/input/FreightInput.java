package com.azship.apifrete.adapter.v1.in.web.freight.dto.input;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierIdInput;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

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
