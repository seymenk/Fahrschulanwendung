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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AutoController {

	@Autowired
	private AutoService autoService;

	private final SessionInterceptor sessionInterceptor;

	@Autowired
	public AutoController(AutoService autoService, SessionInterceptor sessionInterceptor) {
		this.autoService = autoService;
		this.sessionInterceptor = sessionInterceptor;
	}

	@GetMapping("/autoverwaltung")
	public String autoverwaltung(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "autoverwaltung/autoverwaltung";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@GetMapping("/autoverwaltung-uebersicht")
	public String autoverwaltungUebersicht(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "autoverwaltung/autoverwaltung-uebersicht";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@GetMapping("/autoverwaltung-hinzufuegen")
	public String autoverwaltungHinzufuegen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "autoverwaltung/autoverwaltung-hinzufuegen";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@GetMapping("/autoverwaltung-loeschen")
	public String autoverwaltungLoeschen(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "autoverwaltung/autoverwaltung-loeschen";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@GetMapping("/autoverwaltung-aendern")
	public String autoverwaltungAendern(HttpSession session, HttpServletRequest request) {
		if (sessionInterceptor.hasUserRole(request, "fahrlehrer"))
			return "autoverwaltung/autoverwaltung-aendern";
		else
			return "redirect:/terminverwaltung-schueler";
	}

	@PostMapping("/create-auto")
	public ResponseEntity<AutoDTO> createAuto(@RequestBody AutoDTO autoDTO) {
		try {
			AutoDTO createdAuto = autoService.createAuto(autoDTO);
			return new ResponseEntity<>(createdAuto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/change-auto")
	public ResponseEntity<AutoDTO> updateAuto(@RequestBody AutoDTO autoDTO) {
		try {
			AutoDTO updatedAuto = autoService.updateAuto(autoDTO);
			if (updatedAuto != null) {
				return new ResponseEntity<>(updatedAuto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	AutoDAO autoRepository;

	@GetMapping("/get-car")
	public ResponseEntity<AutoDTO> getCar(@RequestParam("id") Long id) {
		Optional<Auto> autoData = autoRepository.findById(id);

		if (autoData.isPresent()) {
			AutoDTO autoDTO = autoService.convertToAutoDTO(autoData.get());
			return new ResponseEntity<>(autoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id) {
		Optional<Auto> existingAuto = autoRepository.findById(id);
		if (existingAuto.isPresent()) {
			autoService.deleteCar(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getCars")
	public ResponseEntity<List<AutoDTO>> getCars() {
		try {
			List<Auto> autos = autoRepository.findAll();
			List<AutoDTO> autoDTOs = autos.stream().map(autoService::convertToAutoDTO).collect(Collectors.toList());
			return new ResponseEntity<>(autoDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}