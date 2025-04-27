package com.arquitectura.proyecto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// Verifica que el contexto de Spring se carga correctamente
	}

	@Test
	void mainMethodStartsApplication() {
		// Prueba el método main
		ProyectoApplication.main(new String[]{});
	}

	@Test
	void applicationHasRequiredBeans() {
		// Verifica que los beans esenciales están presentes
		assert applicationContext.containsBeanDefinition("customAuthenticationProvider");
		assert applicationContext.containsBeanDefinition("jwtTokenProvider");
		assert applicationContext.containsBeanDefinition("usuarioService");
	}

}
