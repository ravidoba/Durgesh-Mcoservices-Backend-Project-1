package com.quiz.QuizService.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.quiz.QuizService.entities.Quiz;
import com.quiz.QuizService.service.QuizService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

//Controller
@RestController
@RequestMapping("/quizzes")
@Slf4j
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    @CircuitBreaker(name = "getAllQuizzesCircuitBreaker", fallbackMethod = "getAllQuizzesFallbackMethod")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        log.info("getAllQuizzes() called");
        List<Quiz> quizzes = quizService.getAllQuizzes();
        log.info("Retrieved {} quizzes successfully", quizzes.size());
        return ResponseEntity.ok(quizzes);
    }

    public ResponseEntity<List<Quiz>> getAllQuizzesFallbackMethod(Exception ex) {
        log.warn("getAllQuizzesFallbackMethod executed because service is down: {}", ex.getMessage());
        ArrayList<Quiz> list = new ArrayList<>();
        Quiz quiz = Quiz.builder().title("Dummy Question").build();
        list.add(quiz);
        log.info("Returning fallback response with {} dummy quiz", list.size());
        return ResponseEntity.ok(list);
    }

    int retryCount = 1;

    @GetMapping("/{id}")
    @Retry(name = "getQuizByIdRetry", fallbackMethod = "handleGetQuizByIdRetryRetryFallback")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        log.info("getQuizById() called with id: {}", id);
        log.info("Retry count is : {}", retryCount);
        retryCount++;
        Quiz quiz = quizService.getQuizById(id);
        log.info("Successfully retrieved quiz with id: {}", id);
        return ResponseEntity.ok(quiz);
    }

    public ResponseEntity<Quiz> handleGetQuizByIdRetryRetryFallback(Long id, Throwable throwable) {
        log.error("Fallback triggered for getQuizById with id: {}. Reason: {}", id, throwable.getMessage());
        Quiz fallbackQuiz = new Quiz();
        fallbackQuiz.setId(id);
        fallbackQuiz.setTitle("Default Quiz Title");
        fallbackQuiz.setQuestions(Collections.emptyList());
        log.info("Returning fallback quiz for id: {}", id);
        return ResponseEntity.ok(fallbackQuiz);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        log.info("createQuiz() called with quiz: {}", quiz);
        Quiz createdQuiz = quizService.createQuiz(quiz);
        log.info("Successfully created quiz with id: {}", createdQuiz.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuizById(@PathVariable Long id, @RequestBody Quiz quiz) {
        log.info("updateQuizById() called with id: {} and quiz: {}", id, quiz);
        Quiz updatedQuiz = quizService.updateQuizById(id, quiz);
        log.info("Successfully updated quiz with id: {}", id);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Long id) {
        log.info("deleteQuizById() called with id: {}", id);
        quizService.deleteQuizById(id);
        log.info("Successfully deleted quiz with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
