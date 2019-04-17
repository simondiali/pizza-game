package pizzagame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.studiopharma.test.pizzagame.AppContext;
import it.studiopharma.test.pizzagame.exception.ConfigException;
import it.studiopharma.test.pizzagame.utils.PropertiesConfig;

/**
 * @author Simone Mondiali
 *
 */
//  locations = { "classpath:context.xml" }
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {AppContext.class})
public class PropertiesConfigIT {

	@Autowired
	private PropertiesConfig istance;

	/**
	 * Test method for
	 * {@link it.studiopharma.test.pizzagame.utils.PropertiesConfig#getMaxPizzeProperties()}.
	 * @throws ConfigException 
	 */
	@Test
	public void testGetMaxPizze() throws ConfigException {
		String maxPizzeP = istance.getMaxPizzeProperties();
		assertEquals("50", maxPizzeP);
		
		int maxPizze = istance.getMaxPizze();
		assertEquals(50, maxPizze);
		
		String minPizzeP = istance.getMinPizzeProperties();
		assertEquals("10", minPizzeP);
		
		int minPizze = istance.getMinPizze();
		assertEquals(10, minPizze);
	}
	
	//TODO: testare come unit test mockando il file di properties

}
