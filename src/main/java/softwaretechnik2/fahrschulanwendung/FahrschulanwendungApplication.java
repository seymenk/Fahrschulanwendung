package softwaretechnik2.fahrschulanwendung;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(FahrschulanwendungApplication.class);
	
	/**
	 * Startet die Anwendung und gibt den aktuellen Standort aus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		System.out.println("Standort: " + Locale.getDefault());
		SpringApplication.run(FahrschulanwendungApplication.class, args);
		logger.info("Projekt gestartet");
	}
}
