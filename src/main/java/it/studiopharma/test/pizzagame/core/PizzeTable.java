package it.studiopharma.test.pizzagame.core;

import it.studiopharma.test.pizzagame.exception.ConfigException;
import it.studiopharma.test.pizzagame.utils.MiscUtils;
import it.studiopharma.test.pizzagame.utils.PropertiesConfig;

/**
 * @author Simone Mondiali
 *
 */
public class PizzeTable {

	private PropertiesConfig propertiesConfig;

	public void setPropertiesConfig(PropertiesConfig propertiesConfig) {
		this.propertiesConfig = propertiesConfig;
	}

	private int nrPizze;

	public void init() throws ConfigException {
		int min = propertiesConfig.getMinPizze();
		int max = propertiesConfig.getMaxPizze();
		this.nrPizze = MiscUtils.getRandomByIntervall(min, max);
	}

	public int getNrPizze() {
		return nrPizze;
	}

	public void eat(TurnMove move) {
		if (move.isSkipTheTurn()) {
			return;
		}
		this.nrPizze = this.nrPizze - move.getPizzeToEat();
	}

	public boolean isFinished() {
		return this.nrPizze <= 0;
	}

	public boolean remainingOnePizzaOnTable() {
		return this.nrPizze == 1;
	}

}
