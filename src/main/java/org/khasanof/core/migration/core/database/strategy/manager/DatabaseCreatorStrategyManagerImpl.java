package org.khasanof.core.migration.core.database.strategy.manager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.khasanof.core.migration.core.database.strategy.DatabaseCreatorStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy.manager
 * @since 11/11/2024 12:23 PM
 */
@Service
public class DatabaseCreatorStrategyManagerImpl implements DatabaseCreatorStrategyManager, InitializingBean {

    private final ApplicationContext applicationContext;
    private final List<DatabaseCreatorStrategy> strategies = new CopyOnWriteArrayList<>();

    public DatabaseCreatorStrategyManagerImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @param jdbcUrl
     * @return
     */
    @Override
    public Optional<DatabaseCreatorStrategy> getStrategy(String jdbcUrl) {
        return strategies.stream()
                .filter(databaseCreatorStrategy -> databaseCreatorStrategy.isSupport(jdbcUrl))
                .findFirst();
    }

    /**
     *
     */
    @Override
    public void afterPropertiesSet() {
        this.strategies.addAll(getDatabaseCreatorStrategies());
    }

    /**
     *
     * @return
     */
    private Collection<DatabaseCreatorStrategy> getDatabaseCreatorStrategies() {
        return applicationContext.getBeansOfType(DatabaseCreatorStrategy.class)
                .values();
    }
}
