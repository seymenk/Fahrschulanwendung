package softwaretechnik2.fahrschulanwendung;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurationsklasse für die Web-Applikation. Enthält Konfigurationen für den
 * View-Controller.
 * 
 * @author seymen
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Sorgt dafür, dass man bei Start der Applikation auf der /login Seite landet.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
	}
}
