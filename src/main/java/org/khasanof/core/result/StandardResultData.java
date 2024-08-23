package org.khasanof.core.result;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.result
 * @since 8/24/2024 1:19 AM
 */
public class StandardResultData<DATA> extends BasicResultData<DATA> {

    private String errorKey;

    public StandardResultData(DATA data) {
        this(true, data, null, null);
    }

    public StandardResultData(String errorKey) {
        this(false, null, null, errorKey);
    }

    public StandardResultData(DATA data, String errorKey) {
        this(false, data, null, errorKey);
    }

    public StandardResultData(DATA data, String errorKey, String responseText) {
        this(false, data, responseText, errorKey);
    }

    public StandardResultData(boolean success, DATA data, String responseText) {
        this(success, data, responseText, null);
    }

    public StandardResultData(boolean success, DATA data, String responseText, String errorKey) {
        super(success, data, responseText);
        this.errorKey = errorKey;
    }

    public StandardResultData<DATA> withErrorKey(String errorKey) {
        this.errorKey = errorKey;
        return this;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        StandardResultData<?> that = (StandardResultData<?>) object;
        return Objects.equals(errorKey, that.errorKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errorKey);
    }

    @Override
    public String toString() {
        return "StandardResultData{" +
                "errorKey='" + errorKey + '\'' +
                '}';
    }
}
