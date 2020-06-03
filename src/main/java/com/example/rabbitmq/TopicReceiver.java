package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {
    @RabbitListener(queues = "langCourse")
    public void handler1(String message) {
        System.out.println("langCourseReceiver:" + message);
    }
    @RabbitListener(queues = "javaCourse")
    public void handler2(String message) {
        System.out.println("javaCourseReceiver:" + message);
    }
    @RabbitListener(queues = "pythonCourse")
    public void handler3(String message) {
        System.out.println("pythonCourseReceiver:" + message);
    }
}
