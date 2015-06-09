package com.vizaco.onlinecontrol.configuration;

import com.vizaco.onlinecontrol.security.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import java.util.Arrays;

/**
 * Created by super on 6/5/15.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/index", "/registration/facebook", "/registration/google", "/callback/google/registration").permitAll()
                .antMatchers("/registration").access("ROLE_ADMIN")
                .antMatchers("/**").access("ROLE_USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/", false)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID,SPRING_SECURITY_REMEMBER_ME_COOKIE")
                .and()
                .rememberMe().key("onlinecontrol")
                .tokenRepository(new JdbcTokenRepositoryImpl())
                .tokenValiditySeconds(3600)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .accessDeniedPage("/exception/403")
                .and()
                .csrf();

    }
}
