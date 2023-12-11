package com.azship.apifrete.application.port.out;

import com.azship.apifrete.domain.Carrier;

import java.util.List;

public interface CarrierPort {

    Carrier findById(Long id);

    List<Carrier> listAll();

    Carrier save(Carrier carrier);

    Carrier update(Long id, Carrier carrier);

    void deleteById(Long id);
}
