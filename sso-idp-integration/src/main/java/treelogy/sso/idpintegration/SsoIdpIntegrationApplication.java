package treelogy.sso.idpintegration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class SsoIdpIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoIdpIntegrationApplication.class, args);
	}

}
