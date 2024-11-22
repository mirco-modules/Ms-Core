package org.khasanof.core.client.util;

/**
 * @author Nurislom
 * @see org.khasanof.core.client.util
 * @since 8/24/2024 11:58 PM
 */
public class ServiceNameUtil {

    /**
     *
     * @param url
     * @return
     */
    public static String checkStartWithHttp(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return "http://" + url;
    }
}
