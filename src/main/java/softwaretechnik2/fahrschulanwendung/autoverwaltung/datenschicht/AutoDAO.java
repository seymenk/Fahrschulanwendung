package softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Das AutoDAO Interface implementiert alle Datenbankabfragen, dank der erbenden Klasse JpaRepository.
 * Sozusagen das Repository für die Autoverwaltung.
 * @author seymen
 *
 */
@Repository
public interface AutoDAO extends JpaRepository<Auto, Long> {
}
