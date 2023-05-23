package softwaretechnik2.fahrschulanwendung.terminverwaltung.anwendungsschicht;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.Termin;
import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.TerminDAO;

@Service
public class TerminService {

    private final TerminDAO terminRepository;

    @Autowired
    public TerminService(TerminDAO terminRepository) {
        this.terminRepository = terminRepository;
    }

    public void saveTerminSlots(List<Termin> terminObjekte, int fahrlehrerId) {
        List<Termin> terminSlots = terminObjekte.stream().map(termin -> {
            Termin newTermin = new Termin();
            newTermin.setFahrschuelerID(0);
            newTermin.setFahrlehrerID(fahrlehrerId);
            newTermin.setDatum(termin.getDatum());
            
            newTermin.setStartUhrzeit(termin.getStartUhrzeit());
            newTermin.setEndUhrzeit(termin.createEndUhrzeit(termin.getStartUhrzeit()));
            newTermin.setGebucht(false);

            return newTermin;
        }).collect(Collectors.toList());

        terminRepository.saveAll(terminSlots);
    }
    
    public void deleteTermin(Long id) {
    	terminRepository.deleteById(id);
    }
    
    public List<Termin> getAvailableTimeSlots(Long fahrlehrerID, LocalDate datum) {
        return terminRepository.findByFahrlehrerIDAndDatumAndGebuchtFalse(fahrlehrerID, datum);
    }
}
