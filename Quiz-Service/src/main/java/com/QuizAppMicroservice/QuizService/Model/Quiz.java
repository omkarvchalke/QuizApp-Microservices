package com.QuizAppMicroservice.QuizService.Model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="quiz_table")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer qzid;
	
	@Column(name="quiz_title")
	private String quizTitle;
	
	//@ManyToMany // 1 quiz will have multiple questions | ManyToMany works with Objects / Entity only
	@ElementCollection //used to have collection of elements like Integer String like specifically one type
	private List<Integer> questionIds;
	
	@Override
	public String toString() {
		return "Quiz [qzid=" + qzid + ", quizTitle=" + quizTitle + ", questions=" + questionIds + "]";
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getQzid() {
		return qzid;
	}

	public void setQzid(Integer qzid) {
		this.qzid = qzid;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public List<Integer> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}

	

	
	

}
