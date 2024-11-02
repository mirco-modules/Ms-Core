package org.khasanof.core.tenancy.core.model;

import lombok.*;

import javax.sql.DataSource;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/2/2024 6:37 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SDataSource {

    private Boolean isMigrated;
    private DataSource dataSource;
}
