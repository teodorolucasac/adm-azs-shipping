package com.azship.apifrete.adapter.v1.out.persistence.freight.mapper;

import com.azship.apifrete.adapter.v1.out.persistence.carrier.entities.CarrierEntity;
import com.azship.apifrete.adapter.v1.out.persistence.carrier.mapper.CarrierMapper;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.FreightEntity;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightTypeEnum;
import com.azship.apifrete.domain.Freight;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FreightMapper {

    private final ModelMapper modelMapper;

    private final CarrierMapper carrierMapper;

    public Freight toDomain(FreightEntity freightEntity) {
        if( freightEntity == null) {
            return null;
        }
        return Freight.builder()
                .id(freightEntity.getId())
                .type(FreightTypeEnum.valueOf(freightEntity.getType().toString()))
                .weight(freightEntity.getWeight())
                .cubage(freightEntity.getCubage())
                .originAddress(freightEntity.getOriginAddress())
                .destinationAddress(freightEntity.getDestinationAddress())
                .cost(freightEntity.getCost())
                .orderDate(freightEntity.getOrderDate())
                .shipmentDate(freightEntity.getShipmentDate())
                .arrivalDate(freightEntity.getArrivalDate())
                .orderStatus(FreightStatusEnum.valueOf(freightEntity.getOrderStatus().toString()))
                .carrier(carrierMapper.toDomain(freightEntity.getCarrier()))
                .carrierContact(freightEntity.getCarrierContact())
                .paymentMethod(freightEntity.getPaymentMethod())
                .notes(freightEntity.getNotes())
                .build();
    }

    public FreightEntity toEntity(Freight freight) {
        if( freight == null) {
            return null;
        }
        return FreightEntity.builder()
                .id(freight.getId())
                .type(freight.getType())
                .weight(freight.getWeight())
                .cubage(freight.getCubage())
                .originAddress(freight.getOriginAddress())
                .destinationAddress(freight.getDestinationAddress())
                .cost(freight.getCost())
                .orderDate(freight.getOrderDate())
                .shipmentDate(freight.getShipmentDate())
                .arrivalDate(freight.getArrivalDate())
                .orderStatus(freight.getOrderStatus())
                .carrier(carrierMapper.toEntity(freight.getCarrier()))
                .carrierContact(freight.getCarrierContact())
                .paymentMethod(freight.getPaymentMethod())
                .notes(freight.getNotes())
                .build();
    }

    public List<Freight> toDomainList(List<FreightEntity> freightEntities) {
        return freightEntities.stream()
                .map(item -> this.toDomain(item))
                .collect(Collectors.toList());
    }

    public void copyToEntity(Freight freight, Freight currencyFreight) {
        currencyFreight.setCarrier(carrierMapper.toDomain(new CarrierEntity()));
        System.out.println("freight: " + freight);
        System.out.println("currencyFreight: " + currencyFreight);

        modelMapper.map(freight, currencyFreight);
    }

}
