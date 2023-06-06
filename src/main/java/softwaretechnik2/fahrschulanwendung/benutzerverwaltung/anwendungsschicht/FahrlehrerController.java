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
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.Fahrlehrer;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.FahrlehrerDAO;
import softwaretechnik2.fahrschulanwendung.benutzer.datenschicht.User;
import softwaretechnik2.fahrschulanwendung.session.anwendungsschicht.SessionInterceptor;

/**
 * Controller zur Verwaltung der Fahrlehrer Klassen und HTTP Anfragen
 * @author seymen
 *
 */
@Controller
public class FahrlehrerController {

	private final FahrlehrerDAO fahrlehrerDAO;
	private final SessionInterceptor sessionInterceptor;

	@Autowired
	public FahrlehrerController(FahrlehrerDAO repository, SessionInterceptor sessionInterceptor) {
		this.fahrlehrerDAO = repository;
		this.sessionInterceptor = sessionInterceptor;
	}

	/**
	 * Zeigt die Fahrlehrersuche, wenn der Benutzer ein Fahrlehrer ist, ansonsten leitet er zu "terminverwaltung-schueler" um.
	 * @param session Aktuelle Benutzersitzung.
	 * @param request HTTP-Anfrage.
	 * @return Gibt den Namen der entsprechenden View zurück.
	 */
	@GetMapping("/suche-fahrlehrer")
	public String searchFahrlehrer(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "benutzerverwaltung/sucheFahrlehrer";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	/**
	 * Durchsucht die Fahrlehrer-Datenbank anhand der übergebenen Vor- und Nachnamen.
	 * @param vorname Fahrlehrer Vorname.
	 * @param nachname Fahrlehrer Nachname.
	 * @return Liste von FahrlehrerDTOs, die den Suchkriterien entsprechen.
	 */
	@GetMapping("/suche-api-fahrlehrer")
	@ResponseBody
	public List<FahrlehrerDTO> searchApi(
			@RequestParam(value = "vorname", required = false, defaultValue = "") String vorname,
			@RequestParam(value = "nachname", required = false, defaultValue = "") String nachname) {
		if (vorname.isEmpty() && nachname.isEmpty()) {
			return new ArrayList<>();
		}
		List<Fahrlehrer> fahrlehrerList = fahrlehrerDAO.findBySearchTerm(vorname, nachname);
		return fahrlehrerList.stream().map(fahrlehrer -> modelMapper.map(fahrlehrer, FahrlehrerDTO.class))
				.collect(Collectors.toList());
	}

	@Autowired
	private UserService benutzerkontoService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Erstellt einen neuen Fahrlehrer und ein zugehöriges Benutzerkonto.
	 * @param userCreationDTO Übermittelt die Benutzerinformationen.
	 * @return ResponseEntity mit UserCreationResponseDTO, das Informationen über den erstellten Benutzer enthält.
	 */
	@PostMapping("/create-fahrlehrer")
	public ResponseEntity<UserCreationResponseDTO> createFahrlehrer(@RequestBody UserCreationDTO userCreationDTO) {
		Fahrlehrer fahrlehrer = modelMapper.map(userCreationDTO, Fahrlehrer.class);
		Fahrlehrer savedFahrlehrer = fahrlehrerDAO.save(fahrlehrer);

		UserDTO userDTO = benutzerkontoService.createBenutzerkonto(fahrlehrer.getVorname(), fahrlehrer.getNachname());
		User benutzerkonto = benutzerkontoService.convertToEntity(userDTO);

		benutzerkonto.setFlID(savedFahrlehrer.getId());
		benutzerkontoService.save(benutzerkonto);

		UserCreationResponseDTO responseDTO = new UserCreationResponseDTO();
		responseDTO.setId(savedFahrlehrer.getId());
		responseDTO.setVorname(fahrlehrer.getVorname());
		responseDTO.setNachname(fahrlehrer.getNachname());
		responseDTO.setRole("Fahrlehrer");
		return ResponseEntity.ok(responseDTO);
	}

	/**
	 * Gibt alle Fahrlehrer in der Datenbank zurück.
	 * @return Liste von FahrlehrerDTOs.
	 */
	@GetMapping("/get-alleFahrlehrer")
	@ResponseBody
	public List<FahrlehrerDTO> getAllFahrlehrer() {
		List<Fahrlehrer> fahrlehrerList = fahrlehrerDAO.findAll();
		List<FahrlehrerDTO> fahrlehrerDTOList = fahrlehrerList.stream().map(fahrlehrer -> {
			FahrlehrerDTO dto = new FahrlehrerDTO();
			dto.setId(fahrlehrer.getId());
			dto.setVorname(fahrlehrer.getVorname());
			dto.setNachname(fahrlehrer.getNachname());
			dto.setGeburtsdatum(fahrlehrer.getGeburtsdatum());

			Adresse adresse = fahrlehrer.getAdresse();
			dto.setAdresse(adresse);

			return dto;
		}).collect(Collectors.toList());

		return fahrlehrerDTOList;
	}
}