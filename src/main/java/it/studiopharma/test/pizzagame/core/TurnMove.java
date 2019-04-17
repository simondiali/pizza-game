package it.studiopharma.test.pizzagame.core;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import it.studiopharma.test.pizzagame.messages.InvalidSkillUnexpectedCase;
import it.studiopharma.test.pizzagame.utils.ResultOutcome;

/**
 * @author Simone Mondiali
 *
 */
public enum TurnMove {

	EAT_ONE(1, false), //

	EAT_TWO(2, false), //

	EAT_THREE(3, false), //

	TO_PASS(0, true),;
	private int pizzeToEat;
	private boolean skipTheTurn;

	private TurnMove(int pizzeToEat, boolean skipTheTurn) {
		this.pizzeToEat = pizzeToEat;
		this.skipTheTurn = skipTheTurn;
	}

	public int getPizzeToEat() {
		return pizzeToEat;
	}

	public boolean isSkipTheTurn() {
		return skipTheTurn;
	}

	public static ResultOutcome<TurnMove> select(String input, Optional<TurnMove> optionalPrevSkillOpponent) {
		if (StringUtils.isBlank(input)) {
			return new ResultOutcome<TurnMove>(InvalidSkillUnexpectedCase.NO_SKILL_SELECTED);
		}
		input = input.trim();

		Integer nrPizzeSelected = null;
		try {
			nrPizzeSelected = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return new ResultOutcome<TurnMove>(InvalidSkillUnexpectedCase.NO_NUMBER_VALUE);
		}

		Optional<TurnMove> optSkillSelected = Optional.empty();

		for (TurnMove turn : values()) {
			if (turn.getPizzeToEat() == nrPizzeSelected.intValue()) {
				optSkillSelected = Optional.of(turn);
			}
		}

		if (!optSkillSelected.isPresent()) {
			return new ResultOutcome<TurnMove>(InvalidSkillUnexpectedCase.INVALID_NUMBER_VALUE);
		}

		TurnMove skillSelected = optSkillSelected.get();

		if (optionalPrevSkillOpponent.isPresent()) {
			if (optionalPrevSkillOpponent.get().equals(skillSelected)) {
				return new ResultOutcome<TurnMove>(InvalidSkillUnexpectedCase.SKILL_YET_SELECTED);
			}
		}

		return new ResultOutcome<TurnMove>(skillSelected);
	}

	// private void checkSkillOpponent(TurnSkill skillSelected, Optional<TurnSkill>
	// optionalPrevSkillOpponent) {
	//
	// if (optionalPrevSkillOpponent.isPresent()) {
	// if (optionalPrevSkillOpponent.get().equals(skillSelected)) {
	// return new ResultOutcome<TurnSkill>(false,
	// InvalidSkillExceptionCase.SKILL_YET_SELECTED.getCaseCode());
	// }
	//
	// }
	//
	//
	// }

}
