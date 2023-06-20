package softwaretechnik2.fahrschulanwendung.autoverwaltung.anwendungsschicht;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.Auto;
import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.AutoDAO;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Controller zur Verwaltung der Auto Klassen und HTTP Anfragen
 *
 * @author seymen
 *
 */
@Controller
public class AutoController {

	@Autowired
	private AutoService autoService;

	private final SessionInterceptor sessionInterceptor;
	
	private final Logger logger = LoggerFactory.getLogger(AutoController.class);

	@Autowired
	public AutoController(AutoService autoService, SessionInterceptor sessionInterceptor) {
		this.autoService = autoService;
		this.sessionInterceptor = sessionInterceptor;
	}

	/**
	 * Bei einem GET-Request auf "/autoverwaltung" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/autoverwaltung")
	public String autoverwaltung(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /autoverwaltung weitergeleitet.");
			return "autoverwaltung/autoverwaltung";
		} else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/autoverwaltung-uebersicht" wird diese Methode
	 * aufgerufen. Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt
	 * dann die Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer"
	 * ist, erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/autoverwaltung-uebersicht")
	public String autoverwaltungUebersicht(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /autoverwaltung-uebersicht weitergeleitet.");
			return "autoverwaltung/autoverwaltung-uebersicht";
		} else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/autoverwaltung-hinzufuegen" wird diese Methode
	 * aufgerufen. Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt
	 * dann die Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer"
	 * ist, erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/autoverwaltung-hinzufuegen")
	public String autoverwaltungHinzufuegen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /autoverwaltung-hinzufuegen weitergeleitet.");
			return "autoverwaltung/autoverwaltung-hinzufuegen";
		} else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/autoverwaltung-loeschen" wird diese Methode
	 * aufgerufen. Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt
	 * dann die Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer"
	 * ist, erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/autoverwaltung-loeschen")
	public String autoverwaltungLoeschen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /autoverwaltung-loeschen weitergeleitet.");
			return "autoverwaltung/autoverwaltung-loeschen";
		} else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem GET-Request auf "/autoverwaltung-aendern" wird diese Methode
	 * aufgerufen. Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt
	 * dann die Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer"
	 * ist, erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/autoverwaltung-aendern")
	public String autoverwaltungAendern(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /autoverwaltung-aendern weitergeleitet.");
			return "autoverwaltung/autoverwaltung-aendern";
		} else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Bei einem POST-Request auf "/create-auto" wird diese Methode aufgerufen. Sie
	 * erstellt ein neues Auto basierend auf den Daten im Body des Requests und gibt
	 * das erstellte Auto zurück.
	 *
	 * @param autoDTO Die Daten des zu erstellenden Autos.
	 * @return Ein ResponseEntity-Objekt mit dem erstellten Auto und dem
	 *         HTTP-Statuscode.
	 */
	@PostMapping("/create-auto")
	public ResponseEntity<AutoDTO> createAuto(@RequestBody AutoDTO autoDTO) {
		try {
			logger.info("An /create-auto weitergeleitet.");
			AutoDTO createdAuto = autoService.createAuto(autoDTO);
			logger.info("Auto erstellt");
			return new ResponseEntity<>(createdAuto, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Auto konnte nicht erstellt werden.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Bei einem GET-Request auf "/getCars" wird diese Methode aufgerufen. Sie gibt
	 * eine Liste aller Autos zurück.
	 *
	 * @return Ein ResponseEntity-Objekt mit der Liste der Autos und dem
	 *         HTTP-Statuscode.
	 */
	@PostMapping("/change-auto")
	public ResponseEntity<AutoDTO> updateAuto(@RequestBody AutoDTO autoDTO) {
		try {
			logger.info("An /change-auto weitergeleitet.");
			AutoDTO updatedAuto = autoService.updateAuto(autoDTO);
			if (updatedAuto != null) {
				logger.info("Auto geändert.");
				return new ResponseEntity<>(updatedAuto, HttpStatus.OK);
			} else {
				logger.error("Auto konnte nicht geändert werden");
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Auto konnte nicht geändert werden");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	AutoDAO autoRepository;

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get-car")
	public ResponseEntity<AutoDTO> getCar(@RequestParam("id") Long id) {
		Optional<Auto> autoData = autoRepository.findById(id);

		if (autoData.isPresent()) {
			logger.info("An /get-car weitergeleitet.");
			AutoDTO autoDTO = autoService.convertToAutoDTO(autoData.get());
			logger.info("Autos erhalten");
			return new ResponseEntity<>(autoDTO, HttpStatus.OK);
		} else {
			logger.error("Autos konnten nicht aufgerufen werden.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id) {
		Optional<Auto> existingAuto = autoRepository.findById(id);
		if (existingAuto.isPresent()) {
			logger.info("An /cars/{id} weitergeleitet.");
			autoService.deleteCar(id);
			logger.info("Auto gelöscht.");
			return ResponseEntity.ok().build();
		} else {
			logger.error("Auto konnte nicht gelöscht werden.");
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * @return
	 */
	@GetMapping("/getCars")
	public ResponseEntity<List<AutoDTO>> getCars() {
		try {
			logger.info("An /getCars weitergeleitet.");
			List<Auto> autos = autoRepository.findAll();
			List<AutoDTO> autoDTOs = autos.stream().map(autoService::convertToAutoDTO).collect(Collectors.toList());
			logger.info("Autos erhalten.");
			return new ResponseEntity<>(autoDTOs, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Autos konnten nicht aufgerufen werden.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}