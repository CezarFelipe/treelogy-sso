package treelogy.sso.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Employee API", version = "1.0", description = "Documentation Employee API v1.0")
)
public class SsoOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoOauthApplication.class, args);
	}

}
