package org.khasanof.core.util;

/**
 * @author Nurislom
 * @see org.khasanof.core.util
 * @since 10/10/2024 2:16 PM
 */
public abstract class HttpHeaderUtil {

    /**
     *
     * @param token
     * @return
     */
    public static String addBearerPrefix(String token) {
        return "Bearer " + token;
    }
}
