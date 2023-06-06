package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import java.sql.Date;

/**
 * Die Fahrschueler Klasse als DTO Klasse.
 * @author seymen
 *
 */
public class FahrschuelerDTO {
	
	private Long id;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private Adresse adresse;
	private Integer theoriestunden;
	private Integer fahrstunden;
	private Integer sonderfahrten;
	private Double insgesamtBezahlterBetrag;
	private Double neuEingezahlterBetrag;

	public FahrschuelerDTO() {
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