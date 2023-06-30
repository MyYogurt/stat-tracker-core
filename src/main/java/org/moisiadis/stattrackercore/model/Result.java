package org.moisiadis.stattrackercore.model;

import java.util.Objects;

public final class Result {
	private int ID;
	private String driver;
	private int position;
	private int points;

	public Result(int ID, String driver, int position, int points) {
		this.ID = ID;
		this.driver = driver;
		this.position = position;
		this.points = points;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Result) obj;
		return this.ID == that.ID &&
				Objects.equals(this.driver, that.driver) &&
				this.position == that.position &&
				this.points == that.points;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, driver, position, points);
	}

	@Override
	public String toString() {
		return "Result[" +
				"ID=" + ID + ", " +
				"driver=" + driver + ", " +
				"position=" + position + ", " +
				"points=" + points + ']';
	}

}
