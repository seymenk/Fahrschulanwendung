package softwaretechnik2.fahrschulanwendung.terminverwaltung.anwendungsschicht;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.Termin;
import softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht.TerminDAO;

/**
 * Serviceklasse zur Verwaltung von Terminen.
 * @author seymen
 */
@Service
public class TerminService {

    private final TerminDAO terminRepository;
    
    private final Logger logger = LoggerFactory.getLogger(TerminService.class);


    @Autowired
    public TerminService(TerminDAO terminRepository) {
        this.terminRepository = terminRepository;
    }

    /**
     * Speichert eine Liste von Terminobjekten in der Datenbank.
     * @param terminObjekte Liste von Terminobjekten, die gespeichert werden sollen.
     * @param fahrlehrerId ID des Fahrlehrers, der die Termine festlegt.
     */
    public void saveTerminSlots(List<Termin> terminObjekte, int fahrlehrerId) {
        List<Termin> terminSlots = terminObjekte.stream().map(termin -> {
            Termin newTermin = new Termin();
            newTermin.setFahrschuelerID(0);
            newTermin.setFahrlehrerID(fahrlehrerId);
            newTermin.setDatum(termin.getDatum());
            
            newTermin.setStartUhrzeit(termin.getStartUhrzeit());
            newTermin.setEndUhrzeit(termin.createEndUhrzeit(termin.getStartUhrzeit()));
            newTermin.setGebucht(false);
            logger.info("Termine wurden in der Datenbank gespeichert.");
            return newTermin;
        }).collect(Collectors.toList());

        terminRepository.saveAll(terminSlots);
    }
    
    /**
     * Löscht einen Termin anhand seiner ID.
     * @param id Die ID des Termins, der gelöscht werden soll.
     */
    public void deleteTermin(Long id) {
    	logger.info("Termin wurde gelöscht");
    	terminRepository.deleteById(id);
    }
    
    /**
     * Gibt eine Liste von verfügbaren Terminen für einen bestimmten Fahrlehrer und ein bestimmtes Datum zurück.
     * @param fahrlehrerID ID des Fahrlehrers.
     * @param datum Das Datum, für das die verfügbaren Termine abgerufen werden sollen.
     * @return Liste von verfügbaren Terminen.
     */
    public List<Termin> getAvailableTimeSlots(Long fahrlehrerID, LocalDate datum) {
    	logger.info("Verfügbare Timeslots wurden aufgerufen.");
        return terminRepository.findByFahrlehrerIDAndDatumAndGebuchtFalse(fahrlehrerID, datum);
    }
}
