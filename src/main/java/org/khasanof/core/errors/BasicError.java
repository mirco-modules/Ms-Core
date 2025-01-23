package org.khasanof.core.errors;

import org.springframework.http.HttpStatus;

/**
 * @author Nurislom
 * @see org.khasanof.core.errors
 * @since 9/5/2024 6:32 PM
 */
public interface BasicError extends ErrorKey, ErrorMessage, ErrorEntityName, ErrorHttpStatus {

    /**
     *
     * @return
     */
    @Override
    default HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
