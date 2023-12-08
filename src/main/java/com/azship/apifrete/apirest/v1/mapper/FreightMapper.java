package com.azship.apifrete.apirest.v1.mapper;

import com.azship.apifrete.apirest.v1.dto.input.FreightInput;
import com.azship.apifrete.apirest.v1.dto.output.FreightOutput;
import com.azship.apifrete.domain.entities.CarrierEntity;
import com.azship.apifrete.domain.entities.FreightEntity;
import com.azship.apifrete.domain.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.domain.entities.entitiesEnum.FreightTypeEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FreightMapper {

    private final ModelMapper modelMapper;

    private final CarrierMapper carrierMapper;

//    private final FreightStatusMapper freightStatusMapper;

    public FreightEntity toEntity(FreightOutput freightOutput) {
        if( freightOutput == null) {
            return null;
        }
        return FreightEntity.builder()
                .id(freightOutput.getId())
                .type(FreightTypeEnum.valueOf(freightOutput.getType().toString()))
                .weight(freightOutput.getWeight())
                .cubage(freightOutput.getCubage())
                .originAddress(freightOutput.getOriginAddress())
                .destinationAddress(freightOutput.getDestinationAddress())
                .cost(freightOutput.getCost())
                .orderDate(freightOutput.getOrderDate())
                .shipmentDate(freightOutput.getShipmentDate())
                .arrivalDate(freightOutput.getArrivalDate())
                .orderStatus(FreightStatusEnum.valueOf(freightOutput.getOrderStatus().toString()))
                .carrier(carrierMapper.toEntity(freightOutput.getCarrier()))
                .carrierContact(freightOutput.getCarrierContact())
                .paymentMethod(freightOutput.getPaymentMethod())
                .notes(freightOutput.getNotes())
                .build();
    }

    public FreightEntity toEntity(FreightInput freightInput) {
        if( freightInput == null) {
            return null;
        }
        return FreightEntity.builder()
                .type(freightInput.getType())
                .weight(freightInput.getWeight())
                .cubage(freightInput.getCubage())
                .originAddress(freightInput.getOriginAddress())
                .destinationAddress(freightInput.getDestinationAddress())
                .cost(freightInput.getCost())
                .shipmentDate(freightInput.getShipmentDate())
                .arrivalDate(freightInput.getArrivalDate())
                .carrier(carrierMapper.toEntity(freightInput.getCarrier()))
                .paymentMethod(freightInput.getPaymentMethod())
                .notes(freightInput.getNotes())
                .build();
    }

    public FreightOutput toDto(FreightEntity freightEntity) {
        return FreightOutput.builder()
                .id(freightEntity.getId())
                .type(freightEntity.getType().toString())
                .weight(freightEntity.getWeight())
                .cubage(freightEntity.getCubage())
                .originAddress(freightEntity.getOriginAddress())
                .destinationAddress(freightEntity.getDestinationAddress())
                .cost(freightEntity.getCost())
                .orderDate(freightEntity.getOrderDate())
                .shipmentDate(freightEntity.getShipmentDate())
                .arrivalDate(freightEntity.getArrivalDate())
                .orderStatus(freightEntity.getOrderStatus().toString())
                .carrier(carrierMapper.toDto(freightEntity.getCarrier()))
                .carrierContact(freightEntity.getCarrierContact())
                .paymentMethod(freightEntity.getPaymentMethod())
                .notes(freightEntity.getNotes())
                .build();
    }

    public List<FreightEntity> toEntityList(List<FreightOutput> freightsOutput) {
        return freightsOutput.stream()
                .map(freightOutput -> toEntity(freightOutput))
                .collect(Collectors.toList());
    }

    public List<FreightOutput> toDtoList(List<FreightEntity> freightsEntities) {
        return freightsEntities.stream()
                .map(freightEntity -> toDto(freightEntity))
                .collect(Collectors.toList());
    }

    public void copyToEntity(FreightInput freightInput, FreightEntity freightEntity) {
        freightEntity.setCarrier(new CarrierEntity());

        modelMapper.map(freightInput, freightEntity);
    }
    
}
