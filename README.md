# E-Commerce REST API

API RESTful développée en Java 17 avec le framework Spring Boot 3, dédiée à la gestion d'un catalogue de produits et au traitement transactionnel de commandes en ligne.

---

## Fonctionnalités clés

- **Architecture N-Tier (4 couches)** : Découpage strict en `Controller`, `Service`, `Repository`, `Entity`/`DTO`.
- **Catalogue Produits** : Opérations CRUD complètes avec validation des données entrantes (`Jakarta Validation`).
- **Passage de Commandes Multi-articles** :
  - Snapshot des prix unitaires à la commande.
  - Décrémentation automatique des inventaires.
  - Gestion de l'atomicité ACID via `@Transactional` (rollback automatique en cas de stock insuffisant).
- **Gestion Globale des Erreurs** : Interception centralisée via `@RestControllerAdvice` retournant des réponses d'erreurs normées (codes HTTP 400, 404).
- **Documentation Interactive** : Intégration Swagger UI / OpenAPI 3 pour tester les routes en direct.

---

## Stack Technique

- **Langage** : Java 17
- **Framework** : Spring Boot 3
- **Persistance** : Spring Data JPA, Hibernate
- **Base de données** : H2 Database (In-Memory)
- **Documentation** : Springdoc OpenAPI 3 (Swagger UI)
- **Outil de build** : Maven

---

## Installation et Exécution

### 1 - Prérequis
- Java Development Kit (JDK) 17+
- Git
### 2 - Cloner et démarrer l'API
```bash
git clone [https://github.com/DeLaHamz/ecommerce-api.git](https://github.com/DeLaHamz/ecommerce-api.git)
cd ecommerce-api
./mvnw spring-boot:run
