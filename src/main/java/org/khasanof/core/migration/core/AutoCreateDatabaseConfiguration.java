package org.khasanof.core.migration.core;

import org.khasanof.core.config.RootDataSourceProperties;
import org.khasanof.core.migration.core.database.DatabaseCreatorService;
import org.khasanof.core.migration.core.database.DatabaseCreatorServiceImpl;
import org.khasanof.core.migration.core.database.strategy.manager.DatabaseCreatorStrategyManager;
import org.khasanof.core.migration.core.database.strategy.manager.DatabaseCreatorStrategyManagerImpl;
import org.khasanof.core.service.hikari.HikariDataSourceConfigurerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core
 * @since 11/22/2024 7:12 PM
 */
@Configuration
@ConditionalOnBean({AutoCreateDatabaseConfigurer.class})
public class AutoCreateDatabaseConfiguration {

    private final ApplicationContext applicationContext;

    public AutoCreateDatabaseConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @return
     */
    @Bean
    public DatabaseCreatorService databaseCreatorService(RootDataSourceProperties rootDataSourceProperties, HikariDataSourceConfigurerService hikariDataSourceConfigurerService) {
        return new DatabaseCreatorServiceImpl(rootDataSourceProperties, databaseCreatorStrategyManager(), hikariDataSourceConfigurerService);
    }

    /**
     *
     * @return
     */
    @Bean
    public DatabaseCreatorStrategyManager databaseCreatorStrategyManager() {
        return new DatabaseCreatorStrategyManagerImpl(applicationContext);
    }
}
