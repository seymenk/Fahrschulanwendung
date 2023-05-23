package softwaretechnik2.fahrschulanwendung.benutzer.datenschicht;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht.Adresse;

import java.util.Date;

@Entity
@Table(name = "Fahrschueler")
public class Fahrschueler {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fahrschuelerID")
	private Long id;

	@Column(name = "vorname")
	private String vorname;

	@Column(name = "nachname")
	private String nachname;

	@Column(name = "geburtsdatum")
	private Date geburtsdatum;

	@Embedded
	private Adresse adresse;

	@Column(name = "theoriestunden")
	private Integer theoriestunden;

	@Column(name = "fahrstunden")
	private Integer fahrstunden;

	@Column(name = "sonderfahrten")
	private Integer sonderfahrten;

	@Column(name = "insgesamtBezahlterBetrag")
	private Double insgesamtBezahlterBetrag;

	@Column(name = "neuEingezahlterBetrag")
	private Double neuEingezahlterBetrag;

	public Fahrschueler() {
		this.theoriestunden = 0;
		this.fahrstunden = 0;
		this.sonderfahrten = 0;
		this.insgesamtBezahlterBetrag = 0.0;
		this.neuEingezahlterBetrag = 0.0;
	}

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
    
	public Integer getTheoriestunden() {
		return theoriestunden;
	}

	public void setTheoriestunden(Integer theoriestunden) {
		this.theoriestunden = theoriestunden;
	}

	public Integer getFahrstunden() {
		return fahrstunden;
	}

	public void setFahrstunden(Integer fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public Integer getSonderfahrten() {
		return sonderfahrten;
	}

	public void setSonderfahrten(Integer sonderfahrten) {
		this.sonderfahrten = sonderfahrten;
	}

	public Double getInsgesamtBezahlterBetrag() {
		return insgesamtBezahlterBetrag;
	}

	public void setInsgesamtBezahlterBetrag(Double insgesamtBezahlterBetrag) {
		this.insgesamtBezahlterBetrag = insgesamtBezahlterBetrag;
	}

	public Double getNeuEingezahlterBetrag() {
		return neuEingezahlterBetrag;
	}

	public void setNeuEingezahlterBetrag(Double neuEingezahlterBetrag) {
		this.neuEingezahlterBetrag = neuEingezahlterBetrag;
	}

	public Long getId() {
		return id;
	}
}