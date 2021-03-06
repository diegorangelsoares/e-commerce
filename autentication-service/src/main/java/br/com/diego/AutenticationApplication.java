package br.com.diego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class AutenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticationApplication.class, args);
		System.out.println("Link do Swagger: http://localhost:8765/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
		System.out.println("Link do ZipKin para monitorar requests: http://localhost:9411/zipkin/?lookback=15m&endTs=1633460640845&limit=10");
		System.out.println("Url para autenticar via Postman: http://localhost:8200/login");
		System.out.println("Json:\n" +
				"{\n" +
				"    \"login\":\"user\",\n" +
				"    \"password\": \"password\"\n" +
				"}");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}


}
