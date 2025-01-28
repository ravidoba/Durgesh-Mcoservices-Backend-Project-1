package com.quiz.QuizService.kafka;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "fruits")//, groupId = "console-consumer-94543"
    public void consume(String message) {
        log.error("Consumer Received Message: " + message);
    }
}