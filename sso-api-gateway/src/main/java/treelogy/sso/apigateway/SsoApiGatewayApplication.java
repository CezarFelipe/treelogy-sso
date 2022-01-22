package treelogy.sso.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SsoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoApiGatewayApplication.class, args);
	}

}
