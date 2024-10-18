# E-commerce app demo <hr />
This project demos how to use cloud native features in e-commerce application.

### Microservices: <hr />
* `rbac-ecommerce-common`: Stores common entity, enums, constants etc.
* `rbac-ecommerce-eureka-server`: Eureka server for service discovery and health checks
* `rbac-ecommerce-config-server`: Externalize config for applications
* `rbac-ecommerce-gateway`: Centralized service to route traffic from frontend to backend, implement rate limiting, authentication
* `rbac-ecommerce-persistence`: Persistence repo to store persistence entities and repositories
* `rbac-ecommerce-product-catalog`: Handles product listings, categories, and inventory management.
* `rbac-ecommerce-user-service`: Handles user registration and authentication

## Sonar scan <hr />
### To initiate sonar scan
```shell
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=role-based-ecommerce-full-stack-app \
  -Dsonar.projectName='role-based-ecommerce-full-stack-app' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=$SONAR_TOKEN
```
Set system env variable SONAR_TOKEN
