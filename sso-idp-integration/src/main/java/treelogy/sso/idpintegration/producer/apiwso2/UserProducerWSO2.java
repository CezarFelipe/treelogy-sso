package treelogy.sso.idpintegration.producer.apiwso2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import brave.sampler.Sampler;

@Component
public class UserProducerWSO2 {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Queue queue;

	/*** Producer rabbitMQ ****/

	public void send(String json) {
		rabbitTemplate.convertAndSend(this.queue.getName(), json);
		System.out.println("========= send message rabbit ");

		logger.info("Sent rabbitMQ");
	}

}