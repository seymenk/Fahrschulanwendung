package softwaretechnik2.fahrschulanwendung.session.anwendungsschicht;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userId") == null) {
            response.sendRedirect("/login"); // Leiten Sie den Benutzer zur Login-Seite weiter, wenn er nicht angemeldet ist
            return false;
        }
        return true;
    }
    
    public boolean hasUserRole(HttpServletRequest request, String requiredRole) {
        String userRole = (String) request.getSession().getAttribute("userRole");
        return userRole != null && userRole.equals(requiredRole);
    }
}
