# TP11 API REST Sécurisée avec JWT - Spring Boot

##  Description
Ce projet est une API REST sécurisée implémentant l'authentification JWT (JSON Web Token) avec Spring Boot 3, Spring Security 6 et Spring Data JPA.

##  Objectifs du TP
- Créer un endpoint d'authentification REST générant des tokens JWT
- Mettre en place un filtre d'autorisation personnalisé
- Comprendre l'intégration du modèle stateless avec Spring Security
- Sécuriser les endpoints selon les rôles et droits d'accès

##  Technologies Utilisées
- *Spring Boot 3.2.0*
- *Spring Security 6*
- *Spring Data JPA*
- *MySQL 8*
- *JWT (jjwt)*
- *Lombok*
- *Maven*

##  Structure du Projet

src/main/java/ma/ens/security/
├── config/
│   └── SecurityConfig.java
├── entities/
│   ├── User.java
│   └── Role.java
├── repositories/
│   ├── UserRepository.java
│   └── RoleRepository.java
├── services/
│   └── CustomUserDetailsService.java
├── jwt/
│   ├── JwtUtil.java
│   └── JwtAuthorizationFilter.java
└── web/
    ├── AuthController.java
    └── TestController.java


##  Installation et Démarrage

### Prérequis
- Java 17 ou 21
- MySQL 8+
- Maven 3.6+

### 1. Configuration de la Base de Données
sql
CREATE DATABASE security_jwt;


### 2. Configuration de l'Application
properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/security_jwt
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

jwt.secret=MySuperSecretKeyForJwtAuthentication12345678901234567890
jwt.expiration=3600000


### 3. Démarrage de l'Application
bash
mvn spring-boot:run


##  Endpoints de l'API

###  Endpoints Publics
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /api/public/hello | Endpoint public de test |

###  Authentification
| Méthode | URL | Body | Description |
|---------|-----|------|-------------|
| POST | /api/auth/login | {"username":"user","password":"password"} | Authentification JWT |

###  Endpoints Sécurisés
| Méthode | URL | Rôle Requis | Description |
|---------|-----|-------------|-------------|
| GET | /api/user/profile | USER, ADMIN | Profil utilisateur |
| GET | /api/admin/dashboard | ADMIN | Dashboard administrateur |

##  Utilisateurs de Test

| Username | Password | Rôles |
|----------|----------|-------|
| user | password | USER |
| admin | admin | USER, ADMIN |

##  Tests avec Postman

### 1. Test Endpoint Public

   GET http://localhost:8080/api/public/hello 
  
<img width="959" height="497" alt="1" src="https://github.com/user-attachments/assets/1ec2997d-282a-4a58-a0d4-259e1909c9eb" />

### 2. Authentification Utilisateur

 POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
 

<img width="959" height="512" alt="2" src="https://github.com/user-attachments/assets/925a5e4a-763a-4e36-8d85-7285cc979803" />


<img width="956" height="538" alt="4" src="https://github.com/user-attachments/assets/07b452b9-d09e-44e5-bf45-c25a26280269" />

### 3. Accès Endpoint Sécurisé

GET http://localhost:8080/api/user/profile
  Authorization: Bearer <votre-token-jwt>
 

<img width="959" height="539" alt="3" src="https://github.com/user-attachments/assets/590e3752-ef48-4b3d-aaa4-13fc7233efda" />

   /api/admin/dashboard 

<img width="942" height="488" alt="5" src="https://github.com/user-attachments/assets/c4ae08af-adcf-4f2a-b993-1030ffe88b49" />

##  Fonctionnalités Implémentées

###  Sécurité
- *Authentification Stateless* avec JWT
- *Autorisation basée sur les rôles* (USER/ADMIN)
- *Filtre JWT personnalisé* pour validation des tokens
- *Protection CSRF* désactivée pour API REST
- *Gestion des sessions* stateless

###  Persistance
- *Entités JPA* avec relations ManyToMany
- *Chiffrement des mots de passe* avec BCrypt
- *Initialisation automatique* des données de test

###  JWT
- *Génération de tokens* avec expiration
- *Validation et extraction* des claims
- *Signature HMAC-SHA256*
- *Tokens auto-porteurs* sans stockage serveur

##  Concepts Pédagogiques Maîtrisés

### Spring Security
- Configuration de SecurityFilterChain
- Gestion des AuthenticationManager
- Implémentation de UserDetailsService
- Filtres personnalisés avec OncePerRequestFilter

### JWT
- Structure Header-Payload-Signature
- Tokens Bearer dans les headers HTTP
- Validation cryptographique
- Gestion de l'expiration

### Architecture REST
- Contrôleurs Spring MVC
- Gestion des exceptions
- Sérialisation JSON automatique
- Stateless vs Stateful

##  Dépannage

### Erreurs Courantes
- **403 Forbidden** : Token manquant ou insuffisant
- **401 Unauthorized** : Token expiré ou invalide
- **405 Method Not Allowed** : Mauvaise méthode HTTP

### Vérifications
- Base de données MySQL démarrée
- Port 8080 disponible
- Données d'initialisation créées



##  Auteur
**BENZIAT HANA**  
TP Spring Security JWT

---
