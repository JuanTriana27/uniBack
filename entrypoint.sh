#!/bin/sh

# 1. Instalar herramientas de diagnóstico
apk add --no-cache bind-tools curl

# 2. Verificar resolución DNS
echo "Resolviendo DNS de $DB_HOST..."
nslookup $DB_HOST
dig $DB_HOST

# 3. Verificar conectividad de red
echo "Verificando conectividad a $DB_HOST:$DB_PORT..."
nc -zvw 5 $DB_HOST $DB_PORT
tcping $DB_HOST $DB_PORT -t 5

# 4. Verificar certificado SSL
echo "Inspeccionando certificado SSL..."
openssl s_client -connect $DB_HOST:$DB_PORT -showcerts </dev/null

# 5. Prueba de conexión PostgreSQL básica
echo "Probando conexión PostgreSQL sin SSL..."
PGPASSWORD="$DB_PASSWORD" psql -h $DB_HOST -p $DB_PORT -U $DB_USER -d $DB_NAME -c "SELECT 1" >/dev/null 2>&1

# 6. Prueba de conexión PostgreSQL con SSL
echo "Probando conexión PostgreSQL con SSL..."
PGPASSWORD="$DB_PASSWORD" psql "host=$DB_HOST port=$DB_PORT user=$DB_USER dbname=$DB_NAME sslmode=require" -c "SELECT 1"

# 7. Intentar conexión con diferentes versiones TLS
for version in tls1 tls1_1 tls1_2 tls1_3; do
  echo "Probando conexión con $version..."
  PGPASSWORD="$DB_PASSWORD" psql "host=$DB_HOST port=$DB_PORT user=$DB_USER dbname=$DB_NAME sslmode=require sslprotocol=$version" -c "SELECT 1" && break
done

# 8. Iniciar aplicación con TLS 1.2 forzado
echo "Iniciando aplicación Java con TLS 1.2..."
exec java \
  -Djdk.tls.client.protocols=TLSv1.2 \
  -Djavax.net.ssl.trustStore=$JAVA_HOME/lib/security/cacerts \
  -Djavax.net.ssl.trustStorePassword=changeit \
  -Djavax.net.debug=ssl \
  -jar app.jar