package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserDTO;
import softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht.UserService;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.Fahrschueler;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.FahrschuelerDAO;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.User;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller zur Verwaltung der Fahrschueler Klassen und HTTP Anfragen
 * @author seymen
 *
 */
@Controller
public class FahrschuelerController {
	private final FahrschuelerDAO fahrschuelerDAO;
	private final SessionInterceptor sessionInterceptor;

	private final Logger logger = LoggerFactory.getLogger(FahrlehrerController.class);

	@Autowired
	public FahrschuelerController(FahrschuelerDAO repository, SessionInterceptor sessionInterceptor) {
		this.fahrschuelerDAO = repository;
		this.sessionInterceptor = sessionInterceptor;
	}
	
	/**
	 * Zeigt die Fahrschülersuche, wenn der Benutzer ein Fahrlehrer ist, ansonsten leitet er zu "terminverwaltung-schueler" um.
	 * @param session Aktuelle Benutzersitzung.
	 * @param request HTTP-Anfrage.
	 * @return Gibt den Namen der entsprechenden View zurück.
	 */
	@GetMapping("/suche-fahrschueler")
	public String searchFahrschueler(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer")) {
			logger.info("An /suche-fahrschueler weitergeleitet.");
			return "benutzerverwaltung/sucheFahrschueler";
		}
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Durchsucht die Fahrschüler-Datenbank anhand der übergebenen Vor- und Nachnamen.
	 * @param vorname Fahrschüler Vorname.
	 * @param nachname Fahrschüler Nachname.
	 * @return Liste von FahrschülerDTOs, die den Suchkriterien entsprechen.
	 */
	@GetMapping("/suche-api-fahrschueler")
	@ResponseBody
	public List<FahrschuelerDTO> searchApi(
			@RequestParam(value = "vorname", required = false, defaultValue = "") String vorname,
			@RequestParam(value = "nachname", required = false, defaultValue = "") String nachname) {
		if (vorname.isEmpty() && nachname.isEmpty()) {
			return new ArrayList<>();
		}
		List<Fahrschueler> fahrschuelerList = fahrschuelerDAO.findBySearchTerm(vorname, nachname);
		return fahrschuelerList.stream().map(fahrschueler -> modelMapper.map(fahrschueler, FahrschuelerDTO.class))
				.collect(Collectors.toList());
	}

	@Autowired
	private UserService benutzerkontoService;

	/**
	 * Erstellt einen neuen Fahrschüler und ein zugehöriges Benutzerkonto.
	 * @param userCreationDTO Übermittelt die Benutzerinformationen.
	 * @return ResponseEntity mit UserCreationResponseDTO, das Informationen über den erstellten Benutzer enthält.
	 */
	@PostMapping("/create-fahrschueler")
	public ResponseEntity<UserCreationResponseDTO> createFahrschueler(@RequestBody UserCreationDTO userCreationDTO) {
	    Fahrschueler fahrschueler = modelMapper.map(userCreationDTO, Fahrschueler.class);
	    Fahrschueler savedFahrschueler = fahrschuelerDAO.save(fahrschueler);

	    UserDTO userDTO = benutzerkontoService.createBenutzerkonto(fahrschueler.getVorname(), fahrschueler.getNachname());
	    User benutzerkonto = benutzerkontoService.convertToEntity(userDTO);

	    benutzerkonto.setFsID(savedFahrschueler.getId());
	    benutzerkontoService.save(benutzerkonto);

	    UserCreationResponseDTO responseDTO = new UserCreationResponseDTO();
		responseDTO.setId(savedFahrschueler.getId());
		responseDTO.setVorname(fahrschueler.getVorname());
		responseDTO.setNachname(fahrschueler.getNachname());
		responseDTO.setRole("Fahrschüler");
		logger.info("Fahrschüler erstellt");
		return ResponseEntity.ok(responseDTO);
	}

}