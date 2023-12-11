package com.azship.apifrete.adapter.v1.out.persistence.carrier;

import com.azship.apifrete.adapter.v1.out.persistence.carrier.mapper.CarrierMapper;
import com.azship.apifrete.adapter.v1.out.persistence.carrier.repository.CarrierRepository;
import com.azship.apifrete.application.port.out.CarrierPort;
import com.azship.apifrete.config.customexception.CarrierNotFoundException;
import com.azship.apifrete.config.customexception.EntityInUseException;
import com.azship.apifrete.domain.Carrier;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierAdapter implements CarrierPort {

    private final CarrierRepository carrierRepository;

    private final CarrierMapper carrierMapper;

    public static final String MSG_CARRIER_USED
            = "Transportadora de código %d não pode ser removida, pois está em uso";

    @Override
    public Carrier findById(Long id) {
        return carrierMapper.toDomain(carrierRepository.findById(id)
                .orElseThrow(() -> new CarrierNotFoundException(id)));
    }

    @Override
    public List<Carrier> listAll() {
        return carrierMapper.toDomainList(carrierRepository.findAll());
    }

    @Transactional
    @Override
    public Carrier save(Carrier carrier) {
        return carrierMapper.toDomain(carrierRepository.save(carrierMapper.toEntity(carrier)));
    }

    @Transactional
    @Override
    public Carrier update(Long id, Carrier carrier) {
        carrier.setId(id);
        Carrier currencyCarrier = this.findById(id);
        carrierMapper.copyToEntity(carrier, currencyCarrier);

        return carrierMapper.toDomain(carrierRepository.save(carrierMapper.toEntity(currencyCarrier)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var carrier = this.findById(id);
        try {
            carrierRepository.deleteById(carrier.getId());
            carrierRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_CARRIER_USED, id));
        }
    }
}
