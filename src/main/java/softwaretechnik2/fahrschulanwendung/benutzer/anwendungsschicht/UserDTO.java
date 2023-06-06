package softwaretechnik2.fahrschulanwendung.benutzer.anwendungsschicht;

/**
 * Die Benutzerkonto Klasse als DTO Klasse.
 * @author seymen
 *
 */
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private Long flID;
	private Long fsID;

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