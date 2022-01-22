package treelogy.sso.serverlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class SsoServerLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoServerLogApplication.class, args);
	}

}
