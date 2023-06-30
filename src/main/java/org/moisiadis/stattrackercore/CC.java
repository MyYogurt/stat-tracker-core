package org.moisiadis.stattrackercore;

import java.util.Optional;

public enum CC {
	FIFTY("50"), ONE_HUNDRED("100"), ONE_HUDNRED_FIFTY("150"), MIRROR("Mirror"), TWO_HUNDRED("200");

	private final String name;

	CC(String name) {
		this.name = name;
	}

	public static Optional<CC> fromString(String name) {
		for (CC cc : CC.values()) {
			if (cc.name.equals(name) || cc.name().equals(name)) {
				return Optional.of(cc);
			}
		}
		return Optional.empty();
	}

	@Override
	public String toString() {
		return name;
	}
}
