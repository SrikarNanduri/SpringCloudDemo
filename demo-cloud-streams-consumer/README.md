# Spring Cloud Streams Consumer    

This is a demo application which consumes messages from a queue. It has multiple scenario's where we were testing different cases and looking if it will work for our case.
The major reason to create this project it to test for Content-based routing that is supported by spring stream cloud.

## Content-based routing    

Spring Cloud Stream supports dispatching messages to multiple handler methods annotated with @StreamListener based on conditions.

In order to be eligible to support conditional dispatching, a method must satisfy the follow conditions:

- It must not return a value.
- It must be an individual message handling method (reactive API methods are not supported).

For example: 

    `@EnableBinding(Sink.class)
     @EnableAutoConfiguration
        public static class TestPojoWithAnnotatedArguments {
    
        @StreamListener(target = Sink.INPUT, condition = "headers['type']=='bogey'")
        public void receiveBogey(@Payload BogeyPojo bogeyPojo) {
           // handle the message
        }

        @StreamListener(target = Sink.INPUT, condition = "headers['type']=='bacall'")
     public void receiveBacall(@Payload BacallPojo bacallPojo) {
           // handle the message
        }
    }`

