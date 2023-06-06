package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Das FahrlehrerDAO Interface implementiert alle Datenbankabfragen, dank der erbenden Klasse JpaRepository.
 * Sozusagen das Repository für die Fahrlehrerverwaltung.
 * @author seymen
 *
 */
public interface FahrlehrerDAO extends JpaRepository<Fahrlehrer, Long> {
	/**
	 * Sucht anhand von Vorname und Nachname nach dem Fahrlehrer
	 * @param vorname
	 * @param nachname
	 * @return eine Liste an gefundenen Fahrlehrer Instanzen.
	 */
	@Query("SELECT f FROM Fahrlehrer f WHERE f.vorname LIKE CONCAT(:vorname, '%') AND f.nachname LIKE CONCAT(:nachname, '%')")
	List<Fahrlehrer> findBySearchTerm(@Param("vorname") String vorname, @Param("nachname") String nachname);
}