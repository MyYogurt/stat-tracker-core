package org.moisiadis.stattrackercore.model;


import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"driver", "averagePoints", "averagePosition", "totalPoints", "numSweeps", "last10AveragePoints", "last10AveragePosition", "last10Points", "last10NumSweeps"})
public class DriverStats {
	private Driver driver;
	private double averagePoints;
	private double averagePosition;
	private int totalPoints;
	private int numSweeps;
	private double last10AveragePoints;
	private double last10AveragePosition;
	private int last10Points;
	private int last10NumSweeps;

	public DriverStats() {
	}

	public DriverStats(Driver driver, double averagePoints, double averagePosition, int totalPoints, int numSweeps, double last10AveragePoints, double last10AveragePosition, int last10Points, int last10NumSweeps) {
		this.driver = driver;
		this.averagePoints = averagePoints;
		this.averagePosition = averagePosition;
		this.totalPoints = totalPoints;
		this.numSweeps = numSweeps;
		this.last10AveragePoints = last10AveragePoints;
		this.last10AveragePosition = last10AveragePosition;
		this.last10Points = last10Points;
		this.last10NumSweeps = last10NumSweeps;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public double getAveragePoints() {
		return averagePoints;
	}

	public void setAveragePoints(double averagePoints) {
		this.averagePoints = averagePoints;
	}

	public double getAveragePosition() {
		return averagePosition;
	}

	public void setAveragePosition(double averagePosition) {
		this.averagePosition = averagePosition;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getNumSweeps() {
		return numSweeps;
	}

	public void setNumSweeps(int numSweeps) {
		this.numSweeps = numSweeps;
	}

	public double getLast10AveragePoints() {
		return last10AveragePoints;
	}

	public void setLast10AveragePoints(double last10AveragePoints) {
		this.last10AveragePoints = last10AveragePoints;
	}

	public double getLast10AveragePosition() {
		return last10AveragePosition;
	}

	public void setLast10AveragePosition(double last10AveragePosition) {
		this.last10AveragePosition = last10AveragePosition;
	}

	public int getLast10Points() {
		return last10Points;
	}

	public void setLast10Points(int last10Points) {
		this.last10Points = last10Points;
	}

	public int getLast10NumSweeps() {
		return last10NumSweeps;
	}

	public void setLast10NumSweeps(int last10NumSweeps) {
		this.last10NumSweeps = last10NumSweeps;
	}
}
