package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "benutzerkonto")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "benutzername", unique = true)
	private String username;

	@Column(name = "passwort")
	private String password;

	@Column(name = "fahrlehrerID")
	private Long flID;

	@Column(name = "fahrschuelerID")
	private Long fsID;

	// Getter und Setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getFlID() {
		return flID;
	}

	public void setFlID(Long flID) {
		this.flID = flID;
	}

	public Long getFsID() {
		return fsID;
	}

	public void setFsID(Long fsID) {
		this.fsID = fsID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}