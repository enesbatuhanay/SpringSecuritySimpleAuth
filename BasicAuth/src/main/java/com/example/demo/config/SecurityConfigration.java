package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigration   {


@Bean
public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user = User
            .withUsername("user")
            .password("{noop}123123a")
            .roles("USER")
            .build();

    UserDetails admin = User
            .withUsername("admin")
            .password("{noop}123123a")
            .roles("ADMIN","USER")
            .build();


    return new InMemoryUserDetailsManager(user,admin);
}



   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       http
               .csrf().disable().authorizeRequests().antMatchers("/index").hasRole("USER").and()
               .authorizeRequests().antMatchers("/info").hasRole("ADMIN")
               .and()
               .httpBasic();
                ;
      return http.build();
    }
}