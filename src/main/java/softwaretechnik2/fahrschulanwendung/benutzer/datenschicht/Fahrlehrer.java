package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht.Adresse;

@Entity
@Table(name = "Fahrlehrer")
public class Fahrlehrer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fahrlehrerID")
	private Long id;

	@Column(name = "vorname")
	private String vorname;

	@Column(name = "nachname")
	private String nachname;

	@Column(name = "geburtsdatum")
	private Date geburtsdatum;

	@Embedded
	private Adresse adresse;

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
}
