package com.azship.apifrete.adapter.v1.in.web.freight.mapper;

import com.azship.apifrete.adapter.v1.in.web.carrier.mapper.CarrierDtoMapper;
import com.azship.apifrete.adapter.v1.in.web.freight.dto.input.FreightInput;
import com.azship.apifrete.adapter.v1.in.web.freight.dto.output.FreightOutput;
import com.azship.apifrete.domain.Freight;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FreightDtoMapper {

    private final CarrierDtoMapper carrierDtoMapper;

    public Freight toDomain(FreightInput freightInput) {
        if( freightInput == null) {
            return null;
        }
        return Freight.builder()
                .type(freightInput.getType())
                .weight(freightInput.getWeight())
                .cubage(freightInput.getCubage())
                .originAddress(freightInput.getOriginAddress())
                .destinationAddress(freightInput.getDestinationAddress())
                .cost(freightInput.getCost())
                .shipmentDate(freightInput.getShipmentDate())
                .arrivalDate(freightInput.getArrivalDate())
                .carrier(carrierDtoMapper.toDomain(freightInput.getCarrier()))
                .paymentMethod(freightInput.getPaymentMethod())
                .notes(freightInput.getNotes())
                .build();
    }

    public FreightOutput toDto(Freight freight) {
        if( freight == null) {
            return null;
        }
        return FreightOutput.builder()
                .id(freight.getId())
                .type(freight.getType().toString())
                .weight(freight.getWeight())
                .cubage(freight.getCubage())
                .originAddress(freight.getOriginAddress())
                .destinationAddress(freight.getDestinationAddress())
                .cost(freight.getCost())
                .orderDate(freight.getOrderDate())
                .shipmentDate(freight.getShipmentDate())
                .arrivalDate(freight.getArrivalDate())
                .orderStatus(freight.getOrderStatus().toString())
                .carrier(carrierDtoMapper.toDto(freight.getCarrier()))
                .carrierContact(freight.getCarrierContact())
                .paymentMethod(freight.getPaymentMethod())
                .notes(freight.getNotes())
                .build();
    }

    public List<FreightOutput> toDtoList(List<Freight> freights) {
        return freights.stream()
                .map(item -> this.toDto(item))
                .collect(Collectors.toList());
    }

//    public Page<FreightOutput> toDtoList(List<Freight> freights) {
//        return freights.stream()
//                .map(item -> this.toDto(item))
//                .collect(Collectors.toList());
//    }

}
