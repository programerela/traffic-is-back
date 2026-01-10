package rs.ac.np.police.trafficis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",      // Swagger UI
                                "/v3/api-docs/**",     // OpenAPI dokumentacija
                                "/swagger-ui.html",    // Swagger UI index
                                "/api/**"              // Svi API endpoint-i
                        ).permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}