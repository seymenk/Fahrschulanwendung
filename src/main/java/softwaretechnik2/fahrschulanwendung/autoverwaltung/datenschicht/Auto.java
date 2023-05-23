package softwaretechnik2.fahrschulanwendung.autoverwaltung.datenschicht;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "getriebe", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Manuell.class, name = "softwaretechnik2.fahrschule.swt.autoverwaltung.Manuell"),
		@JsonSubTypes.Type(value = Automatik.class, name = "softwaretechnik2.fahrschule.swt.autoverwaltung.Automatik") })
public abstract class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marke;
	private String modell;
	private Integer baujahr;
	private String farbe;
	private Float preis;
	@Column(insertable = false, updatable = false)
	private String getriebe;

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public Integer getBaujahr() {
		return baujahr;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public Float getPreis() {
		return preis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGetriebe() {
		return getriebe;
	}

	public void setGetriebe(String getriebe) {
		this.getriebe = getriebe;
	}

	public void setBaujahr(Integer baujahr) {
		this.baujahr = baujahr;
	}

	public void setPreis(Float preis) {
		this.preis = preis;
	}
}