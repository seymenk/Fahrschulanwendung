package softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-Schnittstelle für den Zugriff auf die Termin-Datenbank.
 * @author seymen
 */
@Repository
public interface TerminDAO extends JpaRepository<Termin, Long> {

	/**
	 * Sucht Termine, die mit einer bestimmten Fahrlehrer-ID verknüpft sind.
	 * @param fahrlehrerID ID des Fahrlehrers.
	 * @return Liste der gefundenen Termine.
	 */
	List<Termin> findByFahrlehrerID(int fahrlehrerID);

	/**
	 * Sucht verfügbare Termine (nicht gebucht) für einen bestimmten Fahrlehrer an einem bestimmten Datum.
	 * @param fahrlehrerID ID des Fahrlehrers.
	 * @param datum Das gewünschte Datum.
	 * @return Liste der verfügbaren Termine.
	 */
	List<Termin> findByFahrlehrerIDAndDatumAndGebuchtFalse(Long fahrlehrerID, LocalDate datum);

	/**
	 * Findet einen Termin anhand seiner ID.
	 * @param terminID Die ID des Termins.
	 * @return Der gefundene Termin.
	 */
	Termin findById(int terminID);
}
