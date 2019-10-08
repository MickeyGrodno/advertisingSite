package ru.senla.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableAuthorizationServer
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private final String CLIENT_ID = "tutorialspoint";
    private final String CLIENT_SECRET = "my-secret-key";
    private final String TOKEN_SIGNATURE = "1234";

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserDetailsService customDetailsService;

    public OAuth2Config(AuthenticationManager authenticationManager,
                        PasswordEncoder encoder, UserDetailsService customDetailsService) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.customDetailsService = customDetailsService;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(TOKEN_SIGNATURE);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer()).userDetailsService(customDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String encodedClientSecret = encoder.encode(CLIENT_SECRET);
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(encodedClientSecret)
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }
}