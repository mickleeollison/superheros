# README #

### Motivation ###

This is a superhero application users can create, view, update, and delete superhero powers, heros, teams and have the ability to add url images for each superhero.
There is also search capabiilities for powers, heros and teams where they can type in keywords using a boolean 'and' or 'or' search. 

### Technologies used and dependencies ###

Written in Java useing Spring Boot, Maven, JPA, Hibernate, JQuery, HTML, and CSS

Dependencies include reflections, spring-boot-starter-web, spring-bot-starter-data-jpa, commons-lang3, hibernate-entitymanager, mysql-connector-java, mockito-all, selenium-java, and junit

### Installation ###

In order to run the project it must first be connected to a MySQL database which can be configured in the application.properties file under the spring.datasource username, password, and url properties

To run, import this project into your prefered IDE as a Maven project and run as a Java Application.

### Tests ###

To run unit tests for services click on the ServiceTests class and run as JUnit tests and for validation tests navigate to the ValidationTests class

### Additional Notes ###

Initial Data is loaded via initial_data.sql file in the src/main/resources/public folder and all date is removed each time the project is ran