package org.khasanof.core.tenancy.multi.condition;

import org.khasanof.core.domain.enumeration.TenancyType;
import org.khasanof.core.tenancy.core.condition.AbstractTenantCondition;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.condition
 * @since 11/2/2024 3:27 PM
 */
public class MultiTenantCondition extends AbstractTenantCondition {

    /**
     *
     * @return
     */
    @Override
    public TenancyType getTenancyType() {
        return TenancyType.MULTI;
    }
}
