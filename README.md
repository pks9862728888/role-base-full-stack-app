# About <hr />
Demo project to show RBAC

## To spin up mysql server in docker <hr />
```shell
docker run --name mysql-rbac -e MYSQL_ROOT_USER=root -e MYSQL_ROOT_PASSWORD=testpw -e MYSQL_DATABASE=mysql-rbac -p 3307:3306 -d mysql:9.0
```
