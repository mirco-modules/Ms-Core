package org.khasanof.core.tenancy.multi;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi
 * @since 8/16/2025 12:45 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MsDataSourceProperties {

    // Properties changeable at runtime through the HikariConfigMXBean
    private volatile long connectionTimeout;
    private volatile long idleTimeout;
    private volatile long maxLifetime;
    private volatile int maxPoolSize;
    private volatile int minIdle;

    // Properties NOT changeable at runtime
    private boolean isAutoCommit;
    private String poolName;
}
