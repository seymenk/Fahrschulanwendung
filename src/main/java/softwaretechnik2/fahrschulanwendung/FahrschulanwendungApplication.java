package softwaretechnik2.fahrschulanwendung;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse für die Fahrschulanwendung. Diese Klasse enthält die
 * main-Methode, die die Anwendung startet.
 * 
 * @author seymen
 *
 */
@SpringBootApplication
public class FahrschulanwendungApplication {

	/**
	 * Startet die Anwendung und gibt den aktuellen Standort aus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Standort: " + Locale.getDefault());
		SpringApplication.run(FahrschulanwendungApplication.class, args);
	}
}
