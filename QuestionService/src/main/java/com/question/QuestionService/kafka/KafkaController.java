package com.question.QuestionService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "fruits";

	@GetMapping("/send/message/{message}")
	public String sendMessage(@PathVariable String message) {
		int n = 2;
		for (int i = 1; i <= n; i++) {
			String message1 = message + i;
			kafkaTemplate.send(TOPIC, message1);
//			log.error("Sent: {}", message1);
		}
		log.error(n + "Producer Messages sent to Kafka topic: {}", TOPIC);
		return "Messages sent to Kafka topic!";
	}

}