package com.azship.apifrete.domain.repository;

import com.azship.apifrete.domain.entities.FreightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreightRepository extends JpaRepository<FreightEntity, Long>,
        JpaSpecificationExecutor<FreightEntity> {
}
