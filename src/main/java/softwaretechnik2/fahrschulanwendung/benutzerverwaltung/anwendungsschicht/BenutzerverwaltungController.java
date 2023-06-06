package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;

/**
 * Definiert die BenutzerverwaltungController-Klasse, die für die Verwaltung von
 * Benutzerkonten zuständig ist.
 * 
 * @author seymen
 *
 */
@Controller
public class BenutzerverwaltungController {

	private final SessionInterceptor sessionInterceptor;
	
	@Autowired
	public BenutzerverwaltungController(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}

	/**
	 * Bei einem GET-Request auf "/benutzerverwaltung" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/benutzerverwaltung")
	public String benutzerverwaltung(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "benutzerverwaltung/benutzerverwaltung";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/benutzerverwaltung-anlegen" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/benutzerverwaltung-anlegen")
	public String benutzerverwaltungAnlegen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "benutzerverwaltung/benutzerverwaltung-anlegen";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/benutzerverwaltung-loeschen" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/benutzerverwaltung-loeschen")
	public String benutzerverwaltungLoeschen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "benutzerverwaltung/benutzerverwaltung-loeschen";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/benutzerverwaltung-aendern" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/benutzerverwaltung-aendern")
	public String benutzerverwaltungAendern(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "benutzerverwaltung/benutzerverwaltung-aendern";
		else
			return "redirect:/terminverwaltung-schueler";
	}
}