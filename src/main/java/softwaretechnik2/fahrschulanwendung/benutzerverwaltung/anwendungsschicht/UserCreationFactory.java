package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

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
	
	/**
	 * Erstellt ein neues Benutzerobjekt auf Basis des übergebenen UserCreationDTO.
	 * @param userCreationDTO Übermittelt die Benutzerinformationen.
	 * @return Gibt ein UserCreationResponseDTO zurück, das die erstellten Benutzerinformationen enthält.
	 */
	public UserCreationResponseDTO createUser(UserCreationDTO userCreationDTO) {
	    if (userCreationDTO.getRole().equals("Fahrschueler")) {
	        return fahrschuelerController.createFahrschueler(userCreationDTO).getBody();
	    } else if (userCreationDTO.getRole().equals("Fahrlehrer")) {
	        return fahrlehrerController.createFahrlehrer(userCreationDTO).getBody();
	    } else {
	        throw new IllegalArgumentException("Invalid role: " + userCreationDTO.getRole());
	    }
	}
}
