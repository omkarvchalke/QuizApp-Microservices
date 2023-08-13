package com.QuizAppMicroservice.QuizService.Model;

import lombok.Data;

//this class will have category, number of questions and quiz title to create a quiz
@Data
public class QuizDTO {
	
	private String categoryName;
	private Integer numQ;
	private String quizTitle;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getNumQ() {
		return numQ;
	}
	public void setNumQ(Integer numQ) {
		this.numQ = numQ;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	

}
