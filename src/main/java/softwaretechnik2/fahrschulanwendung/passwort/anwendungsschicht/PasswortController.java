package softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserService;

/**
 * Definiert die PasswortController-Klasse als RestController, die für die
 * Verarbeitung von Passwortänderungsanfragen zuständig ist.
 * @author seymen
 */
@RestController
public class PasswortController {

	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(PasswortController.class);

	/**
	 * Definiert die changePassword()-Methode, die auf Anfragen an die
	 * "/change-password" URL reagiert.
	 *
	 * @param request Ein PasswortChangeRequest-Objekt, das die erforderlichen
	 *                Informationen zur Änderung des Passworts enthält.
	 * @return true, wenn die Passwortänderung erfolgreich war, sonst false.
	 */
	@PostMapping("/change-password")
	public boolean changePassword(@RequestBody PasswortChangeRequest request) {
		logger.info("Passwort wurde geändert.");
		return userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
	}
}