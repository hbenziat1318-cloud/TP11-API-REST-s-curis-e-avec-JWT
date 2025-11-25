package ma.ens.security;

import ma.ens.security.entities.Role;
import ma.ens.security.entities.User;
import ma.ens.security.repositories.RoleRepository;
import ma.ens.security.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

@SpringBootApplication
public class SpringJwtApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            // Création des rôles
            Role userRole = roleRepository.save(new Role(null, "USER"));
            Role adminRole = roleRepository.save(new Role(null, "ADMIN"));

            // Création des utilisateurs
            if (!userRepository.existsByUsername("user")) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(userRole));
                userRepository.save(user);
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(userRole, adminRole));
                userRepository.save(admin);
            }

            System.out.println("=== Données d'initialisation créées ===");
            System.out.println("Utilisateur: user / password");
            System.out.println("Administrateur: admin / admin");
        };
    }
}