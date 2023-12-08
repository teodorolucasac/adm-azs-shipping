package com.azship.apifrete.apirest.v1.dto.output;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreightOutput {

    private Long id;
    private String type;
    private double weight;
    private double cubage;
    private String originAddress;
    private String destinationAddress;
    private BigDecimal cost;
    private OffsetDateTime orderDate;
    private OffsetDateTime shipmentDate;
    private OffsetDateTime arrivalDate;
    private String orderStatus;
    private CarrierOutput carrier;
    private String carrierContact;
    private String paymentMethod;
    private String notes;

}
