# E-Commerce REST API

API RESTful développée en Java 17 avec le framework Spring Boot 3, pour la gestion d'un catalogue de produits et au traitement des transactions des commandes en ligne.

---

## Fonctionnalités clés

- **Architecture N-Tier (4 couches)**: Découpage strict en `Controller`, `Service`, `Repository`, `Entity`/`DTO`.
- **Catalogue Produits**: Opérations CRUD complètes avec la validation des données entrantes (`Jakarta Validation`).
- **Passage de Commandes Multi-articles**:
  - Snapshot des prix unitaires à la commande.
  - Décrémentation automatique des inventaires.
  - Gestion de l'atomicité ACID avec `@Transactional` (rollback automatique en cas de stock insuffisant).
- **Gestion Globale des Erreurs**: Interception centralisée avec `@RestControllerAdvice` qui retourne des réponses d'erreurs normées (codes HTTP 400, 404).
- **Documentation Interactive**: Intégration Swagger UI / OpenAPI 3 pour tester les routes en direct.

---

## Stack Technique

- **Langage**: Java 17
- **Framework**: Spring Boot 3
- **Persistance**: Spring Data JPA, Hibernate
- **Base de données**: H2 Database (In-Memory)
- **Documentation**: Springdoc OpenAPI 3 (Swagger UI)
- **Outil de build**: Maven

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
```

Sous Windows (PowerShell / CMD) :

```powershell
.\mvnw.cmd spring-boot:run
```

L'application démarre par défaut sur le port **8080** : `http://localhost:8080`.

---

## Documentation Interactive & Console

- **Swagger UI (Documentation interactive)** : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Specs (JSON)** : [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **Console SQL H2** : [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - **JDBC URL** : `jdbc:h2:mem:ecommercedb`
  - **User Name** : `sa`
  - **Password** : _(laisser vide)_

---

## Endpoints de l'API

### Produits (`/api/products`)

| Méthode  | Endpoint             | Description                              |
| :------- | :------------------- | :--------------------------------------- |
| `GET`    | `/api/products`      | Récupérer la liste de tous les produits  |
| `GET`    | `/api/products/{id}` | Consulter un produit par son identifiant |
| `POST`   | `/api/products`      | Ajouter un produit dans le catalogue     |
| `DELETE` | `/api/products/{id}` | Supprimer un produit du catalogue        |

### Commandes (`/api/orders`)

| Méthode | Endpoint           | Description                                                |
| :------ | :----------------- | :--------------------------------------------------------- |
| `GET`   | `/api/orders`      | Lister l'historique des commandes passées                  |
| `GET`   | `/api/orders/{id}` | Consulter les détails d'une commande spécifique            |
| `POST`  | `/api/orders`      | Créer une commande multi-articles (avec décompte de stock) |

---

## Exemples de Requêtes (Payloads JSON)

### 1. Créer un produit (`POST /api/products`)

```json
{
  "name": "Casque Audio Sans Fil",
  "description": "Casque Bluetooth avec réduction active de bruit",
  "price": 129.99,
  "stockQuantity": 25
}
```

### 2. Passer une commande (`POST /api/orders`)

```json
{
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

### 3. Exemple de Réponse d'Erreur Standardisée (`400 Bad Request`)

```json
{
  "status": 400,
  "error": "Règle métier non respectée",
  "message": "Stock insuffisant pour le produit : Casque Audio Sans Fil (Demandé : 50, Disponible : 23)",
  "validationErrors": null,
  "timestamp": "2026-10-14T12:00:00"
}
```

---

## Auteur

- **Hamza EL BOUBAKRI** - [GitHub](https://github.com/DeLaHamz) - [LinkedIn](https://linkedin.com/in/hamza-el-boubakri)
