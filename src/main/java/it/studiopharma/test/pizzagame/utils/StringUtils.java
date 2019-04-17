package it.studiopharma.test.pizzagame.utils;

import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Classe che definisce alcuni metodi di utilita' per la manipolazione di
 * stringhe
 * 
 * @author <a href="mailto:simone.mondiali@consultant.aruba.it">Simone
 *         Mondiali</a> 02 mag 2018 - 17:06:51
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final String ZERO_LINE = "00000000000000000000";

	private StringUtils() {
	}

	/**
	 * Concatena stringhe facendo uso di {@code: StringBuilder} al fine di
	 * migliorare le performance.
	 * 
	 * @param values
	 *            stringhe da concatenare in numero variabile.
	 * @return
	 */
	public static String concat(final String... values) {
		final StringBuilder sb = new StringBuilder();
		try {
			for (String value : values) {
				value = defaultIfEmpty(value, "");
				sb.append(value);
			}
			return sb.toString();
		} catch (NullPointerException ne) {
			return "";
		}

	}

	/**
	 * Concatena due stringhe. Qualora il limite di lunghezza della stringa
	 * concatenata superi quella del limite passato come argomento tronca la prima
	 * stringa facendo in modo che la seconda arrivi ad occupare il limite della
	 * stringa concatenata che si vuole ottenere come risultato finale.
	 * 
	 * @param value1
	 * @param value2
	 * @param limit
	 * @return
	 */
	public static String concatMoveToRightLimit(String value1, String value2, int limit) {
		int lengthValue1 = value1.length();
		int lengthValue2 = value2.length();

		if ((lengthValue1 + lengthValue2) < limit) {
			return value1.concat(Constants.SYMBOL_SPACE).concat(value2);
		}

		int endValue1 = limit - lengthValue2 - 1;
		return substring(value1, endValue1).concat(Constants.SYMBOL_SPACE).concat(value2);
	}

	public static String concatMoveToRightLimit(String value1, String value2, int limit, int limit2) {
		int lengthValue1 = value1.length();
		int lengthValue2 = value2.length();

		if ((lengthValue1 + lengthValue2) < limit) {
			return value1.concat(Constants.SYMBOL_SPACE).concat(value2);
		}

		int endValue1 = limit - lengthValue2 - 1;
		return substring(value1, (endValue1 + limit2)).concat(Constants.SYMBOL_SPACE).concat(value2);
	}

	public static String removeSeparator(String value, String separator) {
		if (value == null || separator == null) {
			return null;
		}
		String split[] = value.split(separator);
		return StringUtils.concat(split);
	}

	public static String adaptToLength(String value, int length, char paddingChar, boolean isMandatory) {
		if (isEmpty(value)) {
			return isMandatory ? getPaddingChar(paddingChar, length) : Constants.EMPTY;
		}

		int valueLength = value.length();
		int diffLength = length - valueLength;

		if (diffLength == 0) {
			return value;
		}

		if (diffLength < 0) {
			return value.substring(0, length);
		}

		String padding = getPaddingChar(paddingChar, diffLength);
		return concat(value, padding);
	}

	public static String getPaddingChar(char paddingChar, int nrChar) {
		StringBuilder sb = new StringBuilder();
		while (nrChar > 0) {
			sb.append(paddingChar);
			nrChar--;
		}
		return sb.toString();
	}

	public static String getPaddingChar(String paddingChar, int nrChar) {
		StringBuilder sb = new StringBuilder();
		while (nrChar > 0) {
			sb.append(paddingChar);
			nrChar--;
		}
		return sb.toString();
	}

	public static String getAppendPaddedString(String string, char paddingChar, int fullLength) {
		int paddingCharNr = fullLength - string.length();
		String padding = getPaddingChar(paddingChar, paddingCharNr);
		return concat(string, padding);
	}

	public static String getPrependPaddedString(String string, char paddingChar, int fullLength) {
		int paddingCharNr = fullLength - string.length();
		String padding = getPaddingChar(paddingChar, paddingCharNr);
		return concat(padding, string);
	}

	/**
	 * Converte un intero nella sua rappresentazione testuale. Inserisce tanti
	 * caratteri '0' quanti richiesti a sinistra del numero ottenuto.
	 * 
	 * @param value
	 *            il valore da convertire
	 * @param length
	 *            la lunghezza totale della stringa richiesta
	 * 
	 * @return la stringa corrispondente al valore con gli eventuali '0' a sinistra
	 */
	public static final String getZeroedInteger(Integer value, int length) {
		if (value == null) {
			return null;
		}

		StringBuilder zeroedBuffer = new StringBuilder(32);

		zeroedBuffer.append(value);

		if (zeroedBuffer.length() > length) {
			return zeroedBuffer.toString().substring(0, length);
		}

		while (zeroedBuffer.length() < length) {
			zeroedBuffer.insert(0, '0');
		}

		return zeroedBuffer.toString();
	}

	/**
	 * Converte 'decimalValue' in esadecimale e appende tanti 0 quanti necessari a
	 * raggiungere la lunghezza specificata in 'lenhthString'.
	 * 
	 * @param decimalValue
	 * @param lenhthString
	 * @return
	 */
	public static String getHexString(int decimalValue, int lenhthString) {
		String hex = Integer.toHexString(decimalValue).toUpperCase();
		if (lenhthString > hex.length()) { // enlarge
			hex = ZERO_LINE.substring(0, lenhthString - hex.length()) + hex;
		}
		if (lenhthString < hex.length()) { // shorten
			hex = hex.substring(hex.length() - lenhthString);
		}
		return hex;
	}

	/**
	 * Converte un byte array nella sua rappresentazione esadecimale minuscola.
	 * <p>
	 * NOTE DI IMPORTAZIONE:<br>
	 * Importato da <code>com.multicertify.utils.Util.toHex</code><br/>
	 * Rispetto al codice importato e' stato aggiunto controllo sul valore null.
	 * 
	 * @author <a href="mailto:simone.mondiali@consultant.aruba.it">Simone
	 *         Mondiali</a> 18 ott 2018 - 15:21:11
	 ************
	 * 
	 * @param ba
	 *            il byte array da convertire
	 * 
	 * @return la rappresentazione esadecimale del parametro di input
	 */
	public static final String toHex(byte[] ba) {
		if (ba == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer(2 * ba.length);
		int h;
		int l;

		for (int i = 0; i < ba.length; i++) {
			h = (ba[i] & 0xf0) >> 4;
			l = (ba[i] & 0x0f);
			sb.append((char) ((h > 9) ? 'a' + h - 10 : '0' + h));
			sb.append((char) ((l > 9) ? 'a' + l - 10 : '0' + l));
		}

		return sb.toString();
	}

	public static String recombine(String value, String separator) {
		if (value == null || separator == null) {
			return null;
		}
		String split[] = value.split(separator);
		StringBuilder sb = new StringBuilder();

		String tempSeparator = "";

		for (int i = (split.length - 1); i >= 0; i--) {
			String field = split[i];
			sb.append(tempSeparator);
			sb.append(field);
			tempSeparator = separator;

		}

		return sb.toString();
	}

	public static String recombineWithoutSeparator(String value, String separator) {
		value = recombine(value, separator);
		if (value == null) {
			return null;
		}
		return removeSeparator(value, separator);
	}

	public static String recombineReplaceSeparator(String value, String oldSeparator, String newSeparator) {
		if (value == null || newSeparator == null) {
			return null;
		}
		value = recombine(value, oldSeparator);
		if (value == null) {
			return null;
		}
		value = value.replaceAll(oldSeparator, newSeparator);
		return value;
	}

	public static String substring(String value, int endIndex) {
		return substring(value, 0, endIndex);
	}

	public static String substring(String value, int beginIndex, int endIndex) {
		if (value == null) {
			return null;
		}

		beginIndex = beginIndex > endIndex ? endIndex : beginIndex;
		return value.length() > endIndex ? value.substring(beginIndex, endIndex) : value.substring(beginIndex);
	}

	public static String removeLastChar(final String value) {
		return removeLastChar(value, 1);
	}

	public static String removeLastChar(final String value, final int nrCharToRemove) {
		if (value == null) {
			return null;
		}
		final int fromRemove = (value.length() - nrCharToRemove);
		if (fromRemove <= 0) {
			return "";
		}
		return StringUtils.substring(value, 0, fromRemove);
	}

	/**
	 * Converte un valore {@code: BigInteger} nella sua rappresentazione esadecimale
	 * maiuscola.
	 * 
	 * @param value
	 *            valore di tipo {@code: BigInteger} da convertire.
	 * @return la rappresentazione esadecimale del valore di input
	 */
	public static final String toUpperHex(BigInteger value) {
		return toUpperHex(value.toByteArray());
	}

	/**
	 * Converte un valore {@code: String} nella sua rappresentazione esadecimale
	 * maiuscola.
	 * 
	 * @param value
	 *            valore di tipo {@code: String} da convertire.
	 * @return la rappresentazione esadecimale del valore di input
	 */
	public static final String toUpperHex(String value) {
		return toUpperHex(value.getBytes());
	}

	/**
	 * Converte un byte array nella sua rappresentazione esadecimale maiuscola.
	 * 
	 * @param ba
	 *            il byte array da convertire
	 * 
	 * @return la rappresentazione esadecimale del parametro di input
	 */
	public static final String toUpperHex(byte[] ba) {
		StringBuilder sb = new StringBuilder(2 * ba.length);
		int h;
		int l;

		for (int i = 0; i < ba.length; i++) {
			h = (ba[i] & 0xf0) >> 4;
			l = (ba[i] & 0x0f);
			sb.append((char) ((h > 9) ? 'A' + h - 10 : '0' + h));
			sb.append((char) ((l > 9) ? 'A' + l - 10 : '0' + l));
		}

		return sb.toString();
	}

	/**
	 * Esegue il padding a destra di un byte array con 0 binari. Se la lunghezza
	 * dell'array e' maggiore o uguale alla dimensione richiesta il padding non ha
	 * luogo.
	 *
	 * @param bs
	 *            byte array sul quale eseguire il padding
	 * @param size
	 *            dimensione del nuovo byte array contenente il padding richiesto
	 *
	 * @return il nuovo byte array con il padding richiesto, il parametro in input
	 *         se il padding non ha luogo
	 **/
	public static byte[] rightPadWithZero(byte[] bs, int size) {
		// lunghezza maggiore della dimensione richiesta, padding inutile
		if (bs.length >= size) {
			return bs;
		}

		byte[] nbs = new byte[size];

		Arrays.fill(nbs, bs.length, size, (byte) 0);
		System.arraycopy(bs, 0, nbs, 0, bs.length);
		return nbs;
	}

	public static String toUpperCase(String value) {
		if (value == null) {
			return null;
		}
		return value.toUpperCase();
	}

	public static String removeLastIndexOf(String value, String textToRemove) {
		int positionToRemove = value.lastIndexOf(textToRemove);
		return substring(value, 0, positionToRemove);
	}

	public static String ensureArg(String value) {
		if (value == null) {
			return Constants.UNKNOWED;
		}
		return value;
	}

	public static String ensureArg(Integer value) {
		if (value == null) {
			return Constants.UNKNOWED;
		}
		return String.valueOf(value);
	}

	public static String ensureArg(File file) {
		if (file == null) {
			return Constants.UNKNOWED;
		}
		return file.getAbsolutePath();
	}

}
