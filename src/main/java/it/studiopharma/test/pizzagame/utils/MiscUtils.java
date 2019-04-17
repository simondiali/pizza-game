/**
 * 
 */
package it.studiopharma.test.pizzagame.utils;

import java.util.Random;

/**
 * @author Simone Mondiali
 *
 */
public class MiscUtils {

	private static Random rnd = new Random();

	public static int getRandomByIntervall(int min, int max) {
		max  = max - min;
		return min + rnd.nextInt(max);
	}

}
