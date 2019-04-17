/**
 * 
 */
package it.studiopharma.test.pizzagame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import it.studiopharma.test.pizzagame.core.PizzaGame;
import it.studiopharma.test.pizzagame.core.PizzeTable;
import it.studiopharma.test.pizzagame.core.PlayerA;
import it.studiopharma.test.pizzagame.core.PlayerB;
import it.studiopharma.test.pizzagame.exception.ConfigException;
import it.studiopharma.test.pizzagame.utils.PropertiesConfig;

/**
 * @author Simone Mondiali
 *
 */
@Configuration
@ComponentScan(basePackages = { "it.studiopharma.test.pizzagame.*" })
public class AppContext {

	@Bean
	public PropertiesConfig propertiesConfig() throws ConfigException {
		// TODO: gestire eccezione in inizializzazione
		return new PropertiesConfig();
	}

	@Bean
	public PizzeTable pizzeTable() throws ConfigException {
		PizzeTable pizzeTable = new PizzeTable();
		pizzeTable.setPropertiesConfig(propertiesConfig());
		pizzeTable.init();
		return pizzeTable;
	}

	@Bean
	public PlayerA playerA() {
		return new PlayerA();
	}

	@Bean
	public PlayerB playerB() {
		return new PlayerB();
	}

	@Bean
	public PizzaGame pizzaGame() {
		PizzaGame pizzaGame = new PizzaGame();
		try {

			pizzaGame.setPizzeTable(pizzeTable());
			pizzaGame.setPlayerA(playerA());
			pizzaGame.setPlayerB(playerB());
			pizzaGame.init();

		} catch (ConfigException e) {

			String message = e.getMessage();
			pizzaGame.setInitErrorMessage(message);

		}
		return pizzaGame;
	}

}
