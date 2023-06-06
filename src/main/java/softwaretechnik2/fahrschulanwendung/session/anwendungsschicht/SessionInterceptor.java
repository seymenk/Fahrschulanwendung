package softwaretechnik2.fahrschulanwendung.session.anwendungsschicht;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor-Klasse zur Verwaltung von Sessions.
 * @author seymen
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    
    /**
     * Prüft vor dem Aufruf einer Methode, ob der Benutzer eingeloggt ist.
     * Leitet den Benutzer zur Login-Seite weiter, wenn er nicht angemeldet ist.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userId") == null) {
            response.sendRedirect("/login"); // Leiten Sie den Benutzer zur Login-Seite weiter, wenn er nicht angemeldet ist
            return false;
        }
        return true;
    }
    
    /**
     * Überprüft, ob der Benutzer eine bestimmte Rolle hat.
     * @param request Das HttpServletRequest-Objekt.
     * @param requiredRole Die erforderliche Rolle.
     * @return true, wenn der Benutzer die erforderliche Rolle hat, sonst false.
     */
    public boolean hasUserRole(HttpServletRequest request, String requiredRole) {
        String userRole = (String) request.getSession().getAttribute("userRole");
        return userRole != null && userRole.equals(requiredRole);
    }
}
