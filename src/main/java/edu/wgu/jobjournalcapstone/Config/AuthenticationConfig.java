package edu.wgu.jobjournalcapstone.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
@EnableWebSecurity
public class AuthenticationConfig{

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher optionsMatcher = new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(optionsMatcher).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).authenticated());


        http.formLogin(f -> f.disable())
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("https://a-kansfield.github.io/job-journal", "http://localhost:4200"));
                    cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(List.of("*"));
                    cors.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
                    return cors;
                }));
        http
                .csrf(csrf -> csrf.disable());

        http.httpBasic(withDefaults());

        return http.build();
    }
// Spring Security Password Encoder Causes issues with incoming header, jasypt used instead.
//    @Bean(name = "passwordEncoder")
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
