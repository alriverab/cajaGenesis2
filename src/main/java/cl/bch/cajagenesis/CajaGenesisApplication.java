package cl.bch.cajagenesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan("cl.bch.cajagenesis.persistence.entties")
@EnableJpaRepositories("cl.bch.cajagenesis.persistence.repository")
public class CajaGenesisApplication {
	public static void main(String[] args) {
		SpringApplication.run(CajaGenesisApplication.class, args);
	}
}
