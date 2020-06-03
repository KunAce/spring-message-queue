package com.example.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queque","hello direct!");
    }
    @Test
    public void fanoutTest() {
        rabbitTemplate
                .convertAndSend(RabbitFanoutConfig.FANOUTNAME, null, "hello fanout!");
    }
    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "javaCourse.news","javaCourse's news.....");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "pythonCourse.news","pythonCourse's news......");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "javaCourse.langCourse","javaCourse in langCourse......");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "pythonCourse.langCourse","pythonCourse in langCourse......");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "langCourse.news","langCourse's news......");
    }
    @Test
    public void headerTest() {
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue".getBytes())
                .setHeader("name","user").build();
        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue".getBytes())
                .setHeader("age","99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, nameMsg);
    }

}
