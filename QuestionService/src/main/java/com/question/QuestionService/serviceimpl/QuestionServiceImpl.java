package com.question.QuestionService.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.QuestionService.entities.Question;
import com.question.QuestionService.repository.QuestionRepository;
import com.question.QuestionService.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestionById(Long id) {
		return questionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
	}

	@Override
	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question updateQuestionById(Long id, Question question) {
		Question existingQuestion = questionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
		existingQuestion.setQuestion(question.getQuestion());
		return questionRepository.save(existingQuestion);
	}

	@Override
	public void deleteQuestionById(Long id) {
		if (!questionRepository.existsById(id)) {
			throw new RuntimeException("Question not found with ID: " + id);
		}
		questionRepository.deleteById(id);
	}

	@Override
	public List<Question> getQuestionsByQuizId(Long quizId) {
		return questionRepository.findByQuizId(quizId);
	}
}