package org.khasanof.core.tenancy.multi.provider;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.provider
 * @since 11/2/2024 3:38 PM
 */
@Slf4j
public class MultiTenantConnectorProviderImpl implements MultiTenantConnectionProvider<Long> {

    private final DataSource dataSource;

    public MultiTenantConnectorProviderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     *
     * @param connection The JDBC connection to release
     *
     * @throws SQLException
     */
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    /**
     *
     * @param tenantIdentifier The identifier of the tenant for which to get a connection
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection(Long tenantIdentifier) throws SQLException {
        log.info("Get connection for tenant {}", tenantIdentifier);
        return dataSource.getConnection();
    }

    /**
     *
     * @param tenantIdentifier The identifier of the tenant.
     *
     * @param connection The JDBC connection to release
     * @throws SQLException
     */
    @Override
    public void releaseConnection(Long tenantIdentifier, Connection connection) throws SQLException {
        log.info("Release connection for tenant {}", tenantIdentifier);
        releaseAnyConnection(connection);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    /**
     *
     * @param unwrapType The type to check.
     *
     * @return
     */
    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return false;
    }

    /**
     *
     * @param unwrapType The java type as which to unwrap this instance.
     *
     * @return
     * @param <T>
     */
    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
