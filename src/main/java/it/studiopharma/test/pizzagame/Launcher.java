package it.studiopharma.test.pizzagame;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.studiopharma.test.pizzagame.core.PizzaGame;

public class Launcher {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = null;
		int statusReturn = 0;

		try {
			context = new AnnotationConfigApplicationContext(AppContext.class);
			PizzaGame pizzaGame = context.getBean(PizzaGame.class);
			pizzaGame.start();

		} catch (Exception e) {
			statusReturn = 1;
		} finally {
			if (context != null) {
				context.close();
			}
		}

		System.exit(statusReturn);

	}

}
