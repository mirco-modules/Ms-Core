package org.khasanof.core.exception;

import org.khasanof.core.constants.ErrorConstants;
import org.khasanof.core.error.BasicError;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause.ProblemDetailWithCauseBuilder;

import java.io.Serial;
import java.net.URI;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class BadRequestAlertException extends ErrorResponseException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String entityName;

    private final String errorKey;

    public BadRequestAlertException(BasicError basicError) {
        this(basicError.getMessage(), basicError.getEntityName(), basicError.getErrorKey());
    }

    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        super(HttpStatus.BAD_REQUEST,
                ProblemDetailWithCauseBuilder
                        .instance()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withType(ErrorConstants.DEFAULT_TYPE)
                        .withTitle(defaultMessage)
                        .withProperty(ErrorConstants.DEFAULT_MESSAGE, defaultMessage)
                        .withProperty(ErrorConstants.ERROR_KEY, errorKey)
                        .withProperty("message", "error." + errorKey)
                        .withProperty("params", entityName)
                        .build(),
                null
        );
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(
            HttpStatus.BAD_REQUEST,
            ProblemDetailWithCauseBuilder.instance()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withType(type)
                .withTitle(defaultMessage)
                .withProperty("message", "error." + errorKey)
                .withProperty("params", entityName)
                .build(),
            null
        );
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public ProblemDetailWithCause getProblemDetailWithCause() {
        return (ProblemDetailWithCause) this.getBody();
    }
}
