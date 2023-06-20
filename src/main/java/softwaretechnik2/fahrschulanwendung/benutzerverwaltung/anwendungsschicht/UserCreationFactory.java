package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Die Factory Klasse zur Implementierung des Factory Musters.
 * @author seymen
 *
 */
public class UserCreationFactory {
	@Autowired
	private FahrlehrerController fahrlehrerController;
	
	@Autowired
	private FahrschuelerController fahrschuelerController;

	private final Logger logger = LoggerFactory.getLogger(UserCreationFactory.class);
	
	/**
	 * Erstellt ein neues Benutzerobjekt auf Basis des übergebenen UserCreationDTO.
	 * @param userCreationDTO Übermittelt die Benutzerinformationen.
	 * @return Gibt ein UserCreationResponseDTO zurück, das die erstellten Benutzerinformationen enthält.
	 */
	public UserCreationResponseDTO createUser(UserCreationDTO userCreationDTO) {
	    if (userCreationDTO.getRole().equals("Fahrschueler")) {
		logger.info("Fahrschueler erstellt");
	        return fahrschuelerController.createFahrschueler(userCreationDTO).getBody();
	    } else if (userCreationDTO.getRole().equals("Fahrlehrer")) {
		logger.info("Fahrlehrer erstellt");
	        return fahrlehrerController.createFahrlehrer(userCreationDTO).getBody();
	    } else {
		logger.error("User konnte nicht erstellt werden (falsche Rolle)");
	        throw new IllegalArgumentException("Invalid role: " + userCreationDTO.getRole());
	    }
	}
}
