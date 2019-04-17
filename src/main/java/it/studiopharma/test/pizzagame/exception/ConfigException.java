/**
 * 
 */
package it.studiopharma.test.pizzagame.exception;

import it.studiopharma.test.pizzagame.messages.ConfigUnexpectedCase;

/**
 * @author Simone Mondiali
 *
 */
public class ConfigException extends AbstractCustomException {

	private static final long serialVersionUID = 2805184327523115247L;

	public ConfigException(ConfigUnexpectedCase excCase) {
		super(excCase);
	}

}
