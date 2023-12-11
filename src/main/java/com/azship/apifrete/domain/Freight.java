package com.azship.apifrete.domain;

import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@ToString
@Getter
@Setter
@Builder
public class Freight {

    private Long id;
    private FreightTypeEnum type;
    private double weight;
    private double cubage;
    private String originAddress;
    private String destinationAddress;
    private BigDecimal cost;
    private OffsetDateTime orderDate;
    private OffsetDateTime shipmentDate;
    private OffsetDateTime arrivalDate;
    private FreightStatusEnum orderStatus;
    private Carrier carrier;
    private String carrierContact;
    private String paymentMethod;
    private String notes;

}
