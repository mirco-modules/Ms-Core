package org.khasanof.core.tenancy.core;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.khasanof.core.tenancy.TenancyConfigurer;

import static org.khasanof.core.tenancy.core.constants.TenancyConstants.TENANCY_CONFIGURER;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/9/2024 4:48 PM
 */
public abstract class AbstractTenancyConfigurer {

    protected TenancyConfigurer tenancyConfigurer;

    /**
     * Initializes the {@link TenancyConfigurer} by retrieving it from the application context.
     * If the configured bean named {@code TENANCY_CONFIGURER} exists, it is assigned to
     * the {@code tenancyConfigurer} field; otherwise, this method leaves the field unset.
     *
     * @param context the condition context used to retrieve the application bean factory
     */
    protected void initializeTenancyConfigurer(ConditionContext context) {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        if (listableBeanFactory == null) {
            return;
        }
        if (listableBeanFactory.containsBean(TENANCY_CONFIGURER)) {
            tenancyConfigurer = listableBeanFactory.getBean(TENANCY_CONFIGURER, TenancyConfigurer.class);
        }
    }
}
