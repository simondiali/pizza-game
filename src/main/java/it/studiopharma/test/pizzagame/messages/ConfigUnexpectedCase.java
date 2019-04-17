package it.studiopharma.test.pizzagame.messages;

import it.studiopharma.test.pizzagame.utils.StringUtils;

/**
 * @author Simone Mondiali
 *
 */
public enum ConfigUnexpectedCase implements MessageEnum {

	BAD_MIN_PIZZE_CONFIG, //
	BAD_MAX_PIZZE_CONFIG, //
	;

	private final static String PREFIX = "CONFIG.";

	public String getPrefix() {
		return PREFIX;
	}

	public String getCaseCode() {
		return name();
	}

	@Override
	public String getKeyMessage() {
		return StringUtils.concat(PREFIX, name());
	}

}
