package org.khasanof.core.constants;

/**
 * @author Nurislom
 * @see org.khasanof.core.constants
 * @since 1/7/2025 6:27 PM
 */
public abstract class NumberProcessorConstants {

    /**
     *
     */
    public static final String GET_MAX_CODE_QUERY = "SELECT MAX(e.%s) FROM %s e";

    /**
     *
     */
    public static final String GET_ENTITY_COUNT = "SELECT COUNT(e) FROM %s e";

    /**
     *
     */
    public static final String CODE_FIELD = "code";
}
