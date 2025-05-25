package org.khasanof.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import static org.khasanof.core.security.SecurityConstants.SECRET_KEY;

/**
 * @author Nurislom
 * @see org.khasanof.core.security
 * @since 11/9/2024 5:22 PM
 */
//@Configuration
public class PasswordEncoderConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(SECRET_KEY);
    }
}
