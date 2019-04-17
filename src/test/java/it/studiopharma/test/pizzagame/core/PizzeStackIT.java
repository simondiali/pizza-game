/**
 * 
 */
package it.studiopharma.test.pizzagame.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.studiopharma.test.pizzagame.AppContext;
import it.studiopharma.test.pizzagame.exception.ConfigException;
import it.studiopharma.test.pizzagame.utils.MiscUtils;
import it.studiopharma.test.pizzagame.utils.PropertiesConfig;

/**
 * @author Simone Mondiali
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppContext.class })
public class PizzeStackIT {

	@Autowired
	private PizzeTable pizzeStack;
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	/**
	 * Test method for
	 * {@link it.studiopharma.test.pizzagame.core.PizzeTable#getNrPizze()}.
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void testGetNrPizze() throws ConfigException {
		int min = propertiesConfig.getMinPizze();
		int max = propertiesConfig.getMaxPizze();
		for (int i = 0; i < 1000; i++) {
			pizzeStack.init();
			int nrPizze = pizzeStack.getNrPizze();
			System.out.println(nrPizze);
			assertTrue(nrPizze >= min);
			assertTrue(nrPizze < max);
		}

	}

}
