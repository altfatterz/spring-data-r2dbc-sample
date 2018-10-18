Example using Spring Data R2DBC

The example is using [Spring Data R2DBC](https://github.com/spring-projects/spring-data-r2dbc) which is currently in 1.0.0.BUILD-SNAPSHOT.

#### Install Spring Data R2DBC into local maven repository:
```bash
git clone https://github.com/spring-projects/spring-data-r2dbc
cd spring-data-r2dbc
./mvnw clean install
```
 
#### This way we include the following dependency into our sample

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-r2dbc</artifactId>
    <version>1.0.0.BUILD-SNAPSHOT</version>
</dependency>
``` 

#### We need a PostgreSQL server, lets start one up with Docker:
 
```bash
docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=secret postgres:10.5
```

#### Check that is running

```bash
docker ps -a

baaaac8bd5f6  postgres:10.5  "docker-entrypoint.s…"   2 weeks ago   Up 18 minutes   0.0.0.0:5432->5432/tcp   postgres
```

#### Clone and build the sample

```bash
git clone https://github.com/altfatterz/spring-data-r2dbc-sample
cd spring-data-r2dbc-sample
./mvnw clean package
```

#### Run the sample

In IntelliJ run the `SpringDataR2dbcSampleApplication` or using the command line

```bash
java -jar target/spring-data-r2dbc-sample*.jar
```

#### Connect to the database:
```bash
docker exec -it postgres /bin/bash
psql -h localhost -U postgres
Password for user postgres: secret
```

#### View the tables
```bash
Password for user postgres:
psql (10.5)
Type "help" for help.

postgres=# \d

                List of relations
 Schema |       Name       |   Type   |  Owner
--------+------------------+----------+----------
 public | customers        | table    | postgres
 public | customers_id_seq | sequence | postgres

```

#### Get all customers

```bash
http :8080/customers

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

[
    {
        "firstName": "Walter",
        "id": 1,
        "lastName": "White"
    },
    {
        "firstName": "Jesse",
        "id": 2,
        "lastName": "Pinkman"
    },
    {
        "firstName": "Gus",
        "id": 3,
        "lastName": "Fring"
    },
    {
        "firstName": "Mike",
        "id": 4,
        "lastName": "Ehrmantraut"
    },
    {
        "firstName": "Saul",
        "id": 5,
        "lastName": "Goodman"
    },
    {
        "firstName": "Hank",
        "id": 6,
        "lastName": "Schrader"
    },
    {
        "firstName": "Skyler",
        "id": 7,
        "lastName": "White"
    }
]

```

#### Get a customer 

```bash
http :8080/customers/1
HTTP/1.1 200 OK
Content-Length: 48
Content-Type: application/json;charset=UTF-8

{
    "firstName": "Walter",
    "id": 1,
    "lastName": "White"
}
```

#### Search for customers

```bash
http :8080/customers/search\?name=white
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

[
    {
        "firstName": "Walter",
        "id": 1,
        "lastName": "White"
    },
    {
        "firstName": "Skyler",
        "id": 7,
        "lastName": "White"
    }
]
```
