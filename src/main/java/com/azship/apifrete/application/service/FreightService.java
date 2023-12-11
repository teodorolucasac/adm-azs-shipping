package com.azship.apifrete.application.service;

import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.FreightEntity;
import com.azship.apifrete.application.port.in.FreightUseCase;
import com.azship.apifrete.application.port.out.FreightPort;
import com.azship.apifrete.config.customexception.FreightNotFoundException;
import com.azship.apifrete.config.customexception.EntityInUseException;
import com.azship.apifrete.adapter.v1.out.persistence.freight.repository.FreightRepository;
import com.azship.apifrete.domain.Freight;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreightService implements FreightUseCase {

    private final FreightPort freightPort;

    @Override
    public Freight findById(Long id) {
        return freightPort.findById(id);
    }

    @Override
    public List<Freight> listAll() {
        return freightPort.listAll();
    }

    @Override
    public Page<Freight> searchByAttribute(String attributeValue, Pageable pageable) {
        return freightPort.searchByAttribute(attributeValue, pageable);
    }

    @Override
    public Freight save(Freight freight) {
        return freightPort.save(freight);
    }

    @Override
    public Freight update(Long id, Freight freight) {
        return freightPort.update(id, freight);
    }

    @Override
    public void deleteById(Long id) {
        freightPort.deleteById(id);
    }
}
