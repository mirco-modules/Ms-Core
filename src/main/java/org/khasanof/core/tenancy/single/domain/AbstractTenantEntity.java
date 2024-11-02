package org.khasanof.core.tenancy.single.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.khasanof.core.tenancy.core.TenantContext;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 1:16 PM
 */
@Setter
@Getter
@MappedSuperclass
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name = "tenantId", type = Long.class))
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public abstract class AbstractTenantEntity implements Serializable {

    @Column(name = "tenant_id")
    private Long tenantId;

    /**
     *
     */
    @PrePersist
    public void setTenantId() {
        this.tenantId = TenantContext.getCurrentTenant();
    }
}
