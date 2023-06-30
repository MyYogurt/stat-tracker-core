package org.moisiadis.stattrackercore.model;

public class ResultCreateRequest {
	private String driver;
	private int position;
	private int points;

	public ResultCreateRequest() {
	}

	public ResultCreateRequest(String driver, int position, int points) {
		this.driver = driver;
		this.position = position;
		this.points = points;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
