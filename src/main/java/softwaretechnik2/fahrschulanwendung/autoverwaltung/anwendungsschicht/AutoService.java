package softwaretechnik2.fahrschulanwendung.autoverwaltung.anwendungsschicht;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.Auto;
import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.AutoDAO;
import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.Automatik;
import softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht.Manuell;

@Service
public class AutoService {

	@Autowired
	private AutoDAO autoDAO;

	public AutoDTO convertToAutoDTO(Auto auto) {
		AutoDTO autoDTO;
		if (auto instanceof Automatik) {
			autoDTO = new AutomatikDTO();
		} else {
			autoDTO = new ManuellDTO();
		}
		autoDTO.setId(auto.getId());
		autoDTO.setMarke(auto.getMarke());
		autoDTO.setModell(auto.getModell());
		autoDTO.setBaujahr(auto.getBaujahr());
		autoDTO.setFarbe(auto.getFarbe());
		autoDTO.setPreis(auto.getPreis());
		autoDTO.setGetriebe(auto.getGetriebe());
		return autoDTO;
	}

	public Auto convertToAuto(AutoDTO autoDTO) {
		Auto auto;
		if (autoDTO instanceof AutomatikDTO) {
			auto = new Automatik();
		} else {
			auto = new Manuell();
		}
		auto.setId(autoDTO.getId());
		auto.setMarke(autoDTO.getMarke());
		auto.setModell(autoDTO.getModell());
		auto.setBaujahr(autoDTO.getBaujahr());
		auto.setFarbe(autoDTO.getFarbe());
		auto.setPreis(autoDTO.getPreis());
		auto.setGetriebe(autoDTO.getGetriebe());
		return auto;
	}

	public AutoDTO createAuto(AutoDTO autoDTO) {
	    Auto auto = convertToAuto(autoDTO);
	    Auto savedAuto = autoDAO.save(auto);
	    return convertToAutoDTO(savedAuto);
	}

	public AutoDTO updateAuto(AutoDTO autoDTO) {
		Optional<Auto> existingAuto = autoDAO.findById(autoDTO.getId());
		if (existingAuto.isPresent()) {
			Auto updatedAuto = existingAuto.get();

			if (autoDTO.getMarke() != null && !autoDTO.getMarke().isEmpty()) {
				updatedAuto.setMarke(autoDTO.getMarke());
			}
			if (autoDTO.getModell() != null && !autoDTO.getModell().isEmpty()) {
				updatedAuto.setModell(autoDTO.getModell());
			}
			if (autoDTO.getBaujahr() != 0) {
				updatedAuto.setBaujahr(autoDTO.getBaujahr());
			}
			if (autoDTO.getFarbe() != null && !autoDTO.getFarbe().isEmpty()) {
				updatedAuto.setFarbe(autoDTO.getFarbe());
			}
			if (autoDTO.getPreis() != 0) {
				updatedAuto.setPreis(autoDTO.getPreis());
			}

			Auto savedAuto = autoDAO.save(updatedAuto);
			return convertToAutoDTO(savedAuto);
		} else {
			return null;
		}
	}

	public void deleteCar(Long id) {
		autoDAO.deleteById(id);
	}
}