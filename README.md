# Pokedex Application

Pokedex application has the following main functionality:
1) Saving Pokemon data in Pokedex. 
Note: during Application startup, pokemon list in data_pokemon.json (in resource directory) will populate initial Pokedex data.

2) Filter/Search Pokemon data in Pokedex.

3) Saving and modifying caught Pokemon by user.

This application server is built using Spring MVC, making use of H2 database and JPA Repository module.
API Contract: https://docs.google.com/document/d/1O45V11-gl-S-8j5T9oRUXDmYnjUnalFCgPOweDuxHow

Current Limitation: the application is not supporting concurrent user, only one user can be active at one time.

Future improvement: Pokedex App and User handling service can be seperated into two different application server (following microservice architecture).


# How to run:

1) Install maven - http://maven.apache.org/install.html

2) Clone the repository, and go to base directory of this application.

3) Build the application using command: **mvn package**

4) Run the application using command: **mvn spring-boot:run**
