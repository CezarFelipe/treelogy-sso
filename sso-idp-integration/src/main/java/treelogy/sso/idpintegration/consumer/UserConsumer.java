package treelogy.sso.idpintegration.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

	@RabbitListener(queues = { "${queue.user.name}" })
	public void receive(@Payload String fileBody) {
		System.out.println("Message " + fileBody);
	}

}
