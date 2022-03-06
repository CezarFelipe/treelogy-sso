package treelogy.sso.idpintegration.scheduled.apiwso2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import brave.sampler.Sampler;
import treelogy.sso.idpintegration.producer.apiwso2.UserProducerWSO2;
import treelogy.sso.idpintegration.service.apiwso2.UserServiceWSO2;

@EnableAsync
@Component
public class UserScheduledWSO2 {
	
	@Autowired
	private UserServiceWSO2 service;

	@Autowired
	private UserProducerWSO2 produce;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	@Scheduled(fixedDelayString = "${spring.scheduling.wso2.user.fixed}", initialDelayString = "${spring.scheduling.wso2.user.initial}")
	public void scheduleFixedRateTaskAsync() throws InterruptedException {
		try {

			logger.info("Start some work from the scheduled task");

			Thread.sleep(2000);

			String json = service.findBySituation("pending");

			logger.info("Scheduled task successfully executed");

			if (!json.isEmpty()) {

				logger.info("Start shipping rabbitMQ payload");

				produce.send(json);
			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

			logger.error("Error scheduled task: " + e.getMessage());

		}

	}
}