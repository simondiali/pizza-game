package it.studiopharma.test.pizzagame.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import it.studiopharma.test.pizzagame.utils.Constants;
import it.studiopharma.test.pizzagame.utils.StringUtils;

public class MessageResourcesSingleton {

	private static final String BOUNDLE_NOT_INITIALIZE = "Attenzione! Resource boundle non trovato per i codici di errore. Codice errore: ";
	private static final String MESSAGE_NOT_REGISTERED = "Attenzione! Nessun messaggio configurato per questo codice d'errore. Censirlo nel file di configurazione.";

	private static MessageResourcesSingleton instance = null;
	private static Properties properties;

	private MessageResourcesSingleton() {
		InputStream input = getClass().getClassLoader().getResourceAsStream("messages.properties");
		properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			System.err.println("Errore durante l'inizializzazione della configurazione messaggi");
		}
	}

	public static MessageResourcesSingleton getInstance()  {
		if (instance == null) {
			instance = new MessageResourcesSingleton();
		}
		return instance;
	}

	public static String getFullMessage(MessageEnum errorCase) {
		String msg = getMessage(errorCase);
		//LOGGER.error(msg);
		return formatFullMessage(errorCase, msg);
	}

	public static String getFullMessage(MessageEnum errorCase, String arg) {
		String msg = getMessage(errorCase, StringUtils.ensureArg(arg));
		//LOGGER.error(msg);
		return formatFullMessage(errorCase, msg);
	}

	public static String getFullMessage(MessageEnum errorCase, Integer arg) {
		String msg = getMessage(errorCase, StringUtils.ensureArg(arg));
		//LOGGER.error(msg);
		return formatFullMessage(errorCase, msg);
	}

	
	
	/* *** */

	/* *** */

	public static String getMessage(String key) {
		if (properties == null) {
			return StringUtils.concat(BOUNDLE_NOT_INITIALIZE, key);
		}

		String msg = properties.getProperty(key);
		if (StringUtils.isEmpty(msg)) {
			msg = MESSAGE_NOT_REGISTERED;
		}
		return msg;
	}

	public static String getMessage(MessageEnum errorCase) {
		getInstance();
		String key = errorCase.getKeyMessage();
		return getMessage(key);
	}

	private static String getMessage(MessageEnum errorCase, String arg) {
		String key = errorCase.getKeyMessage();
		String msg = String.format(getMessage(key), arg);
		return msg;
	}

	@SuppressWarnings("unused")
	private String getMessage(final MessageEnum errorCase, final Throwable t) {
		String msg = getExceptionMessage(t);
		if (StringUtils.isEmpty(msg)) {
			String key = errorCase.getKeyMessage();
			msg = String.format(getMessage(key));
		}
		return msg;
	}

	private static String formatFullMessage(MessageEnum errorCase, String msg) {
		return StringUtils.concat(Constants.SYMBOL_OPEN_BRACKET, errorCase.getKeyMessage(), Constants.SYMBOL_CLOSED_BRACKET, msg);
	}

	/* *** */

	private static String getExceptionMessage(Throwable t) {
		if (t == null) {
			return "";
		}

		if (StringUtils.isNotEmpty(t.getLocalizedMessage())) {
			return t.getLocalizedMessage();
		}
		if (StringUtils.isNotEmpty(t.getMessage())) {
			return t.getMessage();
		}
		if (StringUtils.isNotEmpty(t.toString())) {
			return t.toString();
		}

		return "";
	}

}
