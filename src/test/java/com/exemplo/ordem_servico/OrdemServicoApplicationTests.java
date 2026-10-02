package com.exemplo.ordem_servico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class OrdemServicoApplicationTests {

	@Autowired
	private Environment environment;

	@Test
	void contextLoads() {
	}

	@Test
	void deveAplicarLimitesSegurosDeConsulta() {
		assertEquals(
				"false",
				environment.getProperty("spring.jpa.open-in-view"));
		assertEquals(
				"100",
				environment.getProperty(
						"spring.data.web.pageable.max-page-size"));
	}

}
