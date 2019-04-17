/**
 * 
 */
package it.studiopharma.test.pizzagame.core;

import org.junit.Before;
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
public class PromptIT {

	Player pA = new PlayerA();
	Player pB = new PlayerB();
	
	@Autowired
	private PizzeTable pizzeTable;		

	
	@Before
	public void setup() {
		pA.setOpponent(pB);
		pA.setPlayerName("Mark");		
		pB.setOpponent(pA);
		pB.setPlayerName("John");		
	}

	/**
	 * Test method for
	 * {@link it.studiopharma.test.pizzagame.core.Prompt#askMove(it.studiopharma.test.pizzagame.core.Player)}.
	 */
	@Test
	public void testAskMove() {
		
		
		TurnMove move = Prompt.askMove(pA, pizzeTable);
		System.out.println("move " + move);
		
//		String turnSelected = null;
//
//		boolean lastSkillIsValid = false;
//
//		do {
//			turnSelected = Prompt.askSkill(pA);
//			ResultOutcome<TurnSkill> skillOutcome = TurnSkill.select(turnSelected);
//			lastSkillIsValid = skillOutcome.isValid();
////			System.out.println("skillOutcome.isValid() " + skillOutcome.isValid());
////			System.out.println("skillOutcome.getMessage() " + skillOutcome.getMessage());
////			System.out.println("skillOutcome.getValue() " + skillOutcome.getValue());
//			if(!lastSkillIsValid) {
//				System.out.println(skillOutcome.getMessage());
//			}
//		} while (!lastSkillIsValid);

	}
	
	@Test
	public void testAsName() {		
		Prompt.askName(pA);
		System.out.println(pA.getName());
	}

}
