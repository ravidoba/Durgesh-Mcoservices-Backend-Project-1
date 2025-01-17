package com.quiz.QuizService.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.quiz.QuizService.entities.Question;

//@FeignClient(url = "http://localhost:8092", value = "Question-Client")
@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionClient {

	@GetMapping("/questions/quiz/{quizId}")
	List<Question> getQuestionsByQuizId(@PathVariable Long quizId);
}
