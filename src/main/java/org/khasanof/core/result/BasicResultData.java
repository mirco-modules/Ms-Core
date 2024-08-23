package org.khasanof.core.result;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 8/10/2024 10:58 AM
 */
public class BasicResultData<D> extends BasicResult {

    private D data;

    public BasicResultData(boolean success, D data, String responseText) {
        super(success, responseText);
        this.data = data;
    }

    public BasicResultData(D data) {
        super(true, "Success");
        this.data = data;
    }

    public BasicResultData<D> withData(D data) {
        this.data = data;
        return this;
    }

    public BasicResultData<D> withSuccess(boolean success) {
        super.withSuccess(success);
        return this;
    }

    public BasicResultData<D> withResponseText(String responseText) {
        super.withResponseText(responseText);
        return this;
    }

    public D getData() {
        return this.data;
    }

    public void setData(final D data) {
        this.data = data;
    }

    public String toString() {
        String var10000 = super.toString();
        return "BasicResultData(super=" + var10000 + ", data=" + this.getData() + ")";
    }
}
