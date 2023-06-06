package softwaretechnik2.fahrschulanwendung.autoverwaltung.anwendungsschicht;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Eine DTO Klasse von Manuell, welche von AutoDTO erbt.
 * @author seymen
 *
 */
@JsonTypeName("softwaretechnik2.fahrschule.swt.autoverwaltung.ManuellDTO")
public class ManuellDTO extends AutoDTO{
	public ManuellDTO() {
	}
	
	public ManuellDTO(AutoDTO autoDTO) {
	    this.setId(autoDTO.getId());
	    this.setMarke(autoDTO.getMarke());
	    this.setModell(autoDTO.getModell());
	    this.setBaujahr(autoDTO.getBaujahr());
	    this.setFarbe(autoDTO.getFarbe());
	    this.setPreis(autoDTO.getPreis());
	}
}
