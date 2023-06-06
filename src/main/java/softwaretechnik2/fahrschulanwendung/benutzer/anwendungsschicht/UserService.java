package softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.BenutzerverwaltungFassade;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.User;
import softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht.PasswortGenerator;

/**
 * Dient als Serviceschicht, welche die Anforderungen der Präsentationsschicht behandelt und entsprechende
 * Datenbankoperationen ausführt.
 * @author seymen
 */
@Service
public class UserService {

	@Autowired
	BenutzerverwaltungFassade benutzerverwaltungFassade;
	 
	private ModelMapper modelMapper;

	private PasswortGenerator pwg;

	public UserService() {
		this.pwg = new PasswortGenerator();
		this.modelMapper = new ModelMapper();
	}
	
	/**
	 * Konvertiert ein User-Objekt in ein UserDTO-Objekt.
	 * @param user Das User-Objekt, das konvertiert werden soll.
	 * @return Das konvertierte UserDTO-Objekt.
	 */
	private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
	
	/**
	 * Konvertiert ein UserDTO-Objekt in ein User-Objekt.
	 * @param userDTO Das UserDTO-Objekt, das konvertiert werden soll.
	 * @return Das konvertierte User-Objekt.
	 */
	public User convertToEntity(UserDTO userDTO) {
	    return modelMapper.map(userDTO, User.class);
	}

	/**
	 * Authentifiziert einen Benutzer anhand von Benutzername und Passwort.
	 * @param username Der Benutzername des Benutzers.
	 * @param password Das Passwort des Benutzers.
	 * @return Das User-Objekt, wenn die Authentifizierung erfolgreich war, oder null, wenn sie fehlschlug.
	 */
	public User authenticate(String username, String password) {
		return benutzerverwaltungFassade.findByUsernameAndPassword(username, password);
	}

	/**
	 * Erstellt ein neues Benutzerkonto mit einem einzigartigen Benutzernamen und einem zufällig generierten Passwort.
	 * @param vorname Der Vorname des Benutzers.
	 * @param nachname Der Nachname des Benutzers.
	 * @return Das erstellte UserDTO-Objekt.
	 */
	public UserDTO createBenutzerkonto(String vorname, String nachname) {
		User benutzerkonto = new User();
		String username = vorname.substring(0, 1).toLowerCase() + nachname.toLowerCase();
		String uniqueUsername = getUniqueUsername(username);
		benutzerkonto.setUsername(uniqueUsername);
		benutzerkonto.setPassword(pwg.generateRandomPassword());
		return convertToDTO(benutzerverwaltungFassade.saveUser(benutzerkonto));
	}

	/**
	 * Generiert einen einzigartigen Benutzernamen auf Basis des gegebenen Benutzernamens durch Anhängen einer Zahlenfolge.
	 * @param username Der Basis-Benutzername.
	 * @return Der einzigartige Benutzername.
	 */
	private String getUniqueUsername(String username) {
        String newUsername = username;
        int num = 1;
        while (benutzerverwaltungFassade.existsByUsername(newUsername)) {
            newUsername = username + String.format("%03d", num);
            num++;
        }
        return newUsername;
    }

	/**
	 * Speichert ein Benutzerkonto in der Datenbank und gibt das gespeicherte Benutzerkonto zurück.
	 * @param benutzerkonto Das zu speichernde Benutzerkonto.
	 * @return Das gespeicherte UserDTO-Objekt.
	 */
    public UserDTO save(User benutzerkonto) {
        return convertToDTO(benutzerverwaltungFassade.saveUser(benutzerkonto));
    }

    /**
     * Löscht ein Benutzerkonto anhand des Benutzernamens. Falls der Benutzer ein Fahrschüler oder Fahrlehrer ist, wird das zugehörige Konto ebenfalls gelöscht.
     * @param benutzername Der Benutzername des zu löschenden Benutzerkontos.
     */
    @Transactional
    public void deleteBenutzerByBenutzername(String benutzername) {
        Optional<User> benutzer = benutzerverwaltungFassade.findByUsername(benutzername);
        if (benutzer.isPresent()) {
            User user = benutzer.get();

            if (user.getFsID() != null) {
                benutzerverwaltungFassade.deleteFahrschuelerById(user.getFsID());
            } else if (user.getFlID() != null) {
                benutzerverwaltungFassade.deleteFahrlehrerById(user.getFlID());
            }

            benutzerverwaltungFassade.deleteUser(user);
        }
    }

    /**
     * Ändert das Passwort eines Benutzers, wenn das alte Passwort korrekt ist.
     * @param username Der Benutzername des Benutzers, dessen Passwort geändert werden soll.
     * @param oldPassword Das alte Passwort.
     * @param newPassword Das neue Passwort.
     * @return true, wenn die Passwortänderung erfolgreich war, sonst false.
     */
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = benutzerverwaltungFassade.findByUsernameAndPassword(username, oldPassword);
        if (user != null) {
            user.setPassword(newPassword);
            benutzerverwaltungFassade.saveUser(user);
            return true;
        }
        return false;
    }
}