FROM postgres:15
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=tasksphere

COPY src/main/resources/db.migration /docker-entrypoint-initdb.d/
