package it.studiopharma.test.pizzagame.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MiscUtilsTest {

	@Test
	public void testGetRandomByIntervall() {

		int min = 10;
		int max = 50;
		int value = 0;

		for (int i = 0; i < 1000; i++) {
			value = MiscUtils.getRandomByIntervall(min, max);
			System.out.println(value);
			assertTrue(value >= min);
			assertTrue(value < max);
		}

	}

}
