package br.com.fiap.horadaprotecao;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;
import br.com.fiap.horadaprotecao.config.TestConfig;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class HoradaprotecaoApplicationTests {

	@Test
	void contextLoads() {
	}

}
