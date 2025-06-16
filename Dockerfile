FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# 1. Actualizar Alpine y paquetes SSL
RUN apk update && apk upgrade && \
    apk add --no-cache ca-certificates openssl postgresql-client

# 2. Descargar y configurar certificado raíz ISRG
RUN wget -O /usr/local/share/ca-certificates/ISRG_Root_X1.crt https://letsencrypt.org/certs/isrgrootx1.pem && \
    update-ca-certificates

# 3. Configurar almacén de confianza de Java
RUN keytool -importcert -noprompt \
    -keystore $JAVA_HOME/lib/security/cacerts \
    -storepass changeit \
    -file /usr/local/share/ca-certificates/ISRG_Root_X1.crt \
    -alias isrg_root_x1

# 4. Copiar aplicación
COPY --from=build /app/target/inmobiliaria-*.jar app.jar

# 5. Script de entrada simplificado
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]