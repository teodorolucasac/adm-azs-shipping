package com.azship.apifrete.adapter.v1.in.web.freight.controller;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.dto.output.CarrierOutput;
import com.azship.apifrete.adapter.v1.in.web.freight.dto.input.FreightInput;
import com.azship.apifrete.adapter.v1.in.web.freight.dto.output.FreightOutput;
import com.azship.apifrete.adapter.v1.in.web.freight.mapper.FreightDtoMapper;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.FreightEntity;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.application.port.in.FreightUseCase;
import com.azship.apifrete.domain.Freight;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/freight")
public class FreightController {

    private final FreightUseCase freightUseCase;

    private final FreightDtoMapper freightDtoMapper;

    @GetMapping("/{id}")
    public FreightOutput findById(@PathVariable Long id) {
        return freightDtoMapper.toDto(freightUseCase.findById(id));
    }

    @GetMapping
    public List<FreightOutput> listAll() {
        return freightDtoMapper.toDtoList(freightUseCase.listAll());
    }

    @GetMapping(path = "/search")
    public Page<FreightOutput> searchByAttribute(@RequestParam String attributeValue, Pageable pageable) {

        Page<Freight> freightPage = freightUseCase.searchByAttribute(attributeValue, pageable);
        List<FreightOutput> freightDto = freightDtoMapper.toDtoList(freightPage.getContent());
        Page<FreightOutput> freightDtoPage = new PageImpl<>(freightDto, pageable, freightPage.getTotalElements());
        return freightDtoPage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FreightOutput save(@RequestBody @Valid FreightInput freightInput) {
        return freightDtoMapper.toDto(freightUseCase.save(freightDtoMapper.toDomain(freightInput)));
    }

    @PutMapping("/{id}")
    public FreightOutput update(@PathVariable Long id, @RequestBody @Valid FreightInput freightInput) {
        return freightDtoMapper.toDto(freightUseCase.update(id, freightDtoMapper.toDomain(freightInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        freightUseCase.deleteById(id);
    }
}
