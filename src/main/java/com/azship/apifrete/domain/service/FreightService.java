package com.azship.apifrete.domain.service;

import com.azship.apifrete.domain.entities.FreightEntity;
import com.azship.apifrete.domain.exception.FreightNotFoundException;
import com.azship.apifrete.domain.exception.EntityInUseException;
import com.azship.apifrete.domain.repository.FreightRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

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
public class FreightService {

    public static final String MSG_FREIGHT_USED
            = "Frete de código %d não pode ser removido, pois está em uso";

    @Autowired
    private FreightRepository freightRepository;

    @Transactional
    public FreightEntity save(FreightEntity freightEntity) {
        return freightRepository.save(freightEntity);
    }

    public List<FreightEntity> list() {
        return freightRepository.findAll();
    }

    public Optional<FreightEntity> find(Long id) {
        return freightRepository.findById(id);
    }


    public List<FreightEntity> buscarPorAttributo(String attributeValue) {
        Specification<FreightEntity> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // INTEGER
            try {
                predicates.add(criteriaBuilder.equal(root.get("id"), Integer.parseInt(attributeValue)));
            } catch (NumberFormatException e) { }

            // STRING
            try {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + attributeValue.toLowerCase() + "%"));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("originAddress")), "%" + attributeValue.toLowerCase() + "%"));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("destinationAddress")), "%" + attributeValue.toLowerCase() + "%"));
            } catch (ClassCastException e) { }

            // DOUBLE
            try {
                predicates.add(criteriaBuilder.equal(root.get("weight"), Double.parseDouble(attributeValue)));
                predicates.add(criteriaBuilder.equal(root.get("cubage"), Double.parseDouble(attributeValue)));
            } catch (NumberFormatException e) { }

            // BIGDECIMAL
            try {
                predicates.add(criteriaBuilder.equal(root.get("cost"), new BigDecimal(attributeValue).setScale(2, RoundingMode.HALF_UP)));
            } catch (NumberFormatException | ArithmeticException e) { }

            // OFFSETDATETIME
            try {
                OffsetDateTime dateTime = OffsetDateTime.parse(attributeValue);
                predicates.add(criteriaBuilder.equal(root.get("orderDate"), dateTime));
                predicates.add(criteriaBuilder.equal(root.get("shipmentDate"), dateTime));
                predicates.add(criteriaBuilder.equal(root.get("arrivalDate"), dateTime));
            } catch (DateTimeParseException e) { }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };


        return freightRepository.findAll(specification);
    }

    @Transactional
    public void delete(Long id) {
        try {
            freightRepository.deleteById(id);
            freightRepository.flush();

        } catch(EmptyResultDataAccessException e) {
            throw new FreightNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_FREIGHT_USED, id));
        }
    }

    public FreightEntity findOrFail(Long id) {
        return freightRepository.findById(id)
                .orElseThrow(() -> new FreightNotFoundException(id));
    }
}
