package com.azship.apifrete.application.service;

import com.azship.apifrete.application.port.in.CarrierUseCase;
import com.azship.apifrete.application.port.out.CarrierPort;
import com.azship.apifrete.domain.Carrier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierService implements CarrierUseCase {

    private final CarrierPort carrierPort;

    @Override
    public Carrier findById(Long id) {
        return carrierPort.findById(id);
    }

    @Override
    public List<Carrier> listAll() {
        return carrierPort.listAll();
    }

    @Override
    public Carrier save(Carrier carrier) {
        return carrierPort.save(carrier);
    }

    @Override
    public Carrier update(Long id, Carrier carrier) {
        return carrierPort.update(id, carrier);
    }

    @Override
    public void deleteById(Long id) {
        carrierPort.deleteById(id);
    }

}
