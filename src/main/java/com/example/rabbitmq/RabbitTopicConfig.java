package com.example.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {
    public final static String TOPICNAME="user-topic";
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPICNAME, true, false);
    }
    @Bean
    Queue javaCourse() {
        return new Queue("javaCourse");
    }
    @Bean
    Queue pythonCourse() {
        return new Queue("pythonCourse");
    }
    @Bean
    Queue langCourse() {
        return new Queue("langCourse");
    }
    @Bean
    Binding javaBinding() {
        return BindingBuilder.bind(javaCourse()).to(topicExchange())
                .with("javaCourse.#");
    }
    @Bean
    Binding pythonBinding() {
        return BindingBuilder.bind(pythonCourse()).to(topicExchange())
                .with("pythonCourse.#");
    }
    @Bean
    Binding langBinding() {
        return BindingBuilder.bind(langCourse()).to(topicExchange())
                .with("#.langCourse.#");
    }
}
