package com.framework.loippi.controller.app.common;


public class AppGameWarehouse {
	
	private Long id;
	/**
	 * 游戏植物id
	 */
	private Long gamePlantId;

	/**
	 * 游戏植物名称
	 */
	private String gamePlantName;
	/**
	 * 游戏植物成熟图片
	 */
	private String gamePlantImage;
	
	private long level;//等级
	private long leveling;//等级显示已成长值
	private long leveled;//等级最长值

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGamePlantId() {
		return gamePlantId;
	}
	public void setGamePlantId(Long gamePlantId) {
		this.gamePlantId = gamePlantId;
	}
	public String getGamePlantName() {
		return gamePlantName;
	}
	public void setGamePlantName(String gamePlantName) {
		this.gamePlantName = gamePlantName;
	}
	public String getGamePlantImage() {
		return gamePlantImage;
	}
	public void setGamePlantImage(String gamePlantImage) {
		this.gamePlantImage = gamePlantImage;
	}
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	public long getLeveling() {
		return leveling;
	}
	public void setLeveling(long leveling) {
		this.leveling = leveling;
	}
	public long getLeveled() {
		return leveled;
	}
	public void setLeveled(long leveled) {
		this.leveled = leveled;
	}

}
