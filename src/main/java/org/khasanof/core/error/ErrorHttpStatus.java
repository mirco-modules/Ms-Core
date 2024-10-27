package org.khasanof.core.error;

import org.springframework.http.HttpStatus;

/**
 * @author Nurislom
 * @see org.khasanof.core.error
 * @since 10/27/2024 6:19 PM
 */
public interface ErrorHttpStatus {

    /**
     *
     * @return
     */
    HttpStatus getHttpStatus();
}
