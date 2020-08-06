[![CircleCI](https://circleci.com/gh/akmal2409/sf-pet-clinic.svg?style=svg)](https://circleci.com/gh/akmal2409/sf-pet-clinic)
# sf-pet-clinic

Pet Clinic app on Spring


# Running the project in Docker
* Run command: 

    mvn clean package
* Pull Spring Boot image from Docker Hub
    
    docker run spring-boot-docker
* Extract from target folder under WEB part of the project pet-clinic-web-SNAPSHOT-006.jar and place it in a folder together with Dockerfile
* Open the console in that folder
* Run command 

    docker build -t spring-boot-docker .
* Deploy your container and map port to 8080
    
    docker run -d -p 8080:8080 spring-boot-docker
