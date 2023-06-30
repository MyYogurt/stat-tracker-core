package org.moisiadis.stattrackercore.model;

import java.io.Serializable;
import java.util.Objects;

public final class Driver {
	private int id;
	private String name;

	public Driver(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Driver) obj;
		return this.id == that.id &&
				Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Driver[" +
				"id=" + id + ", " +
				"name=" + name + ']';
	}

}
