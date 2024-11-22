package org.khasanof.core.migration.core.liquibase;

import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import tech.jhipster.config.liquibase.AsyncSpringLiquibase;
import org.khasanof.core.migration.core.database.DatabaseCreatorService;

import java.util.concurrent.Executor;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.liquibase
 * @since 11/11/2024 11:15 AM
 */
@Slf4j
public class AsyncSpringLiquibaseV2 extends AsyncSpringLiquibase {

    private final Environment env;
    private final DataSourceProperties dataSourceProperties;
    private final DatabaseCreatorService databaseCreatorService;

    public AsyncSpringLiquibaseV2(Executor executor, Environment env, DataSourceProperties dataSourceProperties, DatabaseCreatorService databaseCreatorService) {
        super(executor, env);
        this.env = env;
        this.dataSourceProperties = dataSourceProperties;
        this.databaseCreatorService = databaseCreatorService;
    }

    /**
     *
     * @throws LiquibaseException
     */
    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        if (isLiquibaseDisabled()) {
            log.debug("Liquibase is disabled");
            return;
        }
        executeLiquibaseMigration();
    }

    /**
     *
     * @throws LiquibaseException
     */
    private void executeLiquibaseMigration() throws LiquibaseException {
        databaseCreatorService.createIfNotExist(dataSourceProperties);
        super.afterPropertiesSet();
    }

    /**
     *
     * @return
     */
    private boolean isLiquibaseDisabled() {
        return this.env.acceptsProfiles(Profiles.of("no-liquibase"));
    }
}
