package org.khasanof.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Nurislom
 * @see org.khasanof.core.user.config
 * @since 11/25/2024 5:56 PM
 */
@Configuration
@EntityScan(basePackages = {JpaRepositoriesConfiguration.PACKAGE})
@EnableJpaRepositories(basePackages = {JpaRepositoriesConfiguration.PACKAGE})
public class JpaRepositoriesConfiguration {

    public static final String PACKAGE = "org.khasanof.core";
}
