package treelogy.sso.idpintegration.producer.apiwso2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProducerWSO2 {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	/*** Producer rabbitMQ ****/
	
	public void send(String json) {
		rabbitTemplate.convertAndSend(this.queue.getName(), json);
		System.out.println("========= send message rabbit ");
	}
		
}