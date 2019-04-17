/**
 * 
 */
package it.studiopharma.test.pizzagame.exception;

import it.studiopharma.test.pizzagame.messages.MessageEnum;
import it.studiopharma.test.pizzagame.messages.MessageResourcesSingleton;

/**
 * @author Simone Mondiali
 *
 */
public abstract class AbstractCustomException extends Exception {

	private static final long serialVersionUID = 6162107279501386220L;

	// private String fullCodeCase;
	// private String message;
	private MessageEnum unexpectedCase;

	public AbstractCustomException(MessageEnum unexpectedCase) {

		super(MessageResourcesSingleton.getMessage(unexpectedCase));
		this.unexpectedCase = unexpectedCase;
		// fullCodeCase = excCase.getPrefix().concat(excCase.getCaseCode());

	}

	// @Override
	// public String getMessage() {
	// return fullCodeCase;
	// }

}
