package org.khasanof.core.util;

/**
 * @author Nurislom
 * @see org.khasanof.core.util
 * @since 11/8/2024 11:20 AM
 */
public abstract class BaseUtils {

    /**
     *
     * @param target
     * @param concatString
     * @return
     */
    public static String concat(String target, String concatString) {
        return concatString.concat(target);
    }

    /**
     *
     * @param className
     * @return
     */
    public static Class<?> classLoader(String className, ClassLoader classLoader) {
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
