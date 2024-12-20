package com.example.mom_check.common.config;

import com.example.mom_check.user.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) ->
                web.ignoring()
                        .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/auth/join"),
                                new AntPathRequestMatcher("/auth/login"),
                                new AntPathRequestMatcher("/users"),
                                new AntPathRequestMatcher("/users/me"),
                                new AntPathRequestMatcher("/users/initial-physical"),
                                new AntPathRequestMatcher("/users/initial-physical/me"),
                                new AntPathRequestMatcher("/babies"),
                                new AntPathRequestMatcher("/babies/*"),
                                new AntPathRequestMatcher("/diaries"),
                                new AntPathRequestMatcher("/diaries/*"),
                                new AntPathRequestMatcher("/schedules"),
                                new AntPathRequestMatcher("/schedules/*"),
                                new AntPathRequestMatcher("/weight"),
                                new AntPathRequestMatcher("/weight/*"),
                                new AntPathRequestMatcher("/mypage")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailService userDetailsService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
