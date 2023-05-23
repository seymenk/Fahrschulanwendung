package softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminDAO extends JpaRepository<Termin, Long> {
	List<Termin> findByFahrlehrerID(int fahrlehrerID);
	List<Termin> findByFahrlehrerIDAndDatumAndGebuchtFalse(Long fahrlehrerID, LocalDate datum);
	Termin findById(int terminID);
}
