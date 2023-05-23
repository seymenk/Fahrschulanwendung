package softwaretechnik2.fahrschulanwendung.terminverwaltung.anwendungsschicht;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TerminDTO {
	private int id;
	private int fahrschuelerID;
	private int fahrlehrerID;
	private String startUhrzeit;
	private String endUhrzeit;
	private Date datum;
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