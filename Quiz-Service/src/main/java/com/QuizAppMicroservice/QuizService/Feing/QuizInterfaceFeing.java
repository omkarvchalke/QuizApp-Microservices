package com.QuizAppMicroservice.QuizService.Feing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuizAppMicroservice.QuizService.Model.QuestionWrapper;
import com.QuizAppMicroservice.QuizService.Model.Response;





@FeignClient("QUESTION-SERVICE")
public interface QuizInterfaceFeing {
	
	//createQuiz
	//getQuestions
	//getScore
	//these 3 methods from quetion-service we need here for Quiz-service
	
	//create Quiz
	@GetMapping("question/createQuiz") //using question/createQuiz because we are using the method from Question-service which has heirarchy as /question/createQuiz
	//returning IDs for questions based on category
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);
	
	// getQuestions based get/{questionId}
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsBasedOnId(@RequestBody List<Integer> questionIds);
	
	// calculate score
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
