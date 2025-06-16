#!/bin/sh

# Verificar conexión a PostgreSQL
echo "Testing database connection to $DB_HOST:$DB_PORT..."
until PGPASSWORD=$DB_PASSWORD psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" -c '\q'; do
  echo "PostgreSQL is unavailable - sleeping"
  sleep 2
done

echo "PostgreSQL is ready - starting application"
exec java -jar app.jar