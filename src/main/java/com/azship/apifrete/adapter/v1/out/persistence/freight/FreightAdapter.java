package com.azship.apifrete.adapter.v1.out.persistence.freight;

import com.azship.apifrete.adapter.v1.out.persistence.carrier.CarrierAdapter;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.FreightEntity;
import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.entitiesEnum.FreightStatusEnum;
import com.azship.apifrete.adapter.v1.out.persistence.freight.mapper.FreightMapper;
import com.azship.apifrete.adapter.v1.out.persistence.freight.repository.FreightRepository;
import com.azship.apifrete.application.port.in.CarrierUseCase;
import com.azship.apifrete.application.port.out.FreightPort;
import com.azship.apifrete.config.customexception.EntityInUseException;
import com.azship.apifrete.config.customexception.FreightNotFoundException;
import com.azship.apifrete.config.customexception.NoPropertyFoundException;
import com.azship.apifrete.domain.Freight;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FreightAdapter implements FreightPort {

    private final FreightRepository freightRepository;

    private final FreightMapper freightMapper;

    private final CarrierUseCase carrierUseCase;

    public static final String MSG_FREIGHT_USED
            = "Frete de código %d não pode ser removido, pois está em uso";
    public static final String MSG_NO_PROPERTY
            = "Não existe propriedade %s para ordenação. Corrija e tente novamente";

    @Override
    public Freight findById(Long id) {
        return freightMapper.toDomain(freightRepository.findById(id)
                .orElseThrow(() -> new FreightNotFoundException(id)));
    }

    @Override
    public List<Freight> listAll() {
        return freightMapper.toDomainList(freightRepository.findAll());
    }


    @Override
    public Page<Freight> searchByAttribute(String attributeValue, Pageable pageable) {
        System.out.println("passou 0");
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

        Page<FreightEntity> freightsPage = null;
        try {
            freightsPage = freightRepository.findAll(specification, pageable);

        } catch (PropertyReferenceException e) {
            throw new NoPropertyFoundException(
                    String.format(MSG_NO_PROPERTY, e.getPropertyName())) ;
        }
        List<Freight> freightsDomain = freightMapper.toDomainList(freightsPage.getContent());
        Page<Freight> freightsDomainPage = new PageImpl<>(freightsDomain, pageable, freightsPage.getTotalElements());
        return freightsDomainPage;
    }

    @Override
    @Transactional
    public Freight save(Freight freight) {

        carrierUseCase.findById(freight.getCarrier().getId());

        freight.setOrderStatus(FreightStatusEnum.ORDER);
        freight.setOrderDate(OffsetDateTime.now(ZoneOffset.UTC));
        freight.setCarrierContact(UUID.randomUUID().toString());

        System.out.println(freight);

        return freightMapper.toDomain(freightRepository.save(freightMapper.toEntity(freight)));
    }

    @Transactional
    @Override
    public Freight update(Long id, Freight freight) {
        freight.setId(id);
        Freight currencyFreight = this.findById(id);
        freightMapper.copyToEntity(freight, currencyFreight);

        return freightMapper.toDomain(freightRepository.save(freightMapper.toEntity(currencyFreight)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var freight = findById(id);
        try {
            freightRepository.deleteById(freight.getId());
            freightRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_FREIGHT_USED, id));
        }
    }

}