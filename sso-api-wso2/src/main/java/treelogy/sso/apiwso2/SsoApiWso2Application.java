package treelogy.sso.apiwso2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EntityScan(basePackages = { "treelogy.sso.apiwso2.model" }) // varre pacotes de modelo 
@ComponentScan(basePackages = { "treelogy.sso*" }) // varre todo o projeto - injeção de dependências 
@EnableJpaRepositories(basePackages = { "treelogy.sso.apiwso2.repository" }) // habilita persistência 
@EnableTransactionManagement // controle transacional (gerência de transações) 
@EnableWebMvc // habilita MVC 
@RestController // habilita REST (retorno de JSON) 
@EnableAutoConfiguration // configuração automática do projeto 
@EnableCaching
@EnableEurekaClient
@SpringBootApplication
public class SsoApiWso2Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoApiWso2Application.class, args);
	}

}
