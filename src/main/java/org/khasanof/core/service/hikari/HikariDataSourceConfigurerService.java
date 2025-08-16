package org.khasanof.core.service.hikari;

import com.zaxxer.hikari.HikariDataSource;
import org.khasanof.core.tenancy.multi.MsDataSourceProperties;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi
 * @since 8/16/2025 2:04 PM
 */
public interface HikariDataSourceConfigurerService {

    /**
     * Этот метод используется, чтобы заполнить дополнительные поля.
     *
     * @param hikariDataSource
     * @param properties
     */
    void configurer(HikariDataSource hikariDataSource, MsDataSourceProperties properties);
}
