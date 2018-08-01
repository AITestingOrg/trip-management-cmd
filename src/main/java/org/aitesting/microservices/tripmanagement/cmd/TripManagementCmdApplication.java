package org.aitesting.microservices.tripmanagement.cmd;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class TripManagementCmdApplication {

//    static final String topicExchangeName = "trip-calculation-exchange";
//
//    static final String queueName = "trip-calculation-queue";
//
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }

    public static void main(String[] args) {
        SpringApplication.run(TripManagementCmdApplication.class, args);
    }
}
