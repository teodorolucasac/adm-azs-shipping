package com.azship.apifrete.adapter.v1.out.persistence.freight.repository;

import com.azship.apifrete.adapter.v1.out.persistence.freight.entities.FreightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightRepository extends JpaRepository<FreightEntity, Long>,
        JpaSpecificationExecutor<FreightEntity> {
}
