package treelogy.sso.idpintegration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SsoIdpIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoIdpIntegrationApplication.class, args);
	}

}
