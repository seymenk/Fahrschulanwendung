package softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht;

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

    /**
     * Löscht das Benutzerkonto aus der Datenbank, durch die Anfrage an die Service Klasse.
     * @param benutzername
     */
    @DeleteMapping("/benutzer/{benutzername}")
    public void deleteUser(@PathVariable String benutzername) {
        userService.deleteBenutzerByBenutzername(benutzername);
        System.out.println("Benutzerkonto gelöscht");
    }
}
