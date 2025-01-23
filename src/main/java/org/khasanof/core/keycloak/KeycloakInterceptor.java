package org.khasanof.core.client.keycloak;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.khasanof.core.keycloak.KeycloakConfigs;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.khasanof.core.constants.Constants;
import org.khasanof.core.service.dto.OAuthIdpTokenResponseDTO;

import java.net.URI;
import java.util.Optional;

import static org.khasanof.core.util.BaseUtils.concat;

/**
 * @author Nurislom
 * @see org.khasanof.core.client.keycloak
 * @since 5/7/2024 2:54 PM
 */
@SuppressWarnings("rawtypes")
public class KeycloakInterceptor implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    public static final String CONNECT_TOKEN = "/protocol/openid-connect/token";

    private final RestTemplate restTemplate;
    private final KeycloakConfigs keycloakConfigs;

    public KeycloakInterceptor(RestTemplateBuilder restTemplateBuilder, KeycloakConfigs keycloakConfigs) {
        this.restTemplate = restTemplate(restTemplateBuilder);
        this.keycloakConfigs = keycloakConfigs;
    }

    /**
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Optional<String> accessToken = getAccessToken();
        accessToken.ifPresent(token -> requestTemplate.header(AUTHORIZATION, concat(token, Constants.BEARER_PREFIX)));
    }

    /**
     *
     * @param restTemplateBuilder
     * @return
     */
    private RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .additionalMessageConverters(new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter())
            .errorHandler(new OAuth2ErrorResponseErrorHandler())
            .build();
    }

    /**
     *
     * @return
     */
    private Optional<String> getAccessToken() {
        return Optional
            .of(restTemplate.exchange(getRequestEntity(), OAuthIdpTokenResponseDTO.class))
            .map(HttpEntity::getBody)
            .map(OAuthIdpTokenResponseDTO::getAccessToken);
    }

    /**
     *
     * @return
     */
    private RequestEntity getRequestEntity() {
        return RequestEntity
            .post(URI.create(keycloakConfigs.getIssuerUri() + CONNECT_TOKEN))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(formParameters());
    }

    /**
     *
     * @return
     */
    private MultiValueMap<String, String> formParameters() {
        MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();

        formParameters.add(OAuth2ParameterNames.CLIENT_ID, keycloakConfigs.getClientId());
        formParameters.add(OAuth2ParameterNames.CLIENT_SECRET, keycloakConfigs.getClientSecret());

        formParameters.add(OAuth2ParameterNames.SCOPE, keycloakConfigs.getScope());
        formParameters.add(OAuth2ParameterNames.USERNAME, keycloakConfigs.getUsername());
        formParameters.add(OAuth2ParameterNames.PASSWORD, keycloakConfigs.getPassword());
        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, keycloakConfigs.getGrantType());

        return formParameters;
    }
}
