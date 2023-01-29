package com.artostapyshyn.personaldpslviv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

 
@Configuration
@EnableWebSecurity
@SecurityScheme(name="personaldpslviv", scheme="basic", type=SecuritySchemeType.HTTP, in=SecuritySchemeIn.HEADER)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    	@Bean
    	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    		http
    		.csrf()
    		.disable()
    		.authorizeHttpRequests()
    		.requestMatchers("/").permitAll() 
    		.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
    		.requestMatchers("/info-pages/**").permitAll()
    		.requestMatchers("/css/**").permitAll()
    		.requestMatchers("/js/**").permitAll()
			.requestMatchers("/img/**").permitAll()
			.requestMatchers("/documents/**").permitAll()
			.requestMatchers("/registration/**").permitAll()
			.requestMatchers("/profile").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/actuator/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/profile", true) 
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.and()
			.httpBasic();

		return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}