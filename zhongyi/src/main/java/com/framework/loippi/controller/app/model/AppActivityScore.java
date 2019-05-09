package com.framework.loippi.controller.app.model;

public class AppActivityScore {
       public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	private  String number;//参数号
       private  String name;//获奖名
       private  String score;//奖品
}
