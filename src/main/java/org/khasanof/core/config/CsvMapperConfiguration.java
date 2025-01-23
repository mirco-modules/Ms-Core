package org.khasanof.core.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.config
 * @since 12/17/2024 5:09 PM
 */
@Configuration
public class CsvMapperConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public CsvMapper csvMapper() {
        return new CsvMapper();
    }
}
