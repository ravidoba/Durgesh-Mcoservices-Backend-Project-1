package com.quiz.QuizService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
	
	private Long id;

	private String question;

	private Long quizId;
}
