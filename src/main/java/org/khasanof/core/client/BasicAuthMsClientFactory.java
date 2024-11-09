package org.khasanof.core.client;

import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.khasanof.core.client.configurer.BaseMsClientConfigurer;
import org.khasanof.core.security.SecurityConstants;

/**
 * @author Nurislom
 * @see org.khasanof.core.client
 * @since 11/9/2024 10:47 PM
 */
public class BasicAuthMsClientFactory extends AbstractMsClientFactory {

    public BasicAuthMsClientFactory(Client client, BaseMsClientConfigurer baseMsClientConfigurer) {
        super(client, baseMsClientConfigurer);
    }

    /**
     *
     * @param clientClass the class of the Feign client to create.
     * @param url the base URL for the Feign client.
     * @return
     * @param <T>
     */
    @Override
    public <T> T create(Class<T> clientClass, String url) {
        Feign.Builder builder = feignBuilder(clientClass);
        return build(builder, clientClass, url);
    }

    /**
     *
     * @param clientClass
     * @return
     */
    @Override
    protected Feign.Builder feignBuilder(Class<?> clientClass) {
        return super.feignBuilder(clientClass)
                .requestInterceptor(new BasicAuthRequestInterceptor(SecurityConstants.ADMIN_USER, SecurityConstants.ADMIN_PASSWORD));
    }
}
