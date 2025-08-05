#!/bin/bash
set -e

# Функция для создания БД, если она не существует
create_db_if_not_exists() {
    local dbname="$1"
    echo "Checking if database $dbname exists..."

    if ! psql -U "$POSTGRES_USER" -lqt | cut -d \| -f 1 | grep -qw "$dbname"; then
        echo "Creating database $dbname"
        psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
            CREATE DATABASE "$dbname";
            GRANT ALL PRIVILEGES ON DATABASE "$dbname" TO "$POSTGRES_USER";
EOSQL
        echo "Database $dbname created"
    else
        echo "Database $dbname already exists, skipping creation"
    fi
}

# Добавление расширений в БД
add_extensions_to_db() {
    local dbname="$1"
    echo "Adding extensions to $dbname"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d "$dbname" <<-EOSQL
        CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
EOSQL
}

# Главный скрипт
main() {
    # Список БД из переменной окружения
    IFS=',' read -ra databases <<< "$POSTGRES_MULTIPLE_DATABASES"

    for db in "${databases[@]}"; do
        create_db_if_not_exists "$db"
        add_extensions_to_db "$db"
    done
}

# Запуск главной функции
main
