package com.futuremovement.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnReport() {
		Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/summaryReport",
				String.class)).contains("\"Client_Information\",\"Product_Information\",\"Total_Transaction_Amount\"\n" +
				"\"CL432100020001\",\"SGXFUNK20100910\",\"4\"\n");
	}
}
