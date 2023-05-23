/**
 * Passwort-Generator-Paket.
 */
package softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht;

// Import-Anweisungen
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Definiert die PasswortGenerator-Klasse, die für die Erstellung von zufälligen
 * Passwörtern zuständig ist.
 */
public class PasswortGenerator {
	// Konstante für die Passwortlänge
	public int PASSWORD_LENGTH = 8;

	/**
	 * Erstellt ein zufälliges Passwort, das verschiedene Zeichentypen enthält.
	 *
	 * @return Ein zufällig generiertes Passwort als String.
	 */
	public String generateRandomPassword() {
		// Initialisiere das Passwort-Array und die offenen Stellen
		char[] passwordArray = new char[PASSWORD_LENGTH];
		List<Integer> openPlaces = IntStream.range(0, PASSWORD_LENGTH).boxed().collect(Collectors.toList());

		// Definiere die Regeln für die verschiedenen Zeichentypen
		int[][] rules = { { 'a', 'z' }, { 'A', 'Z' }, { 48, 57 }, { 40, 47 } };

		// Ersetze die leeren Array-Positionen mit den verschiedenen Zeichentypen
		passwordArray = replaceEmptyArrayPositon(passwordArray, openPlaces, rules[0][0], rules[0][1]); // Kleinbuchstaben
		passwordArray = replaceEmptyArrayPositon(passwordArray, openPlaces, rules[1][0], rules[1][1]); // Großbuchstaben
		passwordArray = replaceEmptyArrayPositon(passwordArray, openPlaces, rules[2][0], rules[2][1]); // Zahlen
		passwordArray = replaceEmptyArrayPositon(passwordArray, openPlaces, rules[3][0], rules[3][1]); // Sonderzeichen

		// Fülle die restlichen Positionen nach den Regeln auf
		for (int i = 1; i <= 4; i++) {
			int ruleToUse = (int) generateChar(0, 3);
			passwordArray = replaceEmptyArrayPositon(passwordArray, openPlaces, rules[ruleToUse][0],
					rules[ruleToUse][1]);
		}

		// Konvertiere das Passwort-Array in einen String und gib es zurück
		String password = new String(passwordArray);
		return password;
	}

	/**
	 * Erzeugt ein Zeichen im angegebenen Bereich.
	 *
	 * @param min Der untere Grenzwert für den Bereich.
	 * @param max Der obere Grenzwert für den Bereich.
	 * @return Ein zufällig generiertes Zeichen im angegebenen Bereich.
	 */
	public char generateChar(int min, int max) {
		int ascii = min + (int) (Math.random() * ((max - min) + 1));
		return (char) ascii;
	}

	/**
	 * Ersetzt eine leere Array-Position mit einem zufällig generierten Zeichen im
	 * angegebenen Bereich.
	 *
	 * @param password   Das Passwort-Array, das modifiziert werden soll.
	 * @param openPlaces Die Liste der offenen Positionen im Passwort-Array.
	 * @param min        Der untere Grenzwert für den Bereich.
	 * @param max        Der obere Grenzwert für den Bereich.
	 * @return Das modifizierte Passwort-Array mit der neuen Zeichenposition.
	 */
	public char[] replaceEmptyArrayPositon(char[] password, List<Integer> openPlaces, int min, int max) {
		if (openPlaces.size() == 0) {
			return password;
		}

		int tmp = generateChar(0, openPlaces.size() - 1);
		Integer place = openPlaces.get(tmp);
		password[place] = generateChar(min, max);
		openPlaces.remove(tmp);

		return password;
	}
}