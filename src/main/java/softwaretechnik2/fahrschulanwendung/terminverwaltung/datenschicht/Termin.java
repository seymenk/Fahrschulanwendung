package softwaretechnik2.fahrschulanwendung.terminverwaltung.datenschicht;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Die Termin Klasse zur Implementierung von Terminen mit Datenbankanbindung. Die Gegenklasse zu TerminDTO.
 * @author seymen
 *
 */
@Entity
@Table(name = "Termin")
public class Termin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private int id;
	@JsonProperty("fahrschuelerID")
	private int fahrschuelerID;
	@JsonProperty("fahrlehrerID")
	private int fahrlehrerID;
	@JsonProperty("startUhrzeit")
	private String startUhrzeit;
	@JsonProperty("endUhrzeit")
	private String endUhrzeit;
	@JsonProperty("datum")
	private Date datum;
	@JsonProperty("gebucht")
	private boolean gebucht;

	public int getFahrschuelerID() {
		return fahrschuelerID;
	}

	public void setFahrschuelerID(int fahrschuelerID) {
		this.fahrschuelerID = fahrschuelerID;
	}

	public int getFahrlehrerID() {
		return fahrlehrerID;
	}

	public void setFahrlehrerID(int fahrlehrerID) {
		this.fahrlehrerID = fahrlehrerID;
	}

	public String getStartUhrzeit() {
		return startUhrzeit;
	}

	public void setStartUhrzeit(String uhrzeit) {
		this.startUhrzeit = uhrzeit;
	}

	public String getEndUhrzeit() {
		return endUhrzeit;
	}

	public void setEndUhrzeit(String uhrzeit) {
		this.endUhrzeit = uhrzeit;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public boolean isGebucht() {
		return gebucht;
	}

	public void setGebucht(boolean gebucht) {
		this.gebucht = gebucht;
	}
	
	public String createEndUhrzeit(String uhrzeit) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime time = LocalTime.parse(uhrzeit, formatter);
	    LocalTime newTime = time.plusHours(1);
	    return newTime.format(formatter);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}