package softwaretechnik2.fahrschulanwendung.autoverwaltung.anwendungsschicht;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("softwaretechnik2.fahrschule.swt.autoverwaltung.AutomatikDTO")
public class AutomatikDTO extends AutoDTO{
	public AutomatikDTO() {
	}
	
	public AutomatikDTO(AutoDTO autoDTO) {
	    this.setId(autoDTO.getId());
	    this.setMarke(autoDTO.getMarke());
	    this.setModell(autoDTO.getModell());
	    this.setBaujahr(autoDTO.getBaujahr());
	    this.setFarbe(autoDTO.getFarbe());
	    this.setPreis(autoDTO.getPreis());
	}
}
