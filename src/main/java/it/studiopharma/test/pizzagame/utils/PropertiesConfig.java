/**
 * 
 */
package it.studiopharma.test.pizzagame.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.studiopharma.test.pizzagame.exception.ConfigException;
import it.studiopharma.test.pizzagame.messages.ConfigUnexpectedCase;

/**
 * @author Simone Mondiali
 *
 */
@Configuration
@PropertySource("classpath:config.properties")
public class PropertiesConfig {

	@Value("${game.pizze.min}")
	private String minPizze;

	@Value("${game.pizze.max}")
	private String maxPizze;

	public String getMinPizzeProperties() {
		return minPizze;
	}

	public String getMaxPizzeProperties() {
		return maxPizze;
	}

	public int getMinPizze() throws ConfigException {
		try {
			return Integer.parseInt(minPizze);
		} catch (NumberFormatException e) {
			throw new ConfigException(ConfigUnexpectedCase.BAD_MIN_PIZZE_CONFIG);
		}
	}

	public int getMaxPizze() throws ConfigException {

		try {
			return Integer.parseInt(maxPizze);
		} catch (NumberFormatException e) {
			throw new ConfigException(ConfigUnexpectedCase.BAD_MAX_PIZZE_CONFIG);
		}
	}

}
