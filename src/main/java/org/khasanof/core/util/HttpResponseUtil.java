package org.khasanof.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.khasanof.core.result.BasicResultData;

/**
 * @author Nurislom
 * @see org.khasanof.core.util
 * @since 8/9/2024 3:54 PM
 */
@Slf4j
public abstract class HttpResponseUtil {

    /**
     *
     * @param response
     * @return
     * @param <T>
     */
    public static <T> BasicResultData<T> validateAndReturnResult(ResponseEntity<T> response) {
        if (response == null) {
            log.warn("response entity is null");
            return new BasicResultData<>(false, null, "response is null");
        }

        if (response.getBody() == null) {
            log.warn("response entity body is null");
            return new BasicResultData<>(false, null, response.getStatusCode().toString());
        }

        if (response.getStatusCode().isError()) {
            log.warn("response http status is NOT success: {}", response.getStatusCode());
            return new BasicResultData<>(false, response.getBody(), String.valueOf(response.getBody()));
        }

        log.debug("Response entity status code is OK : {}", response.getBody());
        return new BasicResultData<>(response.getBody());
    }
}
