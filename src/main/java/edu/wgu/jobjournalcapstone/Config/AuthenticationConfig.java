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

        http
                .csrf(csrf -> csrf.disable());

        http.formLogin(f -> f.disable())
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("*"));
                    cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(List.of("*"));
                    cors.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
                    return cors;
                }));
//        http.formLogin(formLogin -> formLogin
//                .loginPage("/account/login")                // Maps to the page url
//
//                .loginProcessingUrl("/account/login"));     // Maps to the specified url after processing
//
//
//        http.logout(formLogout -> formLogout
//                .invalidateHttpSession(true)
//                // this is the URL that will log a user out
//                // this is another URL that is included with spring security - we do not have a controller method for this
//                .logoutUrl("/account/logout")
//                // after spring logs the user out then it will goto this URL
//                .logoutSuccessUrl("/"));
        http.httpBasic(withDefaults());

        return http.build();
    }

//    @Bean(name = "passwordEncoder")
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
