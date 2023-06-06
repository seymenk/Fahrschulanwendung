package softwaretechnik2.fahrschulanwendung;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Diese Konfigurationsklasse enthält Bean-Definitionen, die in der gesamten
 * Anwendung benötigt werden.
 * 
 * @author seymen
 *
 */
@Configuration
public class AppConfig {
	/**
	 * Erstellt und konfiguriert einen ModelMapper, um das Mapping zwischen normaler
	 * Klasse und DTOs zu vereinfachen
	 * 
	 * @return Eine neu erstellte und konfigurierte Instanz von ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}