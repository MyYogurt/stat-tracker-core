package org.moisiadis.stattrackercore.model;

import java.util.List;
import java.util.Objects;

public final class Race {
	private int ID;
	private String grandPrix;
	private String date;
	private String cc;
	private List<Result> results;

	public Race(int ID, String grandPrix, String date, String cc, List<Result> results) {
		this.ID = ID;
		this.grandPrix = grandPrix;
		this.date = date;
		this.cc = cc;
		this.results = results;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getGrandPrix() {
		return grandPrix;
	}

	public void setGrandPrix(String grandPrix) {
		this.grandPrix = grandPrix;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Race) obj;
		return this.ID == that.ID &&
				Objects.equals(this.grandPrix, that.grandPrix) &&
				Objects.equals(this.date, that.date) &&
				Objects.equals(this.cc, that.cc) &&
				Objects.equals(this.results, that.results);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, grandPrix, date, cc, results);
	}

	@Override
	public String toString() {
		return "Race[" +
				"ID=" + ID + ", " +
				"grandPrix=" + grandPrix + ", " +
				"date=" + date + ", " +
				"cc=" + cc + ", " +
				"results=" + results + ']';
	}

}
