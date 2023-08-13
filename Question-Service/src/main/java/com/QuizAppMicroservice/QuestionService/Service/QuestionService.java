package com.QuizAppMicroservice.QuestionService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.QuizAppMicroservice.QuestionService.DAO.QuestionDAO;
import com.QuizAppMicroservice.QuestionService.Model.Question;
import com.QuizAppMicroservice.QuestionService.Model.QuestionWrapper;
import com.QuizAppMicroservice.QuestionService.Model.Response;



@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDao;
	

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.save(question);
		return new ResponseEntity<>("Question Added",HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQuestionById(Integer id) {
		// TODO Auto-generated method stub
		questionDao.deleteById(id);
		return new ResponseEntity<>("Question Deleted with ID: " + id,HttpStatus.OK);
		
	}

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Question>>(questionDao.findAll(),HttpStatus.FOUND);
	}

	public Optional<Question> getQuestionById(Integer qid) {
		// TODO Auto-generated method stub
		return questionDao.findById(qid);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			
			return new ResponseEntity<List<Question>>(questionDao.findByCategory(category),HttpStatus.FOUND);
		}catch(Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByDifficulty(String difficulty) {
		// TODO Auto-generated method stub
		try {
			
			return new ResponseEntity<List<Question>>(questionDao.findByDifficulty(difficulty),HttpStatus.FOUND);
		}catch(Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public void updateQuestionById(Integer id, Question question) {
		Optional<Question>qu = questionDao.findById(id);
		if(qu.isPresent()) {
			Question existingQue = qu.get();
			existingQue.setQuestionTitle(question.getQuestionTitle());
			existingQue.setCategory(question.getCategory());
			existingQue.setDifficulty(question.getDifficulty());
			existingQue.setOption1(question.getOption1());
			existingQue.setOption2(question.getOption2());
			existingQue.setOption3(question.getOption3());
			existingQue.setOption4(question.getOption4());
			existingQue.setDifficulty(question.getCorrectAnswer());
			
			questionDao.save(existingQue);
			
		}
		
	}

	//generating quiz here
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(numQuestions,category);
		
		System.out.println("List of questions with ids : " + questions);
		return  new ResponseEntity<List<Integer>> (questions,HttpStatus.CREATED);
	}

	//getting quiestions based on IDs
	public ResponseEntity<List<QuestionWrapper>> getQuestionsBasedOnId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		//getting all questions based on Ids
		for(Integer id : questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		//converting questions in to questionwrapper to proceed them towards quiz servcie
		for(Question quest : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setQid(quest.getQid());
			wrapper.setQuestionTitle(quest.getQuestionTitle());
			wrapper.setOption1(quest.getOption1());
			wrapper.setOption2(quest.getOption2());
			wrapper.setOption3(quest.getOption3());
			wrapper.setOption4(quest.getOption4());
			
			wrappers.add(wrapper);
		}
			
		System.out.println("QustionWrappers : " + wrappers);
		return new ResponseEntity<List<QuestionWrapper>>(wrappers, HttpStatus.OK);
	}

	//calculting score
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int score = 0;
		for(Response res : responses) {
			Question question = questionDao.findById(res.getId()).get();
			if(res.getResponse().equals(question.getCorrectAnswer()));
				score++;
		}
		System.out.println("Score : " + score);
		return new ResponseEntity<Integer>(score, HttpStatus.OK);
	}

	

}
