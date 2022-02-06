package treelogy.sso.apiwso2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SsoApiWso2Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoApiWso2Application.class, args);
	}

}
