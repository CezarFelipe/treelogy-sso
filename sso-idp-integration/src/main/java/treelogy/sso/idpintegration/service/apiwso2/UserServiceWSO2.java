package treelogy.sso.idpintegration.service.apiwso2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import treelogy.sso.idpintegration.feignclient.apiwso2.UserFeignWSO2;

@Service
public class UserServiceWSO2 {

	private static Logger logger = LoggerFactory.getLogger(UserServiceWSO2.class);

	@Autowired
	private UserFeignWSO2 userFeignWSO2;

	public String findBySituation(String situation) {

		if ((situation == null) || situation.isEmpty()) {
			logger.error("situation empty");
			throw new IllegalArgumentException("situation empty");
		}

		String userJSON = userFeignWSO2.GetUserBySituation(situation).getBody();
		if (userJSON == null) {
			logger.error("situation not found: " + situation);
			throw new IllegalArgumentException("situation not found");
		}
		
		logger.info("situation found: " + situation);
		return userJSON;
	}

}
