FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Instalar herramientas de diagnóstico
RUN apk add --no-cache postgresql-client curl

# Copiar el JAR
COPY --from=build /app/target/inmobiliaria-*.jar app.jar

# Script de inicio con verificación de conexión
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]