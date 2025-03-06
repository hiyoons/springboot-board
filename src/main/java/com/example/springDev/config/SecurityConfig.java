package com.example.springDev.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.disable()) // CORS 설정 추가
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
        ).formLogin((formLogin)-> formLogin.loginPage("/login").loginProcessingUrl("/loginProc").usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/loginOk",true).failureUrl("/login?error"))
                .logout((logoutConfig)-> logoutConfig.logoutUrl("/logout").logoutSuccessUrl("/logoutOk").deleteCookies("JSESSIONID"))
                ;
        http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");// 리액트 서버
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
