package softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Manuell")
@JsonTypeName("softwaretechnik2.fahrschule.swt.autoverwaltung.Manuell")
public class Manuell extends Auto {
	public Manuell() {
	}
	
	public Manuell(Auto auto) {
		this.setId(auto.getId());
	    this.setMarke(auto.getMarke());
	    this.setModell(auto.getModell());
	    this.setBaujahr(auto.getBaujahr());
	    this.setFarbe(auto.getFarbe());
	    this.setPreis(auto.getPreis());
	}
}
