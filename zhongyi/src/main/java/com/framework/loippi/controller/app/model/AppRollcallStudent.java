package com.framework.loippi.controller.app.model;

import java.util.List;

public class AppRollcallStudent {
           public List<AppStudent5> getAll() {
		return all;
	}
	public void setAll(List<AppStudent5> all) {
		this.all = all;
	}
	public List<AppStudent5> getArrived() {
		return arrived;
	}
	public void setArrived(List<AppStudent5> arrived) {
		this.arrived = arrived;
	}
	public List<AppStudent5> getPendingConfirm() {
		return pendingConfirm;
	}
	public void setPendingConfirm(List<AppStudent5> pendingConfirm) {
		this.pendingConfirm = pendingConfirm;
	}
	public List<AppStudent5> getNoarrived() {
		return noarrived;
	}
	public void setNoarrived(List<AppStudent5> noarrived) {
		this.noarrived = noarrived;
	}
	public List<AppStudent5> getLeave() {
		return leave;
	}
	public void setLeave(List<AppStudent5> leave) {
		this.leave = leave;
	}
		List<AppStudent5>  all;//全部学生
           List<AppStudent5>  arrived;//到达学生
           List<AppStudent5>  pendingConfirm;//待确定
           List<AppStudent5>  noarrived;//未到
           List<AppStudent5>  leave;//请假的

}
