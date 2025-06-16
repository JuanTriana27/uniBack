FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Diagnóstico y certificados
RUN apk update && apk upgrade && \
    apk add --no-cache \
    ca-certificates \
    openssl \
    postgresql-client \
    bind-tools \
    curl \
    netcat-openbsd

# Instalar certificado Let's Encrypt
RUN wget -O /usr/local/share/ca-certificates/ISRG_Root_X1.crt https://letsencrypt.org/certs/isrgrootx1.pem && \
    update-ca-certificates && \
    keytool -importcert -noprompt \
    -keystore $JAVA_HOME/lib/security/cacerts \
    -storepass changeit \
    -file /usr/local/share/ca-certificates/ISRG_Root_X1.crt \
    -alias isrg_root_x1

COPY --from=build /app/target/inmobiliaria-0.0.1-SNAPSHOT.jar app.jar
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]