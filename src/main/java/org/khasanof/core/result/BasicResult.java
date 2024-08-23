package org.khasanof.core.result;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 8/10/2024 10:55 AM
 */
public class BasicResult {

    private boolean success = false;
    private String responseText = "Default text";

    public BasicResult(boolean success, String responseText) {
        this.success = success;
        this.responseText = responseText;
    }

    public BasicResult() {
        this.withSuccess(false);
        this.withResponseText("Default text");
    }

    public BasicResult withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public BasicResult withResponseText(String responseText) {
        this.responseText = responseText;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getResponseText() {
        return this.responseText;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setResponseText(final String responseText) {
        this.responseText = responseText;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BasicResult that = (BasicResult) object;
        return success == that.success && Objects.equals(responseText, that.responseText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, responseText);
    }

    @Override
    public String toString() {
        return "BasicResult{" +
                "success=" + success +
                ", responseText='" + responseText + '\'' +
                '}';
    }
}
