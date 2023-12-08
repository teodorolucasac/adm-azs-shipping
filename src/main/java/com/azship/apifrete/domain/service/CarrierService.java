package com.azship.apifrete.domain.service;

import com.azship.apifrete.domain.entities.CarrierEntity;
import com.azship.apifrete.domain.exception.CarrierNotFoundException;
import com.azship.apifrete.domain.exception.EntityInUseException;
import com.azship.apifrete.domain.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {

    public static final String MSG_CARRIER_USED
            = "Transportadora de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CarrierRepository carrierRepository;

    @Transactional
    public CarrierEntity save(CarrierEntity carrierEntity) {
        return carrierRepository.save(carrierEntity);
    }

    public List<CarrierEntity> list() {
        return carrierRepository.findAll();
    }

    public Optional<CarrierEntity> find(Long id) {
        return carrierRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        try {
            carrierRepository.deleteById(id);
            carrierRepository.flush();

        } catch(EmptyResultDataAccessException e) {
            throw new CarrierNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CARRIER_USED, id));
        }
    }

    public CarrierEntity findOrFail(Long id) {
        return carrierRepository.findById(id)
                .orElseThrow(() -> new CarrierNotFoundException(id));
    }
}
