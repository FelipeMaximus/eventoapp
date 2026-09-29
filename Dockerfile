# ETAPA 1 — Compilação
FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ETAPA 2 — Execução
FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /app/target/eventoapp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]