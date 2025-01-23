package org.khasanof.core.keycloak;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.keycloak
 * @since 11/8/2024 11:16 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakConfigs {

    private String clientId;
    private String issuerUri;
    private String clientSecret;

    private String scope;
    private String username;
    private String password;
    private String grantType;
}
