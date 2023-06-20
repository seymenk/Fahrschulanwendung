package softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Die Controller Klasse zur Verwaltung der Benutzerkonten.
 * @author seymen
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;
    
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Löscht das Benutzerkonto aus der Datenbank, durch die Anfrage an die Service Klasse.
     * @param benutzername
     */
    @DeleteMapping("/benutzer/{benutzername}")
    public void deleteUser(@PathVariable String benutzername) {
        userService.deleteBenutzerByBenutzername(benutzername);
        logger.info("Benutzer gelöscht.");
    }
}
