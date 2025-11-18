# Todo-Management-API

Built a Spring MVC/Spring Boot REST API for managing users and todos using Spring Data JPA with H2 DB. Implemented CRUD operations and entity relationships, and tested all endpoints via Postman.
The app is made using Spring Boot, H2 Database and Hibernate. It is based on MVC architecture, which consists of Model, View and Controller.

The Entities are Users and Todo. The User has a One to Many Mapping with Todo, as a single user can have multiple Todos.

The Service layers is responsible for handling logic.

It makes use of JPA repository to handle CRUD operations.

The App architecture can be understood by this flowchart below.
