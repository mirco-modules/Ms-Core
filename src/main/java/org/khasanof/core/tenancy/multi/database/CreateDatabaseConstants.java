package org.khasanof.core.tenancy.multi.database;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.database
 * @since 11/2/2024 6:35 PM
 */
public abstract class CreateDatabaseConstants {

    public static final String CREATE_DATABASE = "CREATE DATABASE ";
    public static final String CHECK_QUERY = "SELECT 1 FROM pg_database WHERE datname = ?";
}
