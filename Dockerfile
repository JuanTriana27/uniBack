FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# 1. Instalar certificados SSL específicos para Render/Let's Encrypt
RUN apk add --no-cache ca-certificates openssl && \
    wget -O /usr/local/share/ca-certificates/Render_ISRG_Root_X1.crt https://letsencrypt.org/certs/isrgrootx1.pem && \
    update-ca-certificates

# 2. Configurar almacén de confianza de Java
RUN keytool -importcert -noprompt \
    -keystore $JAVA_HOME/lib/security/cacerts \
    -storepass changeit \
    -file /usr/local/share/ca-certificates/Render_ISRG_Root_X1.crt \
    -alias render_isrg_root_x1

# 3. Copiar aplicación
COPY --from=build /app/target/inmobiliaria-*.jar app.jar

# 4. Script de entrada mejorado
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]