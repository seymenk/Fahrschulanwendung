package softwaretechnik2.fahrschulanwendung.login.anwendungsschicht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserDTO;
import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserService;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.User;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;

/**
 * 
 * Controller-Klasse für den Login.
 * @author seymen
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	private final SessionInterceptor sessionInterceptor;

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	public LoginController(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}

	/**
	 * Rendert die Login-Seite.
	 * @param model das Modell, welches an die Ansicht übergeben wird.
	 * @return die Name der Login-Seite.
	 */
	@GetMapping("/login")
	public String login(Model model) {
	    model.addAttribute("user", new UserDTO());
	    logger.info("Loginseite aufgerufen.");
	    return "login/index";
	}

	/**
	 * Authentifiziert den Benutzer.
	 * @param userDTO Benutzerdaten-Objekt.
	 * @param model das Modell, welches an die Ansicht übergeben wird.
	 * @param session aktuelle Session.
	 * @return Umleitung zur entsprechenden Seite basierend auf Benutzerrolle.
	 */
	@PostMapping("/login")
	public String authenticate(UserDTO userDTO, Model model, HttpSession session) {
	    User authenticatedUser = userService.authenticate(userDTO.getUsername(), userDTO.getPassword());
	    if (authenticatedUser != null) {
	        session.setAttribute("userId", authenticatedUser.getId().intValue());
	        if (authenticatedUser.getFsID() != null) {
	            session.setAttribute("userRole", "fahrschueler");
	            session.setAttribute("fahrschuelerID", authenticatedUser.getFsID());
	            logger.info("Fahrschüler angemeldet.");
	            return "redirect:/terminverwaltung-schueler";
	        } else if (authenticatedUser.getFlID() != null) {
	            session.setAttribute("userRole", "fahrlehrer");
	            session.setAttribute("fahrlehrerID", authenticatedUser.getFlID());
	            logger.info("Fahrlehrer angemeldet.");
	            return "redirect:/startseite";
	        } else {
	            model.addAttribute("error", "Keine gültige Rolle gefunden");
	            logger.error("Keine gültige Rolle gefunden");
	            return "login/index";
	        }
	    } else {
	        model.addAttribute("error", "Falscher Benutzername oder Passwort");
	        logger.error("Falscher Benutzername oder Passwort.");
	        return "login/index";
	    }
	}

	/**
	 * Zeigt die Startseite an, wenn der Benutzer als Fahrlehrer authentifiziert ist.
	 * @param session aktuelle Session.
	 * @param request HTTP-Anfrage.
	 * @return Umleitung zur entsprechenden Seite basierend auf Benutzerrolle.
	 */
	@GetMapping("/startseite")
	public String startseite(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "startseite/startseite";
		else
			return "redirect:/terminverwaltung-schueler";
	}
}