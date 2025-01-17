package com.quiz.QuizService.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.QuizService.client.QuestionClient;
import com.quiz.QuizService.entities.Quiz;
import com.quiz.QuizService.repository.QuizRepository;
import com.quiz.QuizService.service.QuizService;

//Service Implementation
@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionClient questionClient;

	@Override
	public List<Quiz> getAllQuizzes() {
		List<Quiz> quizees = quizRepository.findAll();
		quizees.forEach(quiz -> quiz.setQuestions(questionClient.getQuestionsByQuizId(quiz.getId())));
		return quizees;
	}

	@Override
	public Quiz getQuizById(Long id) {
		 Quiz quiz = quizRepository.findById(id).get();
		 quiz.setQuestions(questionClient.getQuestionsByQuizId(quiz.getId()));
		 return quiz;
	}

	@Override
	public Quiz createQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuizById(Long id, Quiz quiz) {
		Quiz existingQuiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
		existingQuiz.setTitle(quiz.getTitle());
		return quizRepository.save(existingQuiz);
	}

	@Override
	public void deleteQuizById(Long id) {
		if (!quizRepository.existsById(id)) {
			throw new RuntimeException("Quiz not found");
		}
		quizRepository.deleteById(id);
	}
}
