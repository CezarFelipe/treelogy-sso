package treelogy.sso.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SsoConfigServerApplication implements CommandLineRunner{

	@Value("${spring.cloud.config.server.git.username}")
	private String username;
	
	@Value("${spring.cloud.config.server.git.password}")
	private String password;
	
	public static void main(String[] args) {
		SpringApplication.run(SsoConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
