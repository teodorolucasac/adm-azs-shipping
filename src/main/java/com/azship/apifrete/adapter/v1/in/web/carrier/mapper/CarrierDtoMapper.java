package com.azship.apifrete.adapter.v1.in.web.carrier.mapper;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierIdInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.dto.output.CarrierOutput;
import com.azship.apifrete.domain.Carrier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarrierDtoMapper {

    public Carrier toDomain(CarrierInput carrierInput) {
        if( carrierInput == null) {
            return null;
        }
        return Carrier.builder()
                .name(carrierInput.getName())
                .build();
    }

    public Carrier toDomain(CarrierIdInput carrierIdInput) {
        if (carrierIdInput == null) {
            return null;
        }
        return Carrier.builder()
                .id(carrierIdInput.getId())
                .build();
    }

    public CarrierOutput toDto(Carrier carrier) {
        return CarrierOutput.builder()
                .id(carrier.getId())
                .name(carrier.getName())
                .build();
    }

    public List<CarrierOutput> toDtoList(List<Carrier> carriersEntities) {
        return carriersEntities.stream()
                .map(carrierEntity -> toDto(carrierEntity))
                .collect(Collectors.toList());
    }
    
}
