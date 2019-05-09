package com.framework.loippi.entity;

public class Week {
	
	/**1—上午  2—下午  2—晚上*/
	private Integer type;
	
	/**星期一   1—没空 */
	private Integer Monday;
	
	/**星期二   1—没空*/
	private Integer Tuesday;
	
	/**星期三    1—没空*/
	private Integer Wednesday;
	
	/**星期四    1—没空*/
	private Integer Thursday;
	
	/**星期五    1—没空*/
	private Integer Friday;
	
	/**星期六    1—没空*/
	private Integer Saturday;
	
	/**星期日    1—没空*/
	private Integer Sunday;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMonday() {
		return Monday;
	}

	public void setMonday(Integer monday) {
		Monday = monday;
	}

	public Integer getTuesday() {
		return Tuesday;
	}

	public void setTuesday(Integer tuesday) {
		Tuesday = tuesday;
	}

	public Integer getWednesday() {
		return Wednesday;
	}

	public void setWednesday(Integer wednesday) {
		Wednesday = wednesday;
	}

	public Integer getThursday() {
		return Thursday;
	}

	public void setThursday(Integer thursday) {
		Thursday = thursday;
	}

	public Integer getFriday() {
		return Friday;
	}

	public void setFriday(Integer friday) {
		Friday = friday;
	}

	public Integer getSaturday() {
		return Saturday;
	}

	public void setSaturday(Integer saturday) {
		Saturday = saturday;
	}

	public Integer getSunday() {
		return Sunday;
	}

	public void setSunday(Integer sunday) {
		Sunday = sunday;
	}
}
