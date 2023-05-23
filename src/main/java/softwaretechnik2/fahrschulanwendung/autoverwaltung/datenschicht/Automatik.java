package softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Automatik")
@JsonTypeName("softwaretechnik2.fahrschule.swt.autoverwaltung.Automatik")
public class Automatik extends Auto {
	public Automatik() {
	}
	
	public Automatik(Auto auto) {
	    this.setId(auto.getId());
	    this.setMarke(auto.getMarke());
	    this.setModell(auto.getModell());
	    this.setBaujahr(auto.getBaujahr());
	    this.setFarbe(auto.getFarbe());
	    this.setPreis(auto.getPreis());
	}
}