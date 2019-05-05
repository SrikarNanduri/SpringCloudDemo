package com.cloud.example.streamListerners;


import com.cloud.example.customBindings.DeviceProcess;
import com.cloud.example.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;


/**
 * If we try to use the same binding queue and exchange with different input and output bindings, the it will throw error like this:
 * Caused by: java.lang.IllegalStateException: Failed to register bean with name 'message-exchange.message-queue.errors.recoverer',
 * since bean with the same name already exists. Possible reason: You may have multiple bindings with the same 'destination' and 'group' name (consumer side)
 * and multiple bindings with the same 'destination' name (producer side). Solution: ensure each binding uses different group name (consumer side) or 'destination' name (producer side).
 *
 * Here I've added the following properties in application.properties
 *
 * #this is the name of the queue
 * spring.cloud.stream.bindings.input-device.group=message-queue
 *
 * spring.cloud.stream.bindings.input-device.destination=message-exchange
 *
 * This doesn't work because as we've seen before according to spring cloud streams we can't use the same queue and exchange to return a value and also to listen to multiple StreamListeners.
 *
 *
 * The above properties have the same queue and exchange as we have for MessageListerner class methods.
 *
 * The problem we are facing is that we want to listen to the same queue or pipeline for the messages from 2 different parsed messages, process them and then send them to another queue or pipeline for another application or consumer
 * to retrieve the data. But according to the spring cloud stream
 *   - we can either have multiple methods listening to queue and they don't return anything or
 *   - we can have a method that listens and sends the processed data to another queue
 * So I thought why not create custom binding interfaces with the same queue and exchange names and try using these custom bindings to listen to the same queue and achieve both the cases menctioned above,
 * But it didn't work. as I've mentioned above it is throwing error saying that it failed to create bean as it already exists.
 *
 * */

@EnableBinding(DeviceProcess.class)
public class NewMessageListener {

    @Autowired
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(NewMessageListener.class);

    @StreamListener(DeviceProcess.INPUT)
    @SendTo(DeviceProcess.OUTPUT)
    public User listenForUser(User user) {
        logger.info(" received new user ["+user.toString()+"] ");
        source.output().send(MessageBuilder.withPayload(user).setHeader("user", "admin").build());
        return user;
    }
}
