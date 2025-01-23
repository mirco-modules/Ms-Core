package org.khasanof.core.annotation;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.annotation
 * @since 1/4/2025 11:49 AM
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScanPackages {

    /**
     *
     * @return
     */
    String[] value();
}
