package treelogy.sso.oauth.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "sso-user", path = "/users")
public interface UserFeignClient {

	@GetMapping(value = "/search")
	ResponseEntity<treelogy.sso.oaut.model.User> findByEmail(@RequestParam String email);
}
