package com.question.QuestionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.QuestionService.entities.Question;
import com.question.QuestionService.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Get all questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    // Get question by ID
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

 // Create a new question
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }
    
    // Update question by ID
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestionById(id, question));
    }

    // Delete question by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok("Question deleted successfully");
    }
    
    // Get all questions by quizId
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long quizId) {
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }
}