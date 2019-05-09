package com.framework.loippi.controller.app.model;

public class MesList {
	
	private Long id;
    private String prompt;
 
	private String content;
    private Integer type;
    private Integer isRead;
    
    public String getContent() {
 		return content;
 	}
 	public void setContent(String content) {
 		this.content = content;
 	}
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}
