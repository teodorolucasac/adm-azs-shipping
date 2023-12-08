package com.azship.apifrete.apirest.v1.controller;

import com.azship.apifrete.apirest.v1.dto.input.CarrierInput;
import com.azship.apifrete.apirest.v1.mapper.CarrierMapper;
import com.azship.apifrete.domain.entities.CarrierEntity;
import com.azship.apifrete.domain.repository.CarrierRepository;
import com.azship.apifrete.domain.service.CarrierService;
import com.azship.apifrete.apirest.v1.dto.output.CarrierOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/carrier")
public class CarrierController {

    @Autowired
    private CarrierRepository carrierRepository;

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private CarrierMapper carrierMapper;

    @GetMapping("/{id}")
    public CarrierOutput findById(@PathVariable Long id) {
        return carrierMapper.toDto(carrierService.findOrFail(id));
    }

    @GetMapping
    public List<CarrierOutput> list() {
        return carrierMapper.toDtoList(carrierRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarrierOutput adicionar(@RequestBody @Valid CarrierInput carrierInput) {
        CarrierEntity carrierEntity = carrierMapper.toEntity(carrierInput);

        return carrierMapper.toDto(carrierRepository.save(carrierEntity));
    }

    @PutMapping("/{id}")
    public CarrierOutput update(@PathVariable Long id, @RequestBody @Valid CarrierInput carrierInput) {
        CarrierEntity currencyCarrier = carrierService.findOrFail(id);
        carrierMapper.copyToEntity(carrierInput, currencyCarrier);

        return carrierMapper.toDto(carrierService.save(currencyCarrier));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carrierService.delete(id);
    }
}
