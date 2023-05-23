/**
 * Passwort-Paket.
 */
package softwaretechnik2.fahrschulanwendung.passwort.anwendungsschicht;

// Import-Anweisungen
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserService;

/**
 * Definiert die PasswortController-Klasse als RestController, die für die
 * Verarbeitung von Passwortänderungsanfragen zuständig ist.
 */
@RestController
public class PasswortController {

	// Autowire die UserService-Komponente
	@Autowired
	private UserService userService;

	/**
	 * Definiert die changePassword()-Methode, die auf Anfragen an die
	 * "/change-password" URL reagiert.
	 *
	 * @param request Ein PasswortChangeRequest-Objekt, das die erforderlichen
	 *                Informationen zur Änderung des Passworts enthält.
	 * @return true, wenn die Passwortänderung erfolgreich war, false sonst.
	 */
	@PostMapping("/change-password")
	public boolean changePassword(@RequestBody PasswortChangeRequest request) {
		// Ändere das Passwort mithilfe der userService-Komponente
		return userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
	}
}