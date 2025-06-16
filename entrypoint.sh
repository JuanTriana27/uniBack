#!/bin/sh

# 1. Configuración mínima para conexión
export PGSSLMODE=require

# 2. Espera activa simplificada
echo "Validando conexión a PostgreSQL ($DB_HOST:$DB_PORT)..."
for i in {1..10}; do
  if PGPASSWORD="$DB_PASSWORD" psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" -c "SELECT 1" >/dev/null 2>&1; then
    echo "¡Conexión exitosa a PostgreSQL!"
    break
  else
    echo "Intento $i/10 fallido - reintentando en 3 segundos..."
    sleep 3
  fi
done

# 3. Iniciar aplicación con TLS 1.2 forzado
echo "Iniciando aplicación Java con TLS 1.2..."
exec java \
  -Djdk.tls.client.protocols=TLSv1.2 \
  -Djavax.net.ssl.trustStore=$JAVA_HOME/lib/security/cacerts \
  -Djavax.net.ssl.trustStorePassword=changeit \
  -jar app.jar