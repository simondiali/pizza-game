package it.studiopharma.test.pizzagame.core;

import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import it.studiopharma.test.pizzagame.messages.MessageEnum;
import it.studiopharma.test.pizzagame.messages.MessageResourcesSingleton;
import it.studiopharma.test.pizzagame.utils.ResultOutcome;

class MessagePrompt {

	private static final String END_MESSAGE = ":\n>> ";

	static void messageSplash() {
		System.out.println("        _....._");
		System.out.println("    _.:`.--|--.`:._");
		System.out.println("  .: .'\\o  | o /'. '.");
		System.out.println(" // '.  \\ o|  /  o '.\\");
		System.out.println("//'._o'. \\ |o/ o_.-'o\\\\");
		System.out.println("|| o '-.'.\\|/.-' o   ||");
		System.out.println("||--o--o-->|");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("  _____ _                 _____                      ");
		System.out.println(" |  __ (_)               / ____|                     ");
		System.out.println(" | |__) | __________ _  | |  __  __ _ _ __ ___   ___ ");
		System.out.println(" |  ___/ |_  /_  / _` | | | |_ |/ _` | '_ ` _ \\ / _ \\");
		System.out.println(" | |   | |/ / / / (_| | | |__| | (_| | | | | | |  __/");
		System.out.println(" |_|   |_/___/___\\__,_|  \\_____|\\__,_|_| |_| |_|\\___|");
		System.out.println(" ");
		System.out.println(" ");
	}

	static void messageAskName(Player player) {
		System.out.print(String.format("indicare il nome del giocatore %s %s", player.getOrder().name(), END_MESSAGE));
	}

	static void messageAskMove(Player player) {
		System.out.print(
				String.format("Turno del giocatore %s %s %s", player.getOrder().name(), player.getName(), END_MESSAGE));
	}

	static void messageAskMove(Player player, Player opponent, Optional<TurnMove> optOpponentSkill) {
		System.out.print(
				String.format("Turno del giocatore %s %s (precente mossa di %s : %d) %s", player.getOrder().name(),
						player.getName(), opponent.getName(), optOpponentSkill.get().getPizzeToEat(), END_MESSAGE));
	}

	static void messageNoValidMove(Player player) {
		System.out.println(String.format("Non ci sono mosse valide per il giocatore %s. Obbligato a saltare il turno",
				player.getName()));
	}

	static void messageDeclareWinner(Player player) {
		System.out.println(String.format("\nVince il giocatore %s !!!!!!!!!!!!!", player.getName()));
	}

	static void messageDeclareQuantityTable(PizzeTable table) {
		System.out.println(String.format("Pizze presenti: %d", table.getNrPizze()));
		
	}

	static void messageOnUnexpectedCase(MessageEnum unnexpectedCase) {
		System.out.println(MessageResourcesSingleton.getMessage(unnexpectedCase));
	}

}

/**
 * @author Simone Mondiali
 *
 */
public class Prompt {

	private static Scanner scanner = new Scanner(System.in);

	public static void askName(Player player) {
		MessagePrompt.messageAskName(player);
		String playerName = null;
		do {
			playerName = scanner.nextLine();
			player.setPlayerName(playerName);
		} while (StringUtils.isBlank(playerName));
	}

	public static TurnMove askMove(Player player, PizzeTable pizzeTable) {
		Player opponent = player.getOpponent();
		Optional<TurnMove> optOpponentSkill = opponent.getLastSkill();
		if (optOpponentSkill.isPresent()) {
			TurnMove opponentMove = optOpponentSkill.get();
			if (userHasNoValidMoves(opponentMove, pizzeTable)) {
				MessagePrompt.messageNoValidMove(player);
				return TurnMove.TO_PASS;
			}
		}

		return selectValidMove(player, pizzeTable, opponent, optOpponentSkill);
	}

	private static TurnMove selectValidMove(Player player, PizzeTable pizzeTable, Player opponent,
			Optional<TurnMove> optOpponentSkill) {
		boolean lastSkillIsValid = false;
		ResultOutcome<TurnMove> skillOutcome = null;
		do {
			if (optOpponentSkill.isPresent()) {
				MessagePrompt.messageAskMove(player, opponent, optOpponentSkill);
			} else {
				MessagePrompt.messageAskMove(player);
			}

			String turnSelected = scanner.nextLine();
			skillOutcome = TurnMove.select(turnSelected, optOpponentSkill);
			lastSkillIsValid = skillOutcome.isValid();
			if (!lastSkillIsValid) {
				MessagePrompt.messageOnUnexpectedCase(skillOutcome.getMessage());
			}
		} while (!lastSkillIsValid);
		return skillOutcome.getValue();
	}

	private static boolean userHasNoValidMoves(TurnMove opponentMove, PizzeTable pizzeTable) {
		return opponentMove.equals(TurnMove.EAT_ONE) && pizzeTable.remainingOnePizzaOnTable();
	}

	public static void declareWinner(Player player) {
		MessagePrompt.messageDeclareWinner(player);
	}

	public static void declareQuantityTable(PizzeTable pizzaTable) {
		MessagePrompt.messageDeclareQuantityTable(pizzaTable);
	}

}
