package com.azship.apifrete;

import com.azship.apifrete.adapter.v1.in.web.carrier.dto.input.CarrierInput;
import com.azship.apifrete.adapter.v1.in.web.carrier.mapper.CarrierDtoMapper;
import com.azship.apifrete.adapter.v1.out.persistence.carrier.repository.CarrierRepository;
import com.azship.apifrete.application.port.in.CarrierUseCase;
import com.azship.apifrete.domain.Carrier;
import com.azship.apifrete.util.DatabaseCleaner;
import com.azship.apifrete.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CRUDCarrierApiTest {

	private static final int CARRIER_ID_NONEXISTENT = 100;

	@LocalServerPort
	private int port;

	@Autowired
	private CarrierDtoMapper carrierDtoMapper;

	@Autowired
	CarrierRepository carrierRepository;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CarrierUseCase carrierUseCase;

	private Carrier carrierTest;
	private String jsonCorrectCarrier;
	private int registeredQuantity;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/v1/carrier";

		databaseCleaner.clearTables();
		prepareData();

		jsonCorrectCarrier = ResourceUtils.getContentFromResource(
				"/json/correctBody/carrier-test.json");
	}

	@Test
	public void whenGetCarrier_ThenReturnStatus200() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenFindCarrier_ThenReturnCorrectQuantity() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(registeredQuantity))
			.body("name", hasItems("ABC Transportes", "XYZ Transportes"));
	}

	@Test
	public void whenSaveCarrier_ThenReturnStatus201() {
		given()
				.body("{ \"name\": \"Teste Transportes\" }")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void whenFindCarrierExitent_ThenReturnCorrectResponseAndStatus() {
		given()
				.pathParam("id", carrierTest.getId())
				.accept(ContentType.JSON)
			.when()
				.get("/{id}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("name", equalTo(carrierTest.getName()));
	}

	@Test
	public void whenFindCarrierNonexistent_ThenReturnStatus404() {
		given()
				.pathParam("id", CARRIER_ID_NONEXISTENT)
				.accept(ContentType.JSON)
			.when()
				.get("/{id}")
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepareData() {
		CarrierInput carrier01 = new CarrierInput();
		carrier01.setName("ABC Transportes");
		Carrier carrier1 = carrierUseCase.save(carrierDtoMapper.toDomain(carrier01));

		CarrierInput carrier02 = new CarrierInput();
		carrier02.setName("XYZ Transportes");
		carrierTest = carrierUseCase.save(carrierDtoMapper.toDomain(carrier02));

		registeredQuantity = (int) carrierRepository.count();
	}
}
