package org.khasanof.core.constants;

/**
 * @author Nurislom
 * @see org.khasanof.core.constants
 * @since 11/8/2024 11:21 AM
 */
public abstract class Constants {

    /**
     *
     */
    public static final String BEARER_PREFIX = "Bearer ";

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";
}
