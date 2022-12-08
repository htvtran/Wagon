package com.app.wagon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.wagon.config.security.handler.UserAccessDeniedHandler;
import com.app.wagon.config.security.handler.UserLoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Autowired
    private UserLoginSuccessHandler successHandler;

    @Autowired
    private UserAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyAuthority("STAFF", "DIRECTOR")
                .antMatchers("/account/**").hasAnyAuthority("STAFF", "DIRECTOR", "CUSTOMER")
                .antMatchers("/auth").authenticated()
                .antMatchers("/rest/user").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login/auth")
                .successHandler(successHandler)
                .defaultSuccessUrl("/index", false)
                .failureUrl("/user/login?error")
                .usernameParameter("username")
                .passwordParameter("passwords")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().logout().logoutSuccessUrl("/logout/success");

        // [STAFF, DIRECTOR, CUSTOMER
        http.rememberMe().rememberMeParameter("remember");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();

    }

}
