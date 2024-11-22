package org.khasanof.core.client;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 8/24/2024 7:13 PM
 */
public interface BaseMsClientFactory {

    /**
     * Creates an instance of the specified client class using a configured Feign builder with a custom URL.
     *
     * <p>This method configures a {@link feign.Feign.Builder} specific to the provided client class and
     * uses it to create an instance of the client, using the specified base URL.</p>
     *
     * @param clientClass the class of the Feign client to create.
     * @param url the base URL for the Feign client.
     * @param <T> the type of the Feign client.
     * @return an instance of the specified Feign client class configured with the provided URL.
     */
    <T> T create(Class<T> clientClass, String url);
}
