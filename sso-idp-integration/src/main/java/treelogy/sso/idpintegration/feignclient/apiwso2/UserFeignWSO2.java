package treelogy.sso.idpintegration.feignclient.apiwso2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "sso-api-wso2", path = "integration")
public interface UserFeignWSO2 {
	
	@GetMapping(value = "/user/")
	public ResponseEntity<String> GetUserBySituation(@RequestParam(required = true, name = "situation") String situation);

}
