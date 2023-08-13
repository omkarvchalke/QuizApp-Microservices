package com.QuizAppMicroservice.QuizService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.QuizAppMicroservice.QuizService.DAO.QuizDAO;
import com.QuizAppMicroservice.QuizService.Feing.QuizInterfaceFeing;
import com.QuizAppMicroservice.QuizService.Model.QuestionWrapper;
import com.QuizAppMicroservice.QuizService.Model.Quiz;
import com.QuizAppMicroservice.QuizService.Model.Response;


//mvn spring-boot:run -Dspring-boot.run,arguments=--server.port=8090
@Service
public class QuizService {

	@Autowired
	QuizDAO quizDao;
	
	@Autowired
	QuizInterfaceFeing quizInterfaceFeign;

	
	//createQuiz
	//getQuestions
	//getScore
	
	
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String quizTitle) {		
		
		//interaction with Question Service to get the questions, getting quesstion IDs list to create quiz
		//sending request using url to question service to get questionsIds for quiz
		//RestTemplate : calling generated url of question service to get response | https://localhost:8080/question/createQuiz -> this iwll generate quiz for us using Question-service
		// we can use Resttempalte to send reuqests with some inbuild methods
		
		//getQuestionsForQuiz is in Question-Service so we need soemthing like Feign to interat with it
		
		List<Integer> questions = quizInterfaceFeign.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setQuizTitle(quizTitle);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<String>("Created Quiz using Feign",HttpStatus.CREATED);
	}

	//here we are using QuestionWrapper because we don't want to show entire Question object to user
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer qzid) {
		
		Quiz quiz = quizDao.findById(qzid).get();
		System.out.println(quiz.toString());
		
		List<Integer> questionIds = quiz.getQuestionIds();
		System.out.println(questionIds);
		ResponseEntity<List<QuestionWrapper>> questions= quizInterfaceFeign.getQuestionsBasedOnId(questionIds);
		
		return questions;
//		System.out.println(quiz.toString());
//		//getting all questions from DB matching with qzid
//		List<Question> questionsFromDB = quiz.get().getQuestions();
//		//creating an arrayList to store the modified form of questions
	//	List<QuestionWrapper> questionForUser = new ArrayList<>();
//		//looping through all questions and converting questions into question wrapper
//		System.out.println(questionsFromDB);
//		for(Question q : questionsFromDB) {
//			// now here we are creating question wrapper using Question and QuestionWrapper's Para Constructor
//			QuestionWrapper qw = new QuestionWrapper(q.getQid(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//			System.out.println("for lopp:" + qw.toString());
//			//adding newly form QuestionWrapper to the List
//			questionForUser.add(qw);
//		}
//		System.out.println("\n"+questionForUser);
//		for(QuestionWrapper qw : questionForUser)
//			System.out.println("qw : " + qw);
//		
		//return new ResponseEntity<List<QuestionWrapper>>(questionForUser,HttpStatus.FOUND);
		
		
	}

	//calculating result on the basis of response and correct ans from the table
	public ResponseEntity<Integer> calculateResult(Integer qzid, List<Response> responseList) {

		ResponseEntity<Integer> score = quizInterfaceFeign.getScore(responseList);
		
		return score;
	}
	
	
	
	
}
