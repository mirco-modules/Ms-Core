package org.khasanof.core.annotation;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.annotation
 * @since 1/7/2025 2:06 PM
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SynonymName {

    /**
     * Entity synonym name
     */
    String value();
}
