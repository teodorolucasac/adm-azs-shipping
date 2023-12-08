package com.azship.apifrete.apirest.v1.controller;

import com.azship.apifrete.apirest.v1.dto.input.FreightInput;
import com.azship.apifrete.apirest.v1.dto.output.FreightOutput;
import com.azship.apifrete.apirest.v1.mapper.FreightMapper;
import com.azship.apifrete.domain.entities.FreightEntity;
import com.azship.apifrete.domain.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.domain.exception.BusinessException;
import com.azship.apifrete.domain.exception.CarrierNotFoundException;
import com.azship.apifrete.domain.repository.FreightRepository;
import com.azship.apifrete.domain.service.FreightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private FreightService freightService;

    @Autowired
    private FreightMapper freightMapper;

    @GetMapping("/{id}")
    public FreightOutput findById(@PathVariable Long id) {
        return freightMapper.toDto(freightService.findOrFail(id));
    }

    @GetMapping
    public List<FreightOutput> list() {
        return freightMapper.toDtoList(freightRepository.findAll());
    }

    @GetMapping("/search")
    public List<FreightEntity> searchByAttribute(@RequestParam String attributeValue) {
        return freightService.buscarPorAttributo(attributeValue);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FreightOutput adicionar(@RequestBody @Valid FreightInput freightInput) {
        FreightEntity freightEntity = freightMapper.toEntity(freightInput);
        freightEntity.setOrderDate(OffsetDateTime.now(ZoneOffset.UTC));
        freightEntity.setOrderStatus(FreightStatusEnum.ORDER);
        freightEntity.setCarrierContact(UUID.randomUUID().toString());


        return freightMapper.toDto(freightRepository.save(freightEntity));
    }

    @PutMapping("/{id}")
    public FreightOutput update(@PathVariable Long id, @RequestBody @Valid FreightInput freightInput) {

        try{
            FreightEntity currencyFreight = freightService.findOrFail(id);
            freightMapper.copyToEntity(freightInput, currencyFreight);
            return freightMapper.toDto(freightService.save(currencyFreight));

        }catch (CarrierNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        freightService.delete(id);
    }
}
