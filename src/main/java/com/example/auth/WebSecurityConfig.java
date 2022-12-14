package com.example.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    private DataSource dataSource;*/

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		/*.antMatchers("/**").authenticated()*/
                .antMatchers("/login", "/forgot_password", "/reset_password/**","/some-action","/our-websocket/**","/ws/**").permitAll()
                .antMatchers("/admin_system").hasAnyRole("ADMIN", "DOCTOR", "NURSE")
                .antMatchers("/appointment").hasRole("PATIENT")
                .antMatchers("/patient").hasAnyRole("NURSE", "ADMIN")
                .antMatchers("/visitation").hasAnyRole("DOCTOR", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                /*.usernameParameter("username")*/
                .defaultSuccessUrl("/dashboard")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();

    }




}
