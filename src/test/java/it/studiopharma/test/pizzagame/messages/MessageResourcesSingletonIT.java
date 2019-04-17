package it.studiopharma.test.pizzagame.messages;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class MessageResourcesSingletonIT {

	@Test
	public void test() throws IOException {

		InputStream input = this.getClass().getClassLoader().getResourceAsStream("messages.properties");

		Properties prop = new Properties();
		prop.load(input);

		String minPizzeMsg1 = prop.getProperty("CONFIG.BAD_MIN_PIZZE_CONFIG");
		String maxPizzeMsg1 = prop.getProperty("CONFIG.BAD_MAX_PIZZE_CONFIG");

		System.out.println(minPizzeMsg1);
		System.out.println(maxPizzeMsg1);

//		MessageResourcesSingleton.setProperties(prop, "messages.properties");

		String minPizzeMsg2 = MessageResourcesSingleton.getMessage(ConfigUnexpectedCase.BAD_MIN_PIZZE_CONFIG);
		String maxPizzeMsg2 = MessageResourcesSingleton.getMessage(ConfigUnexpectedCase.BAD_MAX_PIZZE_CONFIG);
		System.out.println(minPizzeMsg2);
		System.out.println(maxPizzeMsg2);

		assertEquals(minPizzeMsg1, minPizzeMsg2);
		assertEquals(maxPizzeMsg1, maxPizzeMsg2);

	}

}
