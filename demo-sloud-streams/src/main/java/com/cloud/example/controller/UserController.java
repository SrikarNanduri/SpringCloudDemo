package com.cloud.example.controller;


import com.cloud.example.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class JointechController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JointechController.class);


    @Autowired
    private Source source;
/**
 * Here we are publishing message using a post method to the queue
 * */
    @PostMapping("/user/publish")
    public String publishMessage(@RequestBody User user, @RequestParam("user") String userRole) {
        source.output().send(MessageBuilder.withPayload(user).setHeader("user", userRole).build());
        LOGGER.info(user.getName());
        return "message_published";
    }

}
