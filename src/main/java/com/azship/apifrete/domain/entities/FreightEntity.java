package com.azship.apifrete.domain.entities;

import com.azship.apifrete.domain.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.domain.entities.entitiesEnum.FreightTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity(name = "tb_freight")
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class FreightEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    private OffsetDateTime orderDate;
    private OffsetDateTime shipmentDate;
    private OffsetDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    private FreightStatusEnum orderStatus;

    @Valid
    @OneToOne
    private CarrierEntity carrier;

    private String carrierContact;

    @NotBlank
    private String paymentMethod;

    private String notes;

}
