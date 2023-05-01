# Game Tracker API

An API that lets users track the games they have played and provide them a rating

## Endpoints

| Request Type | URL                   | Functionality          | Access  |
|--------------|-----------------------|------------------------|---------|
| POST         | /auth/users/register  | User registration      | Public  |
| POST         | /auth/users/login     | User login             | Public  |
| POST         | /api/genres           | Creates genre entry    | Private |
| POST         | /api/games/{gameId}   | Creates game entry     | Private |
| GET          | /api/genres           | View all genres        | Public  |
| GET          | /api/genres/{genreId} | View genre by ID       | Public  |
| GET          | /api/games/           | View all user's games  | Private |
| GET          | /api/games/{gameId}   | View user's game by ID | Private |

Technologies Used

- Visual Paradigm Online UML modeler
- Spring Initializer (start.spring.io)
- IntelliJ IDE
- Git/GitHub
- Maven Repository
- Spring Data JPA
- PgAdmin PostgresSQL Database
- PostMan

Unsolved Problem

- Wanted to have games be a many-to-many relationship, but was losing too much time researching the topic.