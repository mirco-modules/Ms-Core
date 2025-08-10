package org.khasanof.core.tenancy.multi.helper;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.khasanof.core.tenancy.multi.helper.DatabaseNameConstants.PROPERTY_KEY;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.helper
 * @since 11/2/2024 6:33 PM
 */
@Component
public class DatabaseNameHelper {

    private final Environment environment;

    public DatabaseNameHelper(Environment environment) {
        this.environment = environment;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    public String getDatabaseName(Long tenantIdentifier) {
        String applicationName = environment.getProperty(PROPERTY_KEY);
        if (applicationName == null) {
            applicationName = environment.getProperty("spring.application.name");
        }
        if (applicationName == null) {
            applicationName = "default";
        }
        return (applicationName + tenantIdentifier).toLowerCase();
    }
}
