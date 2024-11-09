package org.khasanof.core.client.configurer;

import feign.codec.Decoder;
import feign.codec.Encoder;

/**
 * Interface for configuring microservice clients in a Feign-based environment.
 *
 * <p>This interface defines the methods required for configuring a microservice client,
 * including obtaining the service name, token strategy, and the Feign {@link Encoder} and
 * {@link Decoder} used for request and response handling.</p>
 *
 * @author Nurislom
 * @see org.khasanof.core.client.configurer
 * @since 8/24/2024 7:43 PM
 */
public interface BaseMsClientConfigurer {

    /**
     * Returns the Feign {@link Encoder} used for encoding requests.
     *
     * <p>The encoder is responsible for converting request objects into
     * the appropriate format (e.g., JSON, form data) before they are sent
     * to the microservice.</p>
     *
     * @return the Feign {@link Encoder} configured for this client.
     */
    Encoder getEncoder();

    /**
     * Returns the Feign {@link Decoder} used for decoding responses.
     *
     * <p>The decoder is responsible for converting responses from the
     * microservice into the appropriate object format expected by the client.</p>
     *
     * @return the Feign {@link Decoder} configured for this client.
     */
    Decoder getDecoder();
}
