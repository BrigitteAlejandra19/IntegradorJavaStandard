package vehicle.patent;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PatentGenerator {

	private final static String ALPHABETICAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String NUMBERS = "1234567890";
	private final static Set<String> patents = new HashSet<>();

	public static String getCharacters(Integer length) {

		return getRandom(length, ALPHABETICAL);
	}

	public static String getNumbers(Integer length) {
		return getRandom(length, NUMBERS);

	}

	/**
	 * 1)Cada automotor tiene una PATENTE única que se asigna automáticamente
	 * al realizar el alta o registro.Formatos de patente: AA123BB o ABC123.
	 *
	 * @return patent
	 */
	public static String getNewPatent() {
		String candidatePatent = getCharacters(2) + getNumbers(3) + getCharacters(2);
		if (patents.contains(candidatePatent)) {
			return getNewPatent();
		}
		patents.add(candidatePatent);
		return candidatePatent;
	}

	private static String getRandom(Integer length, String values) {
		String numFac = "";

		final int N = values.length();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			numFac += values.charAt(rnd.nextInt(N));
		}
		return numFac;
	}
}
