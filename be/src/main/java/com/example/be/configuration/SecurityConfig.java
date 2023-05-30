package com.example.be.configuration;

import com.example.be.oauth.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService userService;

    @Autowired
    public SecurityConfig(CustomOAuth2UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .oauth2Login()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(userService);
    }
}
