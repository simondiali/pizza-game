package pizzagame;

import java.util.Random;

import org.junit.Test;

public class Misc {

	Random rnd = new Random();
	final static int max = 50;
	final static int min = 10;
	@Test
	public void test() {
		System.out.println(heigth());
		System.out.println(heigth());
		System.out.println(heigth());
		System.out.println(heigth());
		System.out.println(heigth());
		System.out.println(heigth());
	}
	
	private int heigth() {
		return min + rnd.nextInt(max);
	}

}
