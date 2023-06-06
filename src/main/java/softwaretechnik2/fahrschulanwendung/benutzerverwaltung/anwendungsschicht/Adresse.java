package softwaretechnik2.fahrschulanwendung.benutzerverwaltung.anwendungsschicht;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Die Klasse Adresse dient als Datentyp für die Adressverwaltung der Fahrschüler und Fahrlehrer
 * @author seymen
 *
 */
@Embeddable
public class Adresse {
	
	@Column(name = "adresse_strasse")
	private String adresseStrasse;
	@Column(name = "adresse_hausnummer")
	private String adresseHausnummer;
	@Column(name = "adresse_ort")
	private String adresseOrt;
	@Column(name = "adresse_plz")
	private Integer adressePlz;

	public String getStrasse() {
		return adresseStrasse;
	}

	public void setStrasse(String strasse) {
		this.adresseStrasse = strasse;
	}

	public String getHausnummer() {
		return adresseHausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.adresseHausnummer = hausnummer;
	}

	public String getOrt() {
		return adresseOrt;
	}

	public void setOrt(String ort) {
		this.adresseOrt = ort;
	}

	public Integer getPlz() {
		return adressePlz;
	}

	public void setPlz(Integer plz) {
		this.adressePlz = plz;
	}
}
