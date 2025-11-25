package ma.ens.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    // Page d'accueil
    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "message", "Bienvenue dans l'API REST sécurisée avec JWT",
                "version", "1.0",
                "endpoints", "Consultez /api/public/hello pour commencer"
        );
    }

    @GetMapping("/public/hello")
    public Map<String, String> publicEndpoint() {
        return Map.of("message", "Ceci est un endpoint public");
    }

    @GetMapping("/user/profile")
    public Map<String, String> userProfile(Authentication authentication) {
        return Map.of(
                "message", "Bienvenue utilisateur!",
                "username", authentication.getName(),
                "role", "USER"
        );
    }

    @GetMapping("/admin/dashboard")
    public Map<String, String> adminDashboard(Authentication authentication) {
        return Map.of(
                "message", "Tableau de bord administrateur",
                "username", authentication.getName(),
                "role", "ADMIN"
        );
    }
}