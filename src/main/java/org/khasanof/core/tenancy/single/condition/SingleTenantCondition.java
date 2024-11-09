package org.khasanof.core.tenancy.single.condition;

import org.khasanof.core.enumeration.TenancyType;
import org.khasanof.core.tenancy.core.condition.AbstractTenantCondition;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.single.condition
 * @since 11/2/2024 3:21 PM
 */
public class SingleTenantCondition extends AbstractTenantCondition {

    /**
     *
     * @return
     */
    @Override
    public TenancyType getTenancyType() {
        return TenancyType.SINGLE;
    }
}
