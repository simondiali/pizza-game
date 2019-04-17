package it.studiopharma.test.pizzagame.messages;

import it.studiopharma.test.pizzagame.utils.StringUtils;

/**
 * Casi inaspettati da gestire durante la scelta di una mossa.
 * 
 * @author Simone Mondiali
 *
 */
public enum InvalidSkillUnexpectedCase implements MessageEnum {

	NO_SKILL_SELECTED, //
	NO_NUMBER_VALUE, //
	INVALID_NUMBER_VALUE, //
	SKILL_YET_SELECTED,//
	;

	private final static String PREFIX = "INVALIDSKILL.";

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
