package org.khasanof.core.result;

import org.khasanof.core.error.ErrorKey;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 9/5/2024 6:20 PM
 */
public class ErrorKeyResult<DATA> extends StandardResultData<DATA> {

    public ErrorKeyResult(DATA data) {
        super(data);
    }

    public ErrorKeyResult(ErrorKey errorKey) {
        super(null, errorKey.getErrorKey());
    }
}
