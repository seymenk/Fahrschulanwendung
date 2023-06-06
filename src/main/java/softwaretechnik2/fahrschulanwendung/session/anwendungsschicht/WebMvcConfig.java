package softwaretechnik2.fahrschulanwendung.session.anwendungsschicht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurationsklasse zur Registrierung von Interceptors.
 * @author seymen
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    /**
     * Fügt den SessionInterceptor zum Interceptor-Register hinzu und schließt den Login-Pfad aus.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).excludePathPatterns("/login");
    }
}
