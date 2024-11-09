package org.khasanof.core.client;

import feign.Client;
import feign.Feign;
import feign.slf4j.Slf4jLogger;
import org.khasanof.core.client.configurer.BaseMsClientConfigurer;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import static org.khasanof.core.client.util.ServiceNameUtil.checkStartWithHttp;

/**
 * @author Nurislom
 * @see org.khasanof.core.client
 * @since 8/24/2024 7:59 PM
 */
public abstract class AbstractMsClientFactory implements BaseMsClientFactory {

    protected final Client client;
    protected final BaseMsClientConfigurer baseMsClientConfigurer;

    protected AbstractMsClientFactory(Client client, BaseMsClientConfigurer baseMsClientConfigurer) {
        this.client = client;
        this.baseMsClientConfigurer = baseMsClientConfigurer;
    }

    /**
     *
     * @param clientClass
     * @return
     */
    protected Feign.Builder feignBuilder(Class<?> clientClass) {
        return Feign.builder()
                .client(client)
                .logger(new Slf4jLogger(clientClass))
                .contract(new SpringMvcContract())
                .encoder(baseMsClientConfigurer.getEncoder())
                .decoder(baseMsClientConfigurer.getDecoder());
    }

    /**
     *
     * @param builder
     * @param clientClass
     * @param url
     * @return
     * @param <T>
     */
    protected <T> T build(Feign.Builder builder, Class<T> clientClass, String url) {
        return builder.target(clientClass, checkStartWithHttp(url));
    }
}
