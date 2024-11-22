package org.khasanof.core.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Nurislom
 * @see org.khasanof.core.domain.enumeration
 * @since 11/11/2024 11:50 AM
 */
@Getter
@RequiredArgsConstructor
public enum DatabaseType {

    POSTGRESQL("postgresql", "jdbc:postgresql://[^/]+/([^?]+)", "postgres"),
    SQLSERVER("sqlserver", "jdbc:sqlserver://[^;]+;databaseName=([^;]+)", "master"),
    ORACLE("oracle", "jdbc:oracle:thin:@[^:]+:[^:]+:([^?]+)", "orcl"),
    MYSQL("mysql", "jdbc:mysql://[^/]+/([^?]+)", "information_schema"),
    H2("h2", "jdbc:h2:(?:file:.*/|tcp://[^/]+/|mem:)?([^;]+)", "test")
    ;

    private final String databaseName;
    private final String extractDatabaseNameRegex;
    private final String defaultDatabaseName;
}
