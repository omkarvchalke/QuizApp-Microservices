package com.QuizAppMicroservice.QuizService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.QuizAppMicroservice.QuizService.Model.Quiz;



@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer>  {

	

}
