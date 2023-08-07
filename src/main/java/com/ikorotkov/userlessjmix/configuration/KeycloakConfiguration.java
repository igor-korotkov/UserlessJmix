package com.ikorotkov.userlessjmix.configuration;

import io.jmix.oidc.userinfo.JmixOidcUserService;
import io.jmix.security.SecurityConfigurers;
import io.jmix.security.StandardSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class KeycloakConfiguration extends StandardSecurityConfiguration {

    protected final ClientRegistrationRepository clientRegistrationRepository;

    protected final JmixOidcUserService oidcUserService;

    public KeycloakConfiguration(ClientRegistrationRepository clientRegistrationRepository, JmixOidcUserService oidcUserService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oidcUserService = oidcUserService;
    }

    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                .userInfoEndpoint()
                .oidcUserService(oidcUserService)
                .and()
                .and()
                .logout()
                .logoutSuccessHandler(oidcLogoutSuccessHandler());
        http.csrf().disable();

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"));
        SecurityConfigurers.applySecurityConfigurersWithQualifier(http, SECURITY_CONFIGURER_QUALIFIER);
        return http.build();
    }

    protected OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("{baseUrl}");
        return successHandler;
    }
}
