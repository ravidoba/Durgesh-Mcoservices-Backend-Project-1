package com.quiz.QuizService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.QuizService.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
} 
