Start PostgreSQL
```bash
docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=secret postgres:10.5
```

Check that is running
```bash
docker ps -a

baaaac8bd5f6  postgres:10.5  "docker-entrypoint.s…"   2 weeks ago   Up 18 minutes   0.0.0.0:5432->5432/tcp   postgres
```

Connect to it without `psql` client:
```bash
docker exec -it postgres /bin/bash
psql -h localhost -U postgres
Password for user postgres: secret

```

View the tables
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

