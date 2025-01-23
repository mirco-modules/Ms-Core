package org.khasanof.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nurislom
 * @see org.khasanof.core.config
 * @since 12/6/2024 11:54 AM
 */
@Data
@ConfigurationProperties(prefix = "root")
public class RootProperties {

    /**
     *
     */
    private Boolean enabledProcessors = Boolean.FALSE;
}
