package org.khasanof.core.exception;

import org.khasanof.core.errors.BasicError;
import org.springframework.web.ErrorResponseException;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause;

import java.io.Serial;

/**
 * Custom exception class that extends {@link ErrorResponseException} to handle basic error alerts with additional information.
 *
 * @author Nurislom
 * @see org.khasanof.core.exception
 * @since 10/27/2024 6:26 PM
 */
public class MmBasicAlertException extends ErrorResponseException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final BasicError basicError;

    public MmBasicAlertException(BasicError basicError) {
        super(
                basicError.httpStatus(),
                ProblemDetailWithCause.ProblemDetailWithCauseBuilder
                        .instance()
                        .withStatus(basicError.httpStatus().value())
                        .withTitle(basicError.getMessage())
                        .withProperty(ErrorConstants.MESSAGE, basicError.getMessage())
                        .withProperty(ErrorConstants.ERROR_KEY, basicError.getErrorKey())
                        .withProperty(ErrorConstants.ENTITY_NAME, basicError.getEntityName())
                        .build(),
                null
        );
        this.basicError = basicError;
    }

    /**
     * Retrieves the {@code BasicError} object associated with this exception.
     *
     * @return the {@code BasicError} instance containing details about the error.
     */
    public BasicError getBasicError() {
        return this.basicError;
    }

    /**
     * Retrieves the error key associated with this exception.
     *
     * @return a {@code String} representing the error key if {@code BasicError} is not null,
     *         otherwise, {@code null}.
     */
    public String getErrorKey() {
        return this.basicError != null ? this.basicError.getErrorKey() : null;
    }

    /**
     * Retrieves the entity name associated with this exception.
     *
     * @return a {@code String} representing the entity name if {@code BasicError} is not null,
     *         otherwise, {@code null}.
     */
    public String getEntityName() {
        return this.basicError != null ? this.basicError.getEntityName() : null;
    }

    /**
     * Retrieves the error message associated with this exception.
     *
     * @return a {@code String} representing the error message if {@code BasicError} is not null,
     *         otherwise, {@code null}.
     */
    public String getMessage() {
        return this.basicError != null ? this.basicError.getMessage() : null;
    }
}
