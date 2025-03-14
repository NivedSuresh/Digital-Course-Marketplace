# Project Folder Structure

This project follows a clean architecture approach, organizing the code into different layers to maintain separation of concerns.

## 📂 Folder Structure

```
src/main/kotlin/org/dcmp/
├── api
│   ├── advice           # Global exception handlers
│   ├── controllers      # API controllers (entry points for requests)
│   ├── utils            # Utility/helper functions
│
├── application
│   ├── command          # Command objects for handling business logic
│   ├── command_handler  # Handlers for executing commands
│   ├── dto              # Data Transfer Objects (DTOs)
│   ├── mapper           # Mappers for entity <-> DTO conversion
│   ├── query            # Query objects for fetching data
│   ├── query_handler    # Handlers for executing queries
│   ├── service          # Business logic services
│
├── domain
│   ├── contracts        # Interfaces for repositories and external dependencies
│   ├── entity           # Domain entities representing core business models
│   ├── exception        # Custom exceptions
│
├── infrastructure
│   ├── persistence/jpa  # JPA repositories for database interactions
│   ├── security         # Security-related configurations and classes
│
└── DigitalCourseMarketplaceApplication.kt  # Main application entry point
```

## 📌 Key Package Paths

- **Controllers:** `org.dcmp.api.controllers`
- **Services:** `org.dcmp.application.service`
- **Repositories:** `org.dcmp.infrastructure.persistence.jpa`
- **DTOs:** `org.dcmp.application.dto`
- **Entities:** `org.dcmp.domain.entity`


## Entity-Relationship Diagram

![ER Diagram](/src/main/resources/static/erd.png)

