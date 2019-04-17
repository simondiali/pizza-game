/**
 * 
 */
package it.studiopharma.test.pizzagame.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.studiopharma.test.pizzagame.AppContext;

/**
 * @author Simone Mondiali
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppContext.class })
public class PizzaGameIT {
	
	@Autowired
	private PizzaGame pizzaGame;

	/**
	 * Testa il funzionamento del passaggio di turno da un giocatore all'altro.
	 * 
	 * Test method for {@link it.studiopharma.test.pizzagame.core.PizzaGame#swichtPlayer()}.
	 */
	@Test
	public void testSwichtPlayer() {
		//testiamo che il primo utente è A
		Player playerInTurn = pizzaGame.getPlayerInTurn();
		System.out.println("Turno di " + playerInTurn.getName());
		OrderPlayer order = playerInTurn.getOrder();
		assertEquals(OrderPlayer.ONE, order);
		pizzaGame.swichtPlayer();
		
		playerInTurn = pizzaGame.getPlayerInTurn();
		System.out.println("Turno di " + playerInTurn.getName());
		order = playerInTurn.getOrder();
		assertEquals(OrderPlayer.TWO, order);
		pizzaGame.swichtPlayer();
		
		playerInTurn = pizzaGame.getPlayerInTurn();
		System.out.println("Turno di " + playerInTurn.getName());
		order = playerInTurn.getOrder();
		assertEquals(OrderPlayer.ONE, order);
		pizzaGame.swichtPlayer();
		
		playerInTurn = pizzaGame.getPlayerInTurn();
		System.out.println("Turno di " + playerInTurn.getName());
		order = playerInTurn.getOrder();
		assertEquals(OrderPlayer.TWO, order);
		pizzaGame.swichtPlayer();

	}

}
