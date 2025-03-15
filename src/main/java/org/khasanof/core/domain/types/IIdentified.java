package org.khasanof.core.domain.types;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.core.domain.types
 * @since 12/4/2024 5:25 PM
 */
public interface IIdentified extends Serializable {

    /**
     *
     * @return
     */
    Long getId();

    /**
     *
     * @param id
     */
    void setId(Long id);
}
