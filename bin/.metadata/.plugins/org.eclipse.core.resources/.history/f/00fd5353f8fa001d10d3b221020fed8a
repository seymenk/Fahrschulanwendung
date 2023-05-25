package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FahrschuelerDAO extends JpaRepository<Fahrschueler, Long> {
	@Query("SELECT f FROM Fahrschueler f WHERE f.vorname LIKE CONCAT(:vorname, '%') AND f.nachname LIKE CONCAT(:nachname, '%')")
	List<Fahrschueler> findBySearchTerm(@Param("vorname") String vorname, @Param("nachname") String nachname);
}