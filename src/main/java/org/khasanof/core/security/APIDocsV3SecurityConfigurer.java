package org.khasanof.core.security;

import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.security
 * @since 8/10/2025 7:29 AM
 */
@Component
public class APIDocsV3SecurityConfigurer {

    public static final String API_DOCS = "api-docs";

    private final Environment environment;

    public APIDocsV3SecurityConfigurer(Environment environment) {
        this.environment = environment;
    }

    /**
     * @param http
     * @param mvc
     */
    public void configurer(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        if (!profiles.contains(API_DOCS)) {
            blockApiDocs(http, mvc);
            return;
        }
        permitApiDocs(http, mvc);
    }

    /**
     * @param http
     * @param mvc
     * @throws Exception
     */
    private void blockApiDocs(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(mvc.pattern("/v3/api-docs/**"))
                                .hasAuthority(AuthoritiesConstants.ADMIN)
        );
    }

    /**
     *
     * @param http
     * @param mvc
     * @throws Exception
     */
    private void permitApiDocs(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(mvc.pattern("/v3/api-docs/**"))
                                .permitAll()
                                .requestMatchers(mvc.pattern("/swagger-ui/**"))
                                .permitAll()
        );
    }
}
