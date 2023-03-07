## Mortgage Application

### Tools needed to successfully build and run the application
- Java 17
- Maven 3.9.0 
- Docker Desktop 


### How to start the app locally
- Running the app with Docker: 
  - Open your Docker Desktop
  
Navigate to the root directory of the project 
  - Build the application by running: 
    -      mvn clean package
  - Build a Docker image of the app:
    -      docker build -t mortgage-app .
  - Run the Docker 
    -     docker run -p 8080:8080 mortgage-app

Check the webpage at:
http://localhost:8080/mortgage

------------------------------------------------------------


  - Running the app without Docker and only Spring Boot: 
    -     java -jar target/mortgage-0.0.1-SNAPSHOT.jar

Check the webpage at:
http://localhost:8080/mortgage

