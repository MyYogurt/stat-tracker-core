package org.moisiadis.stattrackercore;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CCTest {
	@Test
	public void testFromStringFifty() {
		String name = "50";
		Optional<CC> optionalCC = CC.fromString(name);
		assertTrue(optionalCC.isPresent());
		CC cc = optionalCC.get();
		assertEquals(CC.FIFTY, cc);
	}

	@Test
	public void testFromStringOneHundred() {
		String name = "100";
		Optional<CC> optionalCC = CC.fromString(name);
		assertTrue(optionalCC.isPresent());
		CC cc = optionalCC.get();
		assertEquals(CC.ONE_HUNDRED, cc);
	}

	@Test
	public void testFromStringOneHundredFifty() {
		String name = "150";
		Optional<CC> optionalCC = CC.fromString(name);
		assertTrue(optionalCC.isPresent());
		CC cc = optionalCC.get();
		assertEquals(CC.ONE_HUDNRED_FIFTY, cc);
	}
}