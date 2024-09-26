# About <hr />
Demo project to show RBAC

## To view swagger documentation <hr />
```shell
http://localhost:8080/swagger-ui/index.html
```

## To spin up mysql server in docker <hr />
```shell
docker run --name mysqlrbac -e MYSQL_ROOT_USER=root -e MYSQL_ROOT_PASSWORD=testpw -e MYSQL_DATABASE=mysqlrbac -p 3307:3306 -d mysql:9.0
```

### To apply changes to db
```shell
mvn liquibase:update -Pliquibase -DLIQUIBASE_DB_PASSWORD=testpw -DLIQUIBASE_DB_URL=jdbc:mysql://localhost:3307/mysqlrbac -DLIQUIBASE_DB_USERNAME=root -DLIQUIBASE_DEFAULT_SCHEMA=mysqlrbac
```

### To rollback changes
```shell
mvn liquibase:rollback -Dliquibase.rollbackTag=TAG -Pliquibase -DLIQUIBASE_DB_PASSWORD=testpw -DLIQUIBASE_DB_URL=jdbc:mysql://localhost:3307/mysqlrbac -DLIQUIBASE_DB_USERNAME=root -DLIQUIBASE_DEFAULT_SCHEMA=mysqlrbac
```

### To debug liquibase script by dry run
```shell
mvn liquibase:updateSQL -Pliquibase -DLIQUIBASE_DB_PASSWORD=testpw -DLIQUIBASE_DB_URL=jdbc:mysql://localhost:3307/mysqlrbac -DLIQUIBASE_DB_USERNAME=root -DLIQUIBASE_DEFAULT_SCHEMA=mysqlrbac
```
