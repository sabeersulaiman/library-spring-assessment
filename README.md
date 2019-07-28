# Library (Spring + Angular Assessment)
This is a simple library web project created as part of an assessment. Project utilizes the following technologies:

- Spring Boot v2.1.6 (Back-end)
- Angular v8.1.1 (Front-end)

## Running using docker
If your system have docker and docker-compose installed and setup. Please use the command `docker-compose up` to build and run the application. No extra setup required.

## Building the application
The application is in two parts: Angular and Spring.

### Running the Spring Application
You can open the spring boot application in `library-spring` in your favorite IDE and run the application or the maven `spring-boot:run` command. Please note that you have to configure the `src/main/resources/application.properties` with the correct database credentials for your system.

### Running the Angular Application
Before running the Angular application make sure you have the spring-api up and running. First change directory to the `library-angular` folder. Now use the following commands to install and run the Angular application:

```bash
$ npm install
$ ng serve
```

## API Documentation
- [v1/books](DOCS/books.md)