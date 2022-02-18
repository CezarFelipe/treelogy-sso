package treelogy.sso.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SsoApiGatewayApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SsoApiGatewayApplication.class, args);
		
	}
	
	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
	  return registry -> registry.config().commonTags("application", "MYAPPNAME");
	}
	
}
