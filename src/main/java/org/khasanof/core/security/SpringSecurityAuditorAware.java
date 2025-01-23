package org.khasanof.core.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.khasanof.core.constants.Constants;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    /**
     *
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(JwtSecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM));
    }
}
