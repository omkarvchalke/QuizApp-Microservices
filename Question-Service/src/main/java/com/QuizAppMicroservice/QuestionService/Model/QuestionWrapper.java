package com.QuizAppMicroservice.QuestionService.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.Data;

//this class is created because we don't want to show entire Question object to the user, we need to hide some values and show some attributes


@Data
@Component
public class QuestionWrapper {

	
	private Integer qid;
	private String questionTitle;
	
	@Override
	public String toString() {
		return "QuestionWrapper [qid=" + qid + ", questionTitle=" + questionTitle + ", option1=" + option1
				+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + "]";
	}
	
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	public QuestionWrapper(Integer qid, String questionTitle, String option1, String option2, String option3,
			String option4) {
		super();
		this.qid = qid;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer integer) {
		this.qid = integer;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public QuestionWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
