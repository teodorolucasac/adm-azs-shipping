package com.azship.apifrete.adapter.v1.in.web.carrier.controller;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.dto.output.CarrierOutput;
import com.azship.apifrete.adapter.v1.in.web.carrier.mapper.CarrierDtoMapper;
import com.azship.apifrete.application.port.in.CarrierUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/carrier")
public class CarrierController {

    private final CarrierUseCase carrierUseCase;

    private final CarrierDtoMapper carrierDtoMapper;

    @GetMapping(path = "/{id}")
    public CarrierOutput findById(@PathVariable Long id) {
        return carrierDtoMapper.toDto(carrierUseCase.findById(id));
    }

    @GetMapping
    public List<CarrierOutput> listAll() {
        return carrierDtoMapper.toDtoList(carrierUseCase.listAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarrierOutput save(@RequestBody @Valid CarrierInput carrierInput) {
        return carrierDtoMapper.toDto(carrierUseCase.save(carrierDtoMapper.toDomain(carrierInput)));
    }

    @PutMapping("/{id}")
    public CarrierOutput update(@PathVariable Long id, @RequestBody @Valid CarrierInput carrierInput) {
        return carrierDtoMapper.toDto(carrierUseCase.update(id, carrierDtoMapper.toDomain(carrierInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carrierUseCase.deleteById(id);
    }
}
