package org.khasanof.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nurislom
 * @see org.khasanof.core.util
 * @since 12/11/2024 5:11 PM
 */
public final class ExtractClassNameUtil {

    private static final Pattern pattern = Pattern.compile("(?:^|\\.)([A-Za-z_$][A-Za-z\\d_$]*)$");

    /**
     *
     * @param className
     * @return
     */
    public static String extractClassName(String className) {
        Matcher matcher = pattern.matcher(className);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
