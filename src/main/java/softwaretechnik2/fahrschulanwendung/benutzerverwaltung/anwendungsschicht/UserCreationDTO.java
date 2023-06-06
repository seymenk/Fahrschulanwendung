package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import java.util.Date;

/**
 * Die Klasse UserCreationDTO dient zur Unterstützung der UserCreationFactory Klasse.
 * @author seymen
 *
 */
public class UserCreationDTO {
	private Long id;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private Adresse adresse;
	private String role;

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
