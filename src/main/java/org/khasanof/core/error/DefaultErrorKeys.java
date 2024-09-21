package org.khasanof.core.error;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 9/5/2024 6:23 PM
 */
public enum DefaultErrorKeys implements BasicError {

    NOT_FOUND("Entity not found", "notFound"),
    INVALID_PARAMETER("invalidParameter"),
    ID_NULL("Invalid ID", "idNull"),
    ID_INVALID("Invalid ID", "idInvalid"),
    ID_EXISTS("A new entity cannot already have an ID", "idExists");

    private String message;
    private String entityName;
    private final String errorKey;

    DefaultErrorKeys(String errorKey) {
        this.errorKey = errorKey;
    }

    DefaultErrorKeys(String message, String errorKey) {
        this.message = message;
        this.errorKey = errorKey;
    }

    DefaultErrorKeys(String message, String entityName, String errorKey) {
        this.message = message;
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    /**
     *
     * @return
     */
    @Override
    public String getErrorKey() {
        return this.errorKey;
    }

    /**
     *
     * @return
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     *
     * @return
     */
    @Override
    public String getEntityName() {
        return this.entityName;
    }
}
