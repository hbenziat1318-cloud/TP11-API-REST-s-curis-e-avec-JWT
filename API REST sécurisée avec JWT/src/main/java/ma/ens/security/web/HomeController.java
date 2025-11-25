package ma.ens.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "application", "API REST Sécurisée avec JWT",
                "version", "1.0",
                "status", "🚀 En ligne",
                "endpoints", """
                🔓 Public:
                - GET /api/public/hello
                
                🔐 Authentification:
                - POST /api/auth/login
                
                🔒 Sécurisé:
                - GET /api/user/profile (USER/ADMIN)
                - GET /api/admin/dashboard (ADMIN seulement)
                """,
                "test_users", """
                👤 user / password
                👨‍💼 admin / admin
                """
        );
    }
}