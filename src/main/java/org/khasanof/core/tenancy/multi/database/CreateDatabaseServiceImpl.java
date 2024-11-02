package org.khasanof.core.tenancy.multi.database;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;

import static org.khasanof.core.tenancy.multi.database.CreateDatabaseConstants.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.database
 * @since 11/2/2024 3:55 PM
 */
public class CreateDatabaseServiceImpl implements CreateDatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public CreateDatabaseServiceImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *
     * @param databaseName
     */
    @Override
    public void create(String databaseName) {
        try {
            tryCreate(databaseName);
        } catch (Exception e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }

    /**
     *
     * @param databaseName
     */
    private void tryCreate(String databaseName) {
        Boolean databaseExists = jdbcTemplate.query(CHECK_QUERY, new Object[]{databaseName}, ResultSet::next);
        if (Boolean.TRUE.equals(databaseExists)) {
            System.out.println("Database already exists: " + databaseName);
            return;
        }

        String createQuery = CREATE_DATABASE + databaseName;
        jdbcTemplate.execute(createQuery);
        System.out.println("Database successfully created: " + databaseName);
    }
}
