package com.poetryline.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "title")
@Component
public class Title {
	@Id
	@GeneratedValue(generator="increment_generator")
	@GenericGenerator(name="increment_generator",strategy="increment")
	private Integer id;
	private String topic;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Title(String topic, String optionA, String optionB, String optionC, String optionD,
			String answer) {
		super();
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
	}
	
	public Title(String topic, String optionA, String optionB,String answer) {
		super();
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.answer = answer;
	}
	
	public Title(String topic, String optionA, String optionB, String optionC,String answer) {
		super();
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.answer = answer;
	}
	
	public Title() {
		
	}
	@Override
	public String toString() {
		return "Title [id=" + id + ", topic=" + topic + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC="
				+ optionC + ", optionD=" + optionD + ", answer=" + answer + "]";
	}
	
	
	
}
