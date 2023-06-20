package softwaretechnik2.fahrschulanwendung.terminverwaltung.anwendungsschicht;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;
import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.Termin;
import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.TerminDAO;

/**
 * Controller-Klasse zur Verwaltung von Terminen.
 * @author seymen
 */
@Controller
public class TerminverwaltungController {

	private final SessionInterceptor sessionInterceptor;
	private final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private HttpSession httpSession;
	
	private final Logger logger = LoggerFactory.getLogger(TerminverwaltungController.class);
	
	@Autowired
	public TerminverwaltungController(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung")
	public String terminverwaltung(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /terminverwaltung weitergeleitet.");
			return "terminverwaltung/terminverwaltung";
		}
		else
			return "redirect:/startseite";
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung-timeslot-erstellen" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung-timeslot-erstellen")
	public String terminverwaltungTimeslotErstellen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /terminverwaltung-timeslot-erstellen weitergeleitet.");
			return "terminverwaltung/terminverwaltung-timeslot-erstellen";
		}
		else
			return "redirect:/startseite";
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung-meine-termine" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung-meine-termine")
	public String terminverwaltungMeineTermine(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /terminverwaltung-meine-termine weitergeleitet.");
			return "terminverwaltung/terminverwaltung-meine-termine";
		}
		else
			return "redirect:/startseite";
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung-alle-termine" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung-alle-termine")
	public String terminverwaltungAlleTermine(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /terminverwaltung-alle-termine weitergeleitet.");
			return "terminverwaltung/terminverwaltung-alle-termine";
		}
		else
			return "redirect:/startseite";
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung-loeschen" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung-loeschen")
	public String terminverwaltungLoeschen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /terminverwaltung-loeschen weitergeleitet.");
			return "terminverwaltung/terminverwaltung-loeschen";
		}
		else
			return "redirect:/startseite";
	}

	/**
	 * Bei einem GET-Request auf "/terminverwaltung-schueler" wird diese Methode aufgerufen.
	 * Überprüft, ob der Benutzer die Rolle "Fahrlehrer" hat und gibt dann die
	 * Autoverwaltung-Seite zurück. Wenn der Benutzer nicht "Fahrlehrer" ist,
	 * erfolgt eine Umleitung zur Seite "terminverwaltung-schueler".
	 *
	 * @param session Die aktuelle Session.
	 * @param request Der HTTP-Request.
	 * @return Der Name der View.
	 */
	@GetMapping("/terminverwaltung-schueler")
	public String terminverwaltungSchueler(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrschueler")) {
			logger.info("An /terminverwaltung-schueler weitergeleitet.");
			return "terminverwaltung/terminverwaltung-schueler";
		}
		else
			return "redirect:/startseite";
	}

	private int getFahrlehrerID() {
		return ((Long) httpSession.getAttribute("fahrlehrerID")).intValue();
	}

	private int getFahrschuelerID() {
		return ((Long) httpSession.getAttribute("fahrschuelerID")).intValue();
	}

	@Autowired
	TerminService terminService;

	@Autowired
	TerminDAO terminDAO;

	/**
	 * Fügt eine Liste von Terminslots hinzu.
	 * @param terminDTOs Liste von Termin-DTOs.
	 * @return ResponseEntity mit Status und Nachricht.
	 */
	@PostMapping("/terminslots")
	public ResponseEntity<String> addTerminSlots(@RequestBody List<TerminDTO> terminDTOs) {
		List<Termin> terminObjekte = terminDTOs.stream().map(terminDTO -> modelMapper.map(terminDTO, Termin.class))
				.collect(Collectors.toList());
		terminService.saveTerminSlots(terminObjekte, getFahrlehrerID());
		logger.info("Terminslots erflogreich hinzugefügt.");
		return new ResponseEntity<>("Terminslots erfolgreich hinzugefügt", HttpStatus.CREATED);
	}

	/**
	 * Löscht einen Termin basierend auf seiner ID.
	 * @param id Die ID des Termins.
	 * @return ResponseEntity mit Status.
	 */
	@DeleteMapping("/termin/{id}")
	public ResponseEntity<?> deleteTermin(@PathVariable Long id) {
		Optional<Termin> existing = terminDAO.findById(id);
		if (existing.isPresent()) {
			terminService.deleteTermin(id);
			logger.info("Termin gelöscht.");
			return ResponseEntity.ok().build();
		} else {
			logger.error("Termin konnte nicht gelöscht werden.");
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Holt alle Termine für den aktuellen Fahrlehrer.
	 * @return ResponseEntity mit Liste von Termin-DTOs und Status.
	 */
	@GetMapping("/get-meine-termine")
	public ResponseEntity<List<TerminDTO>> getMeineTermine() {
		try {
			int fahrlehrerID = getFahrlehrerID();
			List<Termin> termine = terminDAO.findByFahrlehrerID(fahrlehrerID);
			List<TerminDTO> terminDTOs = termine.stream().map(termin -> modelMapper.map(termin, TerminDTO.class))
					.collect(Collectors.toList());
			logger.info("Alle Termine aufgerufen für den aktuellen Fahrlehrer.");
			return new ResponseEntity<>(terminDTOs, HttpStatus.OK);
		} catch (Exception e) {
			logger.warn("Konnte keine Termine finden.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Holt alle Termine.
	 * @return ResponseEntity mit Liste von Termin-DTOs und Status.
	 */
	@GetMapping("/get-alle-termine")
	public ResponseEntity<List<TerminDTO>> getAlleTermine() {
		try {
			List<Termin> termine = terminDAO.findAll();
			List<TerminDTO> terminDTOs = termine.stream().map(termin -> modelMapper.map(termin, TerminDTO.class))
					.collect(Collectors.toList());
			logger.info("Alle Termine aufgerufen.");
			return new ResponseEntity<>(terminDTOs, HttpStatus.OK);
		} catch (Exception e) {
			logger.warn("Konnte keine Termine finden.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Behandelt Anfragen mit ID und Datum.
	 * @param requestData Eine Map, die Fahrlehrer-ID und Datum enthält.
	 * @return ResponseEntity mit Liste von Termin-DTOs und Status.
	 */
	@PostMapping("/getIDandDate")
	public ResponseEntity<List<TerminDTO>> handleIDandDate(@RequestBody Map<String, Object> requestData) {
		Long id = Long.parseLong(requestData.get("fahrlehrerID").toString());
		LocalDate date = LocalDate.parse(requestData.get("date").toString());

		return getTimeslotsByIDAndDate(id, date);
	}

	/**
	 * @param id
	 * @param date
	 * @return
	 */
	private ResponseEntity<List<TerminDTO>> getTimeslotsByIDAndDate(Long id, LocalDate date) {
		List<Termin> termine = terminDAO.findByFahrlehrerIDAndDatumAndGebuchtFalse(id, date);
		List<TerminDTO> terminDTOs = termine.stream().map(termin -> modelMapper.map(termin, TerminDTO.class))
				.collect(Collectors.toList());
		logger.info("Alle Timeslots aufgerufen.");
		return new ResponseEntity<>(terminDTOs, HttpStatus.OK);
	}

	/**
	 * Bucht einen Termin.
	 * @param terminDTO Das Termin-DTO.
	 * @param session Die HttpSession.
	 * @return ResponseEntity mit Status und Map, die den Erfolg der Operation anzeigt.
	 */
	@PostMapping("/bookTermin")
	public ResponseEntity<?> bookTermin(@RequestBody TerminDTO terminDTO, HttpSession session) {
	    int terminID = terminDTO.getId();

	    try {
	        Termin termin = terminDAO.findById(terminID);
	        if (termin != null) {
	            termin.setGebucht(true);
	            termin.setFahrschuelerID(getFahrschuelerID());
	            terminDAO.save(termin);
	            logger.info("Termin erfolgreich gebucht.");
	            return ResponseEntity.ok().body(Map.of("success", true));
	        } else {
	        	logger.error("Termin konnte nicht gebucht werden.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false));
	        }
	    } catch (Exception e) {
	    	logger.error("Termin konnte nicht gebucht werden.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false));
	    }
	}
}