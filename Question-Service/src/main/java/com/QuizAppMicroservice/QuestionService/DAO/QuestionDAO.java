package com.QuizAppMicroservice.QuestionService.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.QuizAppMicroservice.QuestionService.Model.Question;



@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

	
	List<Question> findByCategory(String category);
	List<Question> findByDifficulty(String difficulty);
	
	//used to fetch the number of questions from questions DB to create a quiz
	@Query(value="SELECT q.qid FROM questions_details q WHERE q.category= :category ORDER BY RANDOM() LIMIT :numQ",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(Integer numQ,String category);

	

}
