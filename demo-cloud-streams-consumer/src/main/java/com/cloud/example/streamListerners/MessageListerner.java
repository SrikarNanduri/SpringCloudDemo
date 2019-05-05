package com.cloud.example.streamListerners;


import com.cloud.example.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;


@EnableBinding(Processor.class)
public class MessageListerner {

    @Autowired
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(MessageListerner.class);

    @StreamListener(target = Processor.INPUT, condition = "headers['user']=='user'")
    public void listenForUser( User user) {
        logger.info(" received new user ["+user.toString()+"] ");
    }


    @StreamListener(target = Processor.INPUT, condition = "headers['user']=='admin'")
    public void listenForUserAdmin( User user) {
        logger.info(" received new user :admin ["+user.toString()+"] ");
    }



    /**
     * Spring Cloud Stream supports dispatching messages to multiple handler methods annotated with @StreamListener based on conditions.
     *
     * In order to be eligible to support conditional dispatching, a method must satisfy the follow conditions:
     *
     * It must not return a value.
     *
     * It must be an individual message handling method (reactive API methods are not supported).
     *
     *
     * So we can either use a single method that will consume and then send it to another queue for other consumer to consume or we can use multiple @StreamListener to listen to the queue and
     * we can write conditions to filter like we did above.
     *
     * For testing this out try to un comment the below code and run both publisher and consumer applications and see what happens.
     * To test for multiple @StreamListener methods leave the below code commented and run both the applications.
     * To test the single methode to listen to and send to anothe queue comment the above code and uncomment the below code the run both the applications.
     *
     * To know more go to NewMessageListener class for different approach I've tried.
     * */

    //If we try to listen to the same binding and return a value then we get the following error because of the above mentioned reasons. So this method won't work if we are trying to listen to same binding with multiple methods
    // java.lang.IllegalArgumentException: If multiple @StreamListener methods are listening to the same binding target, none of them may return a value
 /*   @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public User listenForUser( User user) {
        logger.info(" received new user ["+user.toString()+"] ");
        source.output().send(MessageBuilder.withPayload(user).setHeader("user", "admin").build());
        logger.info(" received new message ["+user.getName()+"] ");
        return user;
    }*/

}
