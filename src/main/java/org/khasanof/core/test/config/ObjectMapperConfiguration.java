package org.khasanof.core.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.test.config
 * @since 9/21/2024 6:32 PM
 */
@Configuration
public class ObjectMapperConfiguration {

    /**
     *
     * @return {@link ObjectMapper} link
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper() {{
            registerModule(new Jdk8Module());
            registerModule(new JavaTimeModule());
        }};
    }
}
