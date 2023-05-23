package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}