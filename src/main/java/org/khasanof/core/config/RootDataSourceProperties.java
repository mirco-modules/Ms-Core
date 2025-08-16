package org.khasanof.core.config;

import lombok.Data;
import org.khasanof.core.tenancy.multi.MsDataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nurislom
 * @see org.khasanof.core.config
 * @since 4/27/2025 8:35 PM
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class RootDataSourceProperties {

    private DataSourceProperties common;
    private DataSourceProperties tenant;

    private MsDataSourceProperties commonProperties;
    private MsDataSourceProperties tenantProperties;
}
