# Spring Boot Player Service
## Description
This service provides CRUD operations for player data stored in a MySQL database. Database is deployed to a docker image and service will also be configured to be deployed in a container similarly.

## Setup
1. Set up the database by running: `docker-compose -p <stack name> up` where `<stack name>` is what you want to call your overall docker stack. 
2. To run the SpringBoot application you can either:
   - Select the Run button if your IDE provides one
   - Run `mvn spring-boot:run` to launch

## Testing
Test with the provided Postman collection in the `src/main/resources/postman` folder. The provided collections provides sample REST requests for all endpoints in the controller