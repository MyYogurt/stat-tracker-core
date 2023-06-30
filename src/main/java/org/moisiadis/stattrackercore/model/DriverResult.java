package org.moisiadis.stattrackercore.model;

import org.moisiadis.stattrackercore.CC;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public final class DriverResult {
	private Instant date;
	private String grandPrixName;
	private String cc;
	private int position;
	private int points;

	public DriverResult(Instant date, String grandPrixName, String cc, int position, int points) {
		this.date = date;
		this.grandPrixName = grandPrixName;
		this.cc = cc;
		this.position = position;
		this.points = points;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getGrandPrixName() {
		return grandPrixName;
	}

	public void setGrandPrixName(String grandPrixName) {
		this.grandPrixName = grandPrixName;
	}

	public String getCC() {
		return cc;
	}

	public void setCC(String cc) {
		this.cc = cc;
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
		var that = (DriverResult) obj;
		return Objects.equals(this.date, that.date) &&
				Objects.equals(this.grandPrixName, that.grandPrixName) &&
				Objects.equals(this.cc, that.cc) &&
				this.position == that.position &&
				this.points == that.points;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, grandPrixName, cc, position, points);
	}

	@Override
	public String toString() {
		return "DriverResult[" +
				"date=" + date + ", " +
				"grandPrixName=" + grandPrixName + ", " +
				"cc=" + cc + ", " +
				"position=" + position + ", " +
				"points=" + points + ']';
	}

}
