package it.studiopharma.test.pizzagame.core;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import it.studiopharma.test.pizzagame.utils.ResultOutcome;

public class TurnMoveTest {

	@Test
	public void testGetPizzeToEat_NO_SKILL_SELECTED01() {
		String input = null;
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("NO_SKILL_SELECTED", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_NO_SKILL_SELECTED02() {
		String input = "";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);

		assertFalse(skill.isValid());
		assertEquals("NO_SKILL_SELECTED", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_NO_SKILL_SELECTED03() {
		String input = "  ";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("NO_SKILL_SELECTED", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_NO_NUMBER_VALUE01() {
		String input = "A";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("NO_NUMBER_VALUE", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_NO_NUMBER_VALUE02() {
		String input = "1A";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("NO_NUMBER_VALUE", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_INVALID_NUMBER_VALUE01() {
		String input = "5";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("INVALID_NUMBER_VALUE", skill.getMessage().getCaseCode());
	}

	@Test
	public void testGetPizzeToEat_0k01() {
		String input = "1";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_TWO);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertTrue(skill.isValid());
		// assertEquals("", skill.getMessage().getCaseCode());
		assertNull(skill.getMessage());
		assertNotNull(skill.getValue());
		assertNotNull(skill.getValue().getPizzeToEat());
		assertEquals(1, skill.getValue().getPizzeToEat());
	}

	@Test
	public void testGetPizzeToEat_ko01() {
		String input = "1";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertFalse(skill.isValid());
		assertEquals("SKILL_YET_SELECTED", skill.getMessage().getCaseCode());
		// assertNotNull( skill.getValue());
		// assertNotNull( skill.getValue().getPizzeToEat());
		// assertEquals(1, skill.getValue().getPizzeToEat());
	}

	@Test
	public void testGetPizzeToEat_0k02() {
		String input = "2";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertTrue(skill.isValid());
		// assertEquals("", skill.getMessage().getCaseCode());
		assertNotNull(skill.getValue());
		assertNull(skill.getMessage());
		assertNotNull(skill.getValue().getPizzeToEat());
		assertEquals(2, skill.getValue().getPizzeToEat());
	}

	@Test
	public void testGetPizzeToEat_0k03() {
		String input = "3";
		Optional<TurnMove> optSkill = Optional.of(TurnMove.EAT_ONE);
		ResultOutcome<TurnMove> skill = TurnMove.select(input, optSkill);
		assertTrue(skill.isValid());
		// assertEquals("", skill.getMessage().getCaseCode());
		assertNull(skill.getMessage());
		assertNotNull(skill.getValue());
		assertNotNull(skill.getValue().getPizzeToEat());
		assertEquals(3, skill.getValue().getPizzeToEat());
	}

}
