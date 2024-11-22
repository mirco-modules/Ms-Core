package org.khasanof.core.client;

import feign.Client;
import org.khasanof.core.client.configurer.AbstarctBaseMsClientConfigurer;
import org.khasanof.core.client.configurer.BaseMsClientConfigurer;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.AbstractFormWriter;
import org.springframework.cloud.openfeign.support.FeignEncoderProperties;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.client
 * @since 11/9/2024 10:58 PM
 */
@Configuration
public class BaseMsClientConfiguration {

    private final FeignEncoderProperties encoderProperties;
    private final ObjectProvider<AbstractFormWriter> formWriterProvider;
    private final ObjectFactory<HttpMessageConverters> messageConverters;
    private final ObjectProvider<HttpMessageConverterCustomizer> customizers;

    public BaseMsClientConfiguration(FeignEncoderProperties encoderProperties,
                                     ObjectProvider<AbstractFormWriter> formWriterProvider,
                                     ObjectFactory<HttpMessageConverters> messageConverters,
                                     ObjectProvider<HttpMessageConverterCustomizer> customizers) {

        this.encoderProperties = encoderProperties;
        this.formWriterProvider = formWriterProvider;
        this.messageConverters = messageConverters;
        this.customizers = customizers;
    }

    /**
     *
     * @param client
     * @return
     */
    @Bean
    public BaseMsClientFactory baseMsClientFactory(Client client) {
        return new BasicAuthMsClientFactory(client, baseMsClientConfigurer());
    }

    /**
     *
     * @return
     */
    BaseMsClientConfigurer baseMsClientConfigurer() {
        return new AbstarctBaseMsClientConfigurer(encoderProperties, formWriterProvider, messageConverters, customizers) {};
    }
}
