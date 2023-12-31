<h1 style="font-size: 42px;">HTML GENERATION API</h1>

# Summary
HTML generation api is a REST application which simulates the generation of HTML page components in different threads depending on the available processors for the JVM and also simulates an appeal to a third-party API that can return 429 status with a certain frequency and also describes how to perform a repeated request to the API using the exponential indentation mechanism.
# Endpoints
<h1 style="font-size: 14px;">Acceptable endpoints in the application:</h1>

- GET: /generate - generation of HTML components.
- GET: /external-data - applying to a third-party API.

# Project structure
- src/main/java: contains all the source code for the application.
- Dockerfile - is a text file that contains instructions for building a Docker image.
- checkstyle/checkstyle.xml - is a configuration file for the checkstyle tool, which is used to check the code style. It contains settings for various checkstyle modules that perform various code checks for compliance with style standards.
- pom.xml - used to configure and create a Maven project, add the necessary dependencies.

# Technologies used
- JDK 17
- SpringBoot 3.1.4
- H2 database
- Apache Maven 3.8.1
- Docker

# How to run the application
In order to launch this project, you need to take the following steps:
1. Clone this project from GitHub to your local machine.
2. Install the following software:
- IntelliJ IDEA (IDE) to run the application.
- Install Postman for sending requests, or you can use your browser.
- Install DOCKER DESKTOP from https://www.docker.com/products/docker-desktop/ if you want to run the program through a docker and follow the setup steps).
3. Open the project in IntelliJ IDEA.
4. Build the project using Maven: mvn clean package.
5. In the terminal, enter the command : docker build -t generation-app .
- and then enter the command to run the application on port 8081: docker run -p 8081:8080 generation-app
6. If all the steps have been followed correctly, the server will start successfully.
7. Use Postman or a web browser to interact with the endpoints and test the application by this URL http://localhost:8081/
   Please follow these instructions carefully to launch the project.
