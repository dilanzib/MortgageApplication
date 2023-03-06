## Mortgage Application

### How to start the app locally
- Running the app with docker: 
  - Open your Docker Desktop
  - Run the command 
    -      mvn clean package
  - Build the application with docker
    -      docker build -t mortgage-app .
  - Run the docker 
    -     docker run -p 8080:8080 mortgage-app

Check the webpage at:
http://localhost:8080/mortgage

------------------------------------------------------------


  - Running the app without docker, only Spring Boot: 
    -     java -jar target/mortgage-0.0.1-SNAPSHOT.jar

Check the webpage at:
http://localhost:8080/mortgage

