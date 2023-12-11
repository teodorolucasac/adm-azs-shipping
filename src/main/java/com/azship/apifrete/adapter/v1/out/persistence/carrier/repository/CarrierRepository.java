package com.azship.apifrete.adapter.v1.out.persistence.carrier.repository;

import com.azship.apifrete.adapter.v1.out.persistence.carrier.entities.CarrierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<CarrierEntity, Long> {
}
