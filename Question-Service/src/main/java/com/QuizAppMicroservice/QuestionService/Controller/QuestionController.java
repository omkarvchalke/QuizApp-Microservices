package com.QuizAppMicroservice.QuestionService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QuizAppMicroservice.QuestionService.Model.Question;
import com.QuizAppMicroservice.QuestionService.Model.QuestionWrapper;
import com.QuizAppMicroservice.QuestionService.Model.Response;
import com.QuizAppMicroservice.QuestionService.Service.QuestionService;




@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("/deleteQuestionById/{qid}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer qid) {
		return questionService.deleteQuestionById(qid);
	}
	
	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/getQuestionById/{qid}")
	public Optional<Question> getAllQuestionById(@PathVariable Integer qid){
		return questionService.getQuestionById(qid);
	}
	
	@GetMapping("/getQuestionByCategory/{category}")
	public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@GetMapping("/getQuestionByDifficulty/{difficulty}")
	public ResponseEntity<List<Question>> getAllQuestionByDifficulty(@PathVariable String difficulty){
		return questionService.getQuestionByDifficulty(difficulty);
	}
	
	@PutMapping("/updateQuestion/{id}")
	public void updateStudentById(@PathVariable Integer id, @RequestBody Question question) {
		questionService.updateQuestionById(id, question);
	}
	
	// way to generate Quiz
	
	@GetMapping("/createQuiz")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions) {
		return questionService.getQuestionsForQuiz(category, numQuestions);
		
	}
	// getQuestions based get/{questionId}
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsBasedOnId(@RequestBody List<Integer> questionIds){
		return questionService.getQuestionsBasedOnId(questionIds);
	}
	
	// calculate score
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
	
	
		
	
	
	
}
