package com.quiz.QuizService.service;

import java.util.List;
import java.util.Optional;

import com.quiz.QuizService.entities.Quiz;

//Service
public interface QuizService {
	List<Quiz> getAllQuizzes();

	Quiz getQuizById(Long id);

	Quiz createQuiz(Quiz quiz);

	Quiz updateQuizById(Long id, Quiz quiz);

	void deleteQuizById(Long id);
}
