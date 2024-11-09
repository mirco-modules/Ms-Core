package org.khasanof.core.tenancy.core.model;

import lombok.*;
import org.khasanof.core.enumeration.RepositoryType;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.model
 * @since 11/8/2024 4:23 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

    private Long tenantId;
    private RepositoryType repositoryType;

    public Tenant(Long tenantId) {
        this.tenantId = tenantId;
        this.repositoryType = RepositoryType.DEFAULT;
    }
}
