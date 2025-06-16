#!/bin/sh

# Configurar SSL explícitamente para PostgreSQL
export PGSSLMODE="require"
export PGSSLROOTCERT="/usr/local/share/ca-certificates/Render_ISRG_Root_X1.crt"

# Espera activa con verificación SSL
echo "Verificando conexión SSL con PostgreSQL..."
timeout 10 openssl s_client -connect $DB_HOST:$DB_PORT -showcerts </dev/null

echo "Validando conexión a PostgreSQL..."
while ! PGPASSWORD=$DB_PASSWORD psql "host=$DB_HOST port=$DB_PORT user=$DB_USER dbname=$DB_NAME sslmode=require" -c "SELECT 1" >/dev/null 2>&1; do
  echo "PostgreSQL no está disponible - reintentando en 3 segundos..."
  sleep 3
done

echo "PostgreSQL está listo - iniciando aplicación..."
exec java -jar app.jar