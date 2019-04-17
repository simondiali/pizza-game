package it.studiopharma.test.pizzagame.core;

import java.util.Optional;

/**
 * @author Simone Mondiali
 *
 */
public class PizzaGame {

	private PizzeTable pizzeTable;
	private PlayerA playerA;
	private PlayerB playerB;
	private Player playerInTurn;
	private Optional<String> initErrorMessage;

	public PizzaGame() {
		initErrorMessage = Optional.empty();
	}

	public void setPizzeTable(PizzeTable pizzeTable) {
		this.pizzeTable = pizzeTable;
	}

	public void setPlayerA(PlayerA playerA) {
		this.playerA = playerA;
	}

	public void setPlayerB(PlayerB playerB) {
		this.playerB = playerB;
	}

	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	public void start() {

		if (initErrorMessage.isPresent()) {
			System.out.println(initErrorMessage.get());
			return;
		}

		// init();
		boolean isFinishedPizze = false;
		do {
			Prompt.declareQuantityTable(pizzeTable);
			playerInTurn.eseguiMossa(pizzeTable);

			isFinishedPizze = pizzeTable.isFinished();
			swichtPlayer();
		} while (!isFinishedPizze);
		Prompt.declareWinner(playerInTurn);
	}

	public void init() {
		MessagePrompt.messageSplash();

		this.playerA.setOpponent(this.playerB);
		this.playerB.setOpponent(this.playerA);
		Prompt.askName(this.playerA);
		Prompt.askName(this.playerB);
		this.playerInTurn = playerA;
	}

	void swichtPlayer() {
		this.playerInTurn = this.playerInTurn.getOpponent();
	}

	public void setInitErrorMessage(String message) {
		this.initErrorMessage = Optional.of(message);

	}

}
