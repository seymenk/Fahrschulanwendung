package softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("/benutzer/{benutzername}")
    public void deleteUser(@PathVariable String benutzername) {
        userService.deleteBenutzerByBenutzername(benutzername);
    }
}
