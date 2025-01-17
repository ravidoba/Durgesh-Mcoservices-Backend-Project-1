package com.question.QuestionService.service;

import java.util.List;

import com.question.QuestionService.entities.Question;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestionById(Long id);

    Question createQuestion(Question question);
    
    Question updateQuestionById(Long id, Question question);

    void deleteQuestionById(Long id);
    
    List<Question> getQuestionsByQuizId(Long quizId);
}