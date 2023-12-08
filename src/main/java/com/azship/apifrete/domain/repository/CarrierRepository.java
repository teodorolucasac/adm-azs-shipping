package com.azship.apifrete.domain.repository;

import com.azship.apifrete.domain.entities.CarrierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<CarrierEntity, Long> {
}
