package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import org.springframework.beans.factory.annotation.Autowired;

public class UserCreationFactory {
	@Autowired
	private FahrlehrerController fahrlehrerController;
	
	@Autowired
	private FahrschuelerController fahrschuelerController;
	
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
