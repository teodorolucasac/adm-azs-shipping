package com.azship.apifrete.apirest.v1.mapper;

import com.azship.apifrete.apirest.v1.dto.input.CarrierIdInput;
import com.azship.apifrete.apirest.v1.dto.input.CarrierInput;
import com.azship.apifrete.domain.entities.CarrierEntity;
import com.azship.apifrete.apirest.v1.dto.output.CarrierOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarrierMapper {

    private final ModelMapper modelMapper;

    public CarrierEntity toEntity(CarrierOutput carrierOutput) {
        if( carrierOutput == null) {
            return null;
        }
        return CarrierEntity.builder()
                .id(carrierOutput.getId())
                .name(carrierOutput.getName())
                .build();
    }

    public CarrierEntity toEntity(CarrierInput carrierInput) {
        if( carrierInput == null) {
            return null;
        }
        return CarrierEntity.builder()
                .name(carrierInput.getName())
                .build();
    }

    public CarrierEntity toEntity(CarrierIdInput carrierIdInput) {
        if (carrierIdInput == null) {
            return null;
        }
        return CarrierEntity.builder()
                .id(carrierIdInput.getId())
                .build();
    }

    public CarrierOutput toDto(CarrierEntity carrierEntity) {
        return CarrierOutput.builder()
                .id(carrierEntity.getId())
                .name(carrierEntity.getName())
                .build();
    }

    public List<CarrierEntity> toEntityList(List<CarrierOutput> carriersOutput) {
        return carriersOutput.stream()
                .map(carrierOutput -> toEntity(carrierOutput))
                .collect(Collectors.toList());
    }

    public List<CarrierOutput> toDtoList(List<CarrierEntity> carriersEntities) {
        return carriersEntities.stream()
                .map(carrierEntity -> toDto(carrierEntity))
                .collect(Collectors.toList());
    }

    public void copyToEntity(CarrierInput carrierInput, CarrierEntity carrierEntity) {

        modelMapper.map(carrierInput, carrierEntity);
    }
    
}
