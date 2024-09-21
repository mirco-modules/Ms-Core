package org.khasanof.core.result;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 9/14/2024 11:11 AM
 */
public abstract class SimpleResults {

    /**
     *
     */
    public static final BasicResult SUCCESS = new BasicResult(true, "SUCCESS");

    /**
     *
     */
    public static final BasicResult FAILED = new BasicResult(false, "FAILED");
}
