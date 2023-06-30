package org.moisiadis.stattrackercore.model;

public class CreateResultRequest {
	private int raceId;
	private String driver;
	private int position;
	private int points;

	public CreateResultRequest(int raceId, String driver, int position, int points) {
		this.raceId = raceId;
		this.driver = driver;
		this.position = position;
		this.points = points;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
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
