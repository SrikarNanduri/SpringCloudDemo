package com.cloud.example;

import com.cloud.example.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CloudStreamApplication {

	private static final Logger logger = LoggerFactory.getLogger(CloudStreamApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamApplication.class, args);
	}

	/**
	 * This method is listening to the messages that are being sent from the consumer application
	 *
	 * So the application flow is as follows
	 *
	 * - This application is a publisher, we are sending messages that are parsed from this application to an exchange (message-exchange)
	 *   then on the consumer end the application is binded to a queue (message-queue), so all the messages we publish to the exchange are sent to queue
	 *   and the consumer application was retrieving the messages on the other end.
	 * - Now on the consumer application end we are trying different things and one such experiment is sending those messages back to another application to receive.
	 *   So here this method is listening to those messages that are being pushed to another queue.
	 *
	 * Another scenario that I've tried is using the same queue as input destinations for both consumer application and publisher application.
	 * Before we are sending a message from publisher to a queue and consumer is consuming it and sending it back to another queue and publisher is receiving it back
	 *
	 * if we are using the same queue then before even consumer application consume the message the publisher application is consuming it so the consumer is not receiving anything.
	 *
	 */

	@StreamListener(target = Processor.INPUT, condition = "headers['user']=='admin'" )
	public void envotechOrders(User user){
		logger.info(" USER object received ["+user.toString()+"] ");

	}

}
