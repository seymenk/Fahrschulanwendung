package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Das UserDAO Interface implementiert alle Datenbankabfragen, dank der erbenden Klasse JpaRepository.
 * Sozusagen das Repository für die Benutzerkontoverwaltung.
 * @author seymen
 *
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    /**
     * Sucht nach dem Benutzerkonto anhand von Benutzername und Passwort
     * @param username Der Benutzername
     * @param password Das Passwort
     * @return Das gefundene Benutzerkonto.
     */
    User findByUsernameAndPassword(String username, String password);
    
    /**
     * Überprüft ob es ein Benutzerkonto mit dem Benutzernamen gibt.
     * @param username Der Benutzername
     * @return true, wenn Benutzerkonto mit angegebenem Benutzernamen existiert
     */
    boolean existsByUsername(String username);
    
	/**
	 * Findet das Benutzerkonto anhand des Benutzernamens
	 * @param username Der Benutzername
	 * @return Ein Optional, das das Benutzerkonto enthält, wenn es gefunden wurde, oder sonst Optional.empty().
	 */
	Optional<User> findByUsername(String username);
}