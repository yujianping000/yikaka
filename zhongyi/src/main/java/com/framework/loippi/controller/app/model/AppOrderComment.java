package com.framework.loippi.controller.app.model;



public class AppOrderComment {
	
	
	/** 星星 */
	private Integer favStar;
	
	/** 评论时间 */
	private String createDateString;
	

	/**  */
	private String content;
	
	/**  */
	private String mark;

	
	/** 订单id */
	private Long orderId;
	
	public Integer getFavStar() {
		return favStar;
	}


	public void setFavStar(Integer favStar) {
		this.favStar = favStar;
	}


	public String getCreateDateString() {
		return createDateString;
	}


	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getMark() {
		return mark;
	}


	public void setMark(String mark) {
		this.mark = mark;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


}
