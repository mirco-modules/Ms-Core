package org.khasanof.core.error;

import org.springframework.http.HttpStatus;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 9/5/2024 6:32 PM
 */
public interface BasicError extends ErrorKey, ErrorMessage, ErrorEntityName, ErrorHttpStatus {

    /**
     *
     * @return
     */
    @Override
    default HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
