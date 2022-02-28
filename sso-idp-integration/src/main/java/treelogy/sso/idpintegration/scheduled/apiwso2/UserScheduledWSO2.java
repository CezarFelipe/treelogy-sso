package treelogy.sso.idpintegration.scheduled.apiwso2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import treelogy.sso.idpintegration.producer.apiwso2.UserProducerWSO2;
import treelogy.sso.idpintegration.service.apiwso2.UserServiceWSO2;

@EnableAsync
@Component
public class UserScheduledWSO2 {
	
	@Autowired
	private UserServiceWSO2 service;
	
	@Autowired
	private UserProducerWSO2 produce;
		
	
    @Async
    @Scheduled(fixedRate = 60000)
	public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
          "Fixed rate task async - " + System.currentTimeMillis() / 60000);
        Thread.sleep(2000);
        
        String json = service.findBySituation("pending");
        System.out.println(json);
        
        if (!json.isEmpty()) {
		
        	produce.send(json);
		}
        
        
    }

}