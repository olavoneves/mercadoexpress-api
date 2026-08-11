package br.com.fiap.mercadoexpress;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Sobe o contexto usando o perfil dev (H2 em memoria), para que o build
 * nao dependa da VPN/credenciais do Oracle da FIAP.
 */
@SpringBootTest
@ActiveProfiles("dev")
class MercadoExpressApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
