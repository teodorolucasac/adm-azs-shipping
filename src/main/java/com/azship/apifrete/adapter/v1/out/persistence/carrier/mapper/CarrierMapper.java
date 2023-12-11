package com.azship.apifrete.adapter.v1.out.persistence.carrier.mapper;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.dto.output.CarrierOutput;
import com.azship.apifrete.adapter.v1.out.persistence.carrier.entities.CarrierEntity;
import com.azship.apifrete.domain.Carrier;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarrierMapper {

    private final ModelMapper modelMapper;

    public CarrierEntity toEntity(Carrier carrier) {
        if( carrier == null) {
            return null;
        }
        return CarrierEntity.builder()
                .id(carrier.getId())
                .name(carrier.getName())
                .build();
    }


    public Carrier toDomain(Optional<CarrierEntity> carrierEntity) {
        return Carrier.builder()
                .id(carrierEntity.get().getId())
                .name(carrierEntity.get().getName())
                .build();
    }

    public Carrier toDomain(CarrierEntity carrierEntity) {
        return Carrier.builder()
                .id(carrierEntity.getId())
                .name(carrierEntity.getName())
                .build();
    }

    public List<Carrier> toDomainList(List<CarrierEntity> carrierEntities) {
        return carrierEntities.stream()
                .map(item -> this.toDomain(item))
                .collect(Collectors.toList());
    }

    public void copyToEntity(Carrier carrier, Carrier currencyCarrier) {

        modelMapper.map(carrier, currencyCarrier);
    }


}
