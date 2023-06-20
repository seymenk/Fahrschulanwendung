package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Die BenutzerverwaltungFassade Klasse dient als Zwischenschicht zwischen der
 * Anwendungsschicht und der Datenschicht. Sie bietet Methoden zur Interaktion
 * mit dem UserDAO, FahrlehrerDAO und FahrschuelerDAO. Sie wird als Spring
 * Komponente definiert und verwendet die Autowiring Funktion von Spring, um
 * Abhängigkeiten zu injizieren.
 * 
 * @author seymen
 */
@Component
public class BenutzerverwaltungFassade {

	@Autowired
	private FahrlehrerDAO fahrlehrerDAO;

	@Autowired
	private FahrschuelerDAO fahrschuelerDAO;

	@Autowired
	private UserDAO userDAO;

	private final Logger logger = LoggerFactory.getLogger(BenutzerverwaltungFassade.class);

	/**
	 * Sucht und gibt ein Benutzerkonto zurück, das mit dem angegebenen
	 * Benutzernamen und Passwort übereinstimmt.
	 * 
	 * @param username Der Benutzername des Benutzerkontos.
	 * @param password Das Passwort des Benutzerkontos.
	 * @return Das Benutzerkonto, das mit dem angegebenen Benutzernamen und Passwort
	 *         übereinstimmt, oder null, wenn kein solches Benutzerkonto gefunden
	 *         wurde.
	 */
	public User findByUsernameAndPassword(String username, String password) {
		return userDAO.findByUsernameAndPassword(username, password);
	}

	/**
	 * Überprüft, ob ein Benutzerkonto mit dem angegebenen Benutzernamen existiert.
	 * 
	 * @param username Der zu überprüfende Benutzername.
	 * @return true, wenn ein Benutzerkonto mit dem angegebenen Benutzernamen
	 *         existiert, sonst false.
	 */
	public boolean existsByUsername(String username) {
		return userDAO.existsByUsername(username);
	}

	/**
	 * Sucht und gibt ein Benutzerkonto zurück, das mit dem angegebenen
	 * Benutzernamen übereinstimmt.
	 * 
	 * @param username Der Benutzername des Benutzerkontos.
	 * @return Ein Optional, das das Benutzerkonto enthält, wenn es gefunden wurde,
	 *         oder sonst Optional.empty().
	 */
	public Optional<User> findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	/**
	 * Speichert das gegebene Benutzerkonto in der Datenbank und gibt es zurück.
	 * 
	 * @param user Das zu speichernde Benutzerkonto.
	 * @return Das gespeicherte Benutzerkonto.
	 */
	public User saveUser(User user) {
		logger.info("User gespeichert");
		return userDAO.save(user);
	}

	/**
	 * Löscht das gegebene Benutzerkonto aus der Datenbank.
	 * 
	 * @param user Das zu löschende Benutzerkonto.
	 */
	public void deleteUser(User user) {
		userDAO.delete(user);
		logger.info("User gelöscht");
	}

	/**
	 * Löscht den angegebenen Fahrschüler aus der Datenbank.
	 * 
	 * @param id Die FahrschülerID
	 */
	public void deleteFahrschuelerById(Long id) {
		fahrschuelerDAO.deleteById(id);
		logger.info("Fahrschueler gelöscht");
	}

	/**
	 * Löscht den angegebenen Fahrschüler aus der Datenbank.
	 * 
	 * @param id Die FahrlehrerID
	 */
	public void deleteFahrlehrerById(Long id) {
		fahrlehrerDAO.deleteById(id);
		logger.info("Fahrlehrer gelöscht");
	}
}
