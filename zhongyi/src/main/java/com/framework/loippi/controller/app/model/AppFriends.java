package com.framework.loippi.controller.app.model;

import java.util.List;

public class AppFriends {
	private String indexWord;
    private List<AppUsrRelation> appUsrRelation;
    public String getIndexWord() {
		return indexWord;
	}
	public void setIndexWord(String indexWord) {
		this.indexWord = indexWord;
	}
	public List<AppUsrRelation> getAppUsrRelation() {
		return appUsrRelation;
	}
	public void setAppUsrRelation(List<AppUsrRelation> appUsrRelation) {
		this.appUsrRelation = appUsrRelation;
	}
}
