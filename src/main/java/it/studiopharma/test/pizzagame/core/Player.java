package it.studiopharma.test.pizzagame.core;

import java.util.Optional;

public abstract class Player implements IPlayer {

	private Player opponent;
	private String playerName;
	private Optional<TurnMove> lastSkill;

	public Player() {
		this.lastSkill = Optional.empty();
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	
	public String getName() {
		return playerName;
	}

	public Optional<TurnMove> getLastSkill() {
		return lastSkill;
	}
	
	public void eseguiMossa(PizzeTable pizzeStack) {
		TurnMove move = Prompt.askMove(this, pizzeStack);
		lastSkill = Optional.of(move);
		pizzeStack.eat(move);
//		return skill;
	}
}
