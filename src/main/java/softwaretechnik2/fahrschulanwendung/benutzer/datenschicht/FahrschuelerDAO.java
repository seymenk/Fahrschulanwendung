package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Das FahrschuelerDAO Interface implementiert alle Datenbankabfragen, dank der erbenden Klasse JpaRepository.
 * Sozusagen das Repository für die Fahrschülerverwaltung.
 * @author seymen
 *
 */
public interface FahrschuelerDAO extends JpaRepository<Fahrschueler, Long> {
	/**
	 * Sucht anhand von Vorname und Nachname nach dem Fahrschüler
	 * @param vorname
	 * @param nachname
	 * @return eine Liste an gefundenen Fahrschüler Instanzen.
	 */
	@Query("SELECT f FROM Fahrschueler f WHERE f.vorname LIKE CONCAT(:vorname, '%') AND f.nachname LIKE CONCAT(:nachname, '%')")
	List<Fahrschueler> findBySearchTerm(@Param("vorname") String vorname, @Param("nachname") String nachname);
}