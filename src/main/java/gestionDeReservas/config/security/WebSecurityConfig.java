package gestionDeReservas.config.security;

import gestionDeReservas.config.security.jwt.JwtAuthenticationFilter;

import gestionDeReservas.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    private final static String CUSTOMER = Role.CUSTOMER.toString();
    private final static String RECEPTIONIST = Role.RECEPTIONIST.toString();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authRequest -> {
                    configurePublicEndpoints(authRequest);
                    configureCustomerEndPoints(authRequest);
                    configureReceptionistsEndpoint(authRequest);
                })

                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void configurePublicEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequest) {
        authRequest
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/saludo/hola-todos").permitAll()
                .requestMatchers(HttpMethod.GET, "/saludo/hola-fede").permitAll()
                .requestMatchers(HttpMethod.GET, "/saludo/funciona").permitAll()

                .requestMatchers(HttpMethod.GET, "/rooms").permitAll()
                .requestMatchers(HttpMethod.POST, "/rooms").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/rooms/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/rooms").permitAll()
                .requestMatchers(HttpMethod.POST, "/typeRoom").permitAll()
                .requestMatchers(HttpMethod.GET, "/typeRoom").permitAll()
                .requestMatchers(HttpMethod.PUT, "/typeRoom").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/typeRoom/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/auth/logout").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

    }


    private void configureCustomerEndPoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequest) {
        authRequest.requestMatchers(HttpMethod.GET, "/saludo/hola-cliente").hasRole(CUSTOMER)
                .requestMatchers(HttpMethod.POST,"/booking").permitAll()
                .requestMatchers(HttpMethod.GET,"/booking/enabled-rooms").permitAll()
                .requestMatchers(HttpMethod.GET,"/booking/user").hasRole(CUSTOMER)
                .requestMatchers(HttpMethod.POST,"/visitor").permitAll();
    }

    private void configureReceptionistsEndpoint(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequest) {
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // van las ips del deploy tambien
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("Authorization", "Access-Control-Allow-Origin"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}