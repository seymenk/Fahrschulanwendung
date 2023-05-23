/**

Paket für die Login-Funktionalität.
*/
package softwaretechnik2.fahrschulanwendung.login.anwendungsschicht;

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
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	private final SessionInterceptor sessionInterceptor;

	// Konstruktorinjektion
	@Autowired
	public LoginController(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}

	@GetMapping("/login")
	public String login(Model model) {
	    model.addAttribute("user", new UserDTO());
	    return "login/index";
	}

	@PostMapping("/login")
	public String authenticate(UserDTO userDTO, Model model, HttpSession session) {
	    User authenticatedUser = userService.authenticate(userDTO.getUsername(), userDTO.getPassword());
	    if (authenticatedUser != null) {
	        session.setAttribute("userId", authenticatedUser.getId().intValue());
	        if (authenticatedUser.getFsID() != null) {
	            session.setAttribute("userRole", "fahrschueler");
	            session.setAttribute("fahrschuelerID", authenticatedUser.getFsID());
	            return "redirect:/terminverwaltung-schueler";
	        } else if (authenticatedUser.getFlID() != null) {
	            session.setAttribute("userRole", "fahrlehrer");
	            session.setAttribute("fahrlehrerID", authenticatedUser.getFlID());
	            return "redirect:/startseite";
	        } else {
	            model.addAttribute("error", "Keine gültige Rolle gefunden");
	            return "login/index";
	        }
	    } else {
	        model.addAttribute("error", "Falscher Benutzername oder Passwort");
	        return "login/index";
	    }
	}

	@GetMapping("/startseite")
	public String startseite(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "startseite/startseite";
		else
			return "redirect:/terminverwaltung-schueler";
	}
}