package org.khasanof.core.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause.ProblemDetailWithCauseBuilder;
import org.khasanof.core.errors.BasicError;

import java.net.URI;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class BadRequestAlertException extends ErrorResponseException {

    private static final long serialVersionUID = 1L;

    private final String entityName;

    private final String errorKey;

    public BadRequestAlertException(BasicError basicError) {
        this(basicError.getMessage(), basicError.getEntityName(), basicError.getErrorKey(), basicError.httpStatus());
    }

    public BadRequestAlertException(BasicError basicError, String entityName) {
        this(basicError.getMessage(), entityName == null ? basicError.getEntityName() : entityName, basicError.getErrorKey(), basicError.httpStatus());
    }

    public BadRequestAlertException(String defaultMessage, BasicError basicError) {
        this(defaultMessage == null ? basicError.getMessage() : defaultMessage, basicError.getEntityName(), basicError.getErrorKey(), basicError.httpStatus());
    }

    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        this(defaultMessage, entityName, errorKey, HttpStatus.BAD_REQUEST);
    }

    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey, HttpStatus httpStatus) {
        super(httpStatus,
                ProblemDetailWithCauseBuilder
                        .instance()
                        .withStatus(httpStatus.value())
                        .withType(ErrorConstants.DEFAULT_TYPE)
                        .withTitle(defaultMessage)
                        .withProperty(ErrorConstants.DEFAULT_MESSAGE, defaultMessage)
                        .withProperty(ErrorConstants.ERROR_KEY, errorKey)
                        .withProperty(ErrorConstants.ENTITY_NAME, entityName)
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
                ProblemDetailWithCauseBuilder
                        .instance()
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
