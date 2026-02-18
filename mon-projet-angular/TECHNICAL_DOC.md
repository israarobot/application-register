# Documentation Technique - Projet de Gestion d'Enregistrement

Ce document décrit l'architecture, les technologies et les configurations utilisées dans ce projet full-stack.

## 1. Vue d'Ensemble
Le projet est une application de gestion d'utilisateurs (Inscription/Connexion) composée d'un frontend en Angular et d'un backend en Spring Boot.

## 2. Environnements de Développement
Voici les versions des outils et environnements utilisés lors du développement :

| Composant | Version |
| :--- | :--- |
| **Système d'Exploitation** | Windows |
| **Java (JDK)** | 17.0.12 |
| **Node.js** | 22.13.1 |
| **npm** | 11.1.0 |
| **Angular CLI** | 19.1.5 |
| **PostgreSQL** | 15+ (recommandé) |
| **Maven** | Intégré (Spring Boot 3.2.1) |

## 3. Architecture Technique

### Backend (Spring Boot)
- **Framework** : Spring Boot 3.2.1
- **Langage** : Java 17
- **Sécurité** : Spring Security (BCrypt pour le hachage des mots de passe)
- **Gestion des Données** : Spring Data JPA / Hibernate
- **Validation** : Jakarta Validation (Bean Validation)
- **Base de Données** : PostgreSQL
- **Port du Serveur** : `8081`

### Frontend (Angular)
- **Framework** : Angular 19+
- **Langage** : TypeScript
- **Style** : CSS Vanille / Reactive Forms
- **Serveur de Développement** : `http://localhost:4200`
- **Communication API** : HttpClient (CORS configuré sur `8081`)

## 4. Configuration de la Base de Données
Le backend est configuré pour se connecter à une base de données PostgreSQL locale.

- **URL** : `jdbc:postgresql://localhost:5432/registration_db`
- **Utilisateur** : `postgres`
- **Mot de passe** : `postgres`
- **Mode JPA** : `update` (crée automatiquement les tables si elles n'existent pas)

## 5. Comment Lancer le Projet

### Backend
1. Naviguer vers le dossier `registration-backend`.
2. Lancer la commande : `mvn spring-boot:run`

### Frontend
1. Naviguer vers la racine du projet (`mon-projet-angular`).
2. Lancer la commande : `npm start` (ou `ng serve`)

## 6. Endpoints API Principaux
Les endpoints sont accessibles à l'adresse base `http://localhost:8081`.

- `POST /api/users/register` : Créer un nouveau compte.
- `POST /api/users/login` : Authentification.
