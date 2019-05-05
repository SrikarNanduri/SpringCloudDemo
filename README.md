# Spring Cloud Streams    

This is a demo Spring Cloud Streams application which deals with publishing and consuming messages from a queue. It has multiple scenario's where we were testing different cases and looking if it will work for our case.
The major reason to create this project it to test for Content-based routing that is supported by spring stream cloud. I've used RabbitMQ docker image as message broker for this project and if at all anyone wants to try it out please use docker image.
        
        docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
        
This is create a rabbitmq docker container and open [http://localhost:15672](http://localhost:15672), which will show the management console login form. The default username is: ‘guest’ and password: ‘guest’   

RabbitMQ will also listen on port 5672. (but the web port is 15672)

## Important Concepts!
before moving forward, we will look at some important concepts here. Those concepts will help you in understanding the contents clearly.

Binder :- This represents the underlying message broker middleware such as RabbitMQ or Kafka.

Channel :- represents a logical connection between the external application and the message broker middleware. it can be either input channel or output channel based on the participating application. e.g:-

- Publisher application publishes message to the message broker through Output channel
- Consumer application receives messages from message broker through Input channel.


## Application Scenario's
The Scenario's we are trying in this application are
- Content-based routing.
- Trying to use content-based routing along with methods where we return something.
- using same queue for both content-based routhing methods and methods that are returning a value.
- Trying to create custom binding interfaces using the same exchange and queue and trying to make content-based routing and method returning values work together.
- Using same queue and exchange for publishing a message and consuming the message(the flow is as follows, the publisher will publish a message and the consumer will recieve it then send it to the same queue for another method inside publisher to consue it)
- Using Different queues and exchange for publishing a message and consuming the message(the flow is as follows, the publisher will publish a message and the consumer will recieve it then send it to the different queue for another method inside publisher to consue it)