package com.azship.apifrete;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.mapper.CarrierDtoMapper;
import com.azship.apifrete.adapter.v1.out.persistence.carrier.repository.CarrierRepository;
import com.azship.apifrete.application.port.in.CarrierUseCase;
import com.azship.apifrete.config.customexception.CarrierNotFoundException;
import com.azship.apifrete.config.customexception.EntityInUseException;
import com.azship.apifrete.domain.Carrier;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CRUDCarrierIntegrationTest {

	@Autowired
	private CarrierUseCase carrierUseCase;
	@Autowired
	private CarrierDtoMapper carrierDtoMapper;
	@Autowired
	private CarrierRepository carrierRepository;

	@Test
	void whenSaveCarrier_ThenSucessWithId() {
		CarrierInput carrierInput = new CarrierInput();
		carrierInput.setName("Transport Test S/A");

		Carrier carrier = carrierUseCase.save(carrierDtoMapper.toDomain(carrierInput));

		assertThat(carrier).isNotNull();
		assertThat(carrier.getId()).isNotNull();
	}

	@Test
	void whenSaveCarrierNoName_ThenIsShouldFail() {
		org.junit.jupiter.api.Assertions.assertThrows(ConstraintViolationException.class, () -> {
			CarrierInput carrierInput = new CarrierInput();
			carrierInput.setName("");

			carrierUseCase.save(carrierDtoMapper.toDomain(carrierInput));
		});
	}

	@Test
	public void whenDeleteACarrierInUse_ThenIsShouldFail() {
		assertThrows(EntityInUseException.class, () -> {
			carrierUseCase.deleteById(1L);
		});
	}

	@Test
	public void whenDeleteANonexistentCarrier_ThenIsShouldFail() {
		assertThrows(CarrierNotFoundException.class, () -> {
			carrierUseCase.deleteById(100L);
		});
	}

}
