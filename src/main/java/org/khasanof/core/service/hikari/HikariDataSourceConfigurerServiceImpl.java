package org.khasanof.core.service.hikari;

import com.zaxxer.hikari.HikariDataSource;
import org.khasanof.core.tenancy.multi.MsDataSourceProperties;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.hikari
 * @since 8/16/2025 2:04 PM
 */
@Service
public class HikariDataSourceConfigurerServiceImpl implements HikariDataSourceConfigurerService {

    /**
     * Этот метод используется, чтобы заполнить дополнительные поля.
     *
     * @param hikariDataSource
     * @param properties
     */
    @Override
    public void configurer(HikariDataSource hikariDataSource, MsDataSourceProperties properties) {
        hikariDataSource.setConnectionTimeout(properties.getConnectionTimeout());
        hikariDataSource.setIdleTimeout(properties.getIdleTimeout());
        hikariDataSource.setMaxLifetime(properties.getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(properties.getMaxPoolSize());
        hikariDataSource.setMinimumIdle(properties.getMinIdle());

        hikariDataSource.setAutoCommit(properties.isAutoCommit());
        hikariDataSource.setPoolName(properties.getPoolName());
    }
}
