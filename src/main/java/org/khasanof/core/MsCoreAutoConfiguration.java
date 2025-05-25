package org.khasanof.core;

import org.khasanof.core.config.RootDataSourceProperties;
import org.khasanof.core.config.RootProperties;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 9/21/2024 6:19 PM
 */
@ComponentScan(
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {TypeExcludeFilter.class}
        ), @ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {AutoConfigurationExcludeFilter.class}
        )}
)
@EnableConfigurationProperties({RootProperties.class, RootDataSourceProperties.class})
public class MsCoreAutoConfiguration {
}
