/**
 * 
 */
package it.studiopharma.test.pizzagame.utils;

import it.studiopharma.test.pizzagame.messages.MessageEnum;

/**
 * @author Simone Mondiali
 *
 */
public class ResultOutcome<V> {
	private boolean valid;
	private MessageEnum message;
	private V value;

	public ResultOutcome(MessageEnum message) {
		super();
		this.valid = false;
		this.message = message;
	}

	public ResultOutcome(V value) {
		super();
		this.valid = true;
		this.value = value;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public MessageEnum getMessage() {
		return message;
	}

	public void setMessage(MessageEnum message) {
		this.message = message;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
