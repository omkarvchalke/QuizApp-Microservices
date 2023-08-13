package com.QuizAppMicroservice.QuizService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QuizAppMicroservice.QuizService.Model.QuestionWrapper;
import com.QuizAppMicroservice.QuizService.Model.QuizDTO;
import com.QuizAppMicroservice.QuizService.Model.Response;
import com.QuizAppMicroservice.QuizService.Service.QuizService;



@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	//createQuiz
	//getQuestions
	//getScore
	
	
//	@PostMapping("/createQuiz")
//	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String quizTitle){
//		//return new ResponseEntity<>("In Quiz Controller", HttpStatus.OK);
//		return quizService.createQuiz(category, numQ, quizTitle);
//	}
	
	@PostMapping("/createQuiz") //sending single object with combination of 3 fields : category name, numQ, quizTitle
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
		return quizService.createQuiz(quizDTO.getCategoryName(), quizDTO.getNumQ(),quizDTO.getQuizTitle());
	}
	
//	@GetMapping("/hello")
//	public void test() {
//		System.out.println("Hello from QuizCOntroller");
//	}
	
	@GetMapping("/getQuizById/{qzid}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer qzid){
		System.out.println("in Controller " + quizService.getQuizQuestions(qzid));
		return quizService.getQuizQuestions(qzid);
		
	}
	
	@PostMapping("/submit/{qzid}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer qzid, @RequestBody List<Response> responseList){
		System.out.println("Score : " + quizService.calculateResult(qzid, responseList));
		return quizService.calculateResult(qzid, responseList);
	}

}
