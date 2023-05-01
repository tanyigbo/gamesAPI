# Game Tracker API

### Table of Contents
- [Description](#description)
- [User Stories](#user-stories)
- [Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
- [Dependencies](#dependencies)

### Description
An API that lets users track the games they have played and provides them a rating

### User Stories

- As a new user, I want to be able to make an account to be able to use the app.
- As a user, I want to be able to log in to my account to be able to use the app.
- As a user, I want to be able to add games to my list of games to keep track of which games I have played and their
  ratings.
- As a registered user, I want to be able to see my list of games so that I can see the rating and completion status of
  the games on my list.
- As a registered user, I want to be able to remove a game from my game's list so that I can delete games I no longer
  want to keep track of.
- As a registered user, I want to be able to update the information of my favorite games so that the status of my game
  list will be up-to-date.

### API Endpoints

| Request Type | URL                   | Functionality                  | Access  |
|--------------|-----------------------|--------------------------------|---------|
| POST         | /auth/users/register  | User registration              | Public  |
| POST         | /auth/users/login     | User login                     | Public  |
| POST         | /api/genres/new       | Creates genre entry            | Private |
| POST         | /api/games/{gameId}   | Creates game entry             | Private |
| GET          | /api/genres           | View all genres                | Public  |
| GET          | /api/genres/{genreId} | View genre by ID               | Public  |
| GET          | /api/games/           | View all user's games          | Private |
| GET          | /api/games/{gameId}   | View user's game by ID         | Private |
| PUT          | /api/genres/{genreId} | User updates genre description | Private |
| PUT          | /api/games/{gameId}   | User updates game              | Private |
| DELETE       | /api/games/{gameId}   | User deletes game              | Private |

### Technologies Used

- [Visual Paradigm Online](https://online.visual-paradigm.com/)
- [Spring Initializer](https://start.spring.io/)
- [IntelliJ IDE](https://www.jetbrains.com/idea/)
- [Git/GitHub](https://github.com/)
- [Maven Repository](https://mvnrepository.com/)
- [PostgresSQL Database](https://www.postgresql.org/)
- [PostMan](https://www.postman.com/)

### Dependencies
- [Spring Boot Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter/3.0.6)  
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test/3.0.6)  
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.0.5)  
- [Postgres Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0)  
- [Spring Boot Data Jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.0.6)  
- [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
