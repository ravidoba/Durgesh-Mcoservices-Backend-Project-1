package com.quiz.QuizService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@EnableFeignClients
@SpringBootApplication
public class QuizServiceApplication {

	// Fault Tolerance : @CircuitBreaker, @Retry :: Rate Limiter : ApacheJMeter
	public static void main(String[] args) {

		SpringApplication.run(QuizServiceApplication.class, args);
		System.out.println(  );
	}

}
