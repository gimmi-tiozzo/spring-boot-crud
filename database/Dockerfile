FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/

EXPOSE 3306