package com.example.demo.deepakrohan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${rogs.security.user:rogs}") String user;

    @Value("${rogs.security.password:rogspa$$w0rd}") String password;

    @Value("${rogs.security.enabled:true}")
    boolean enableSecurity;

    @SuppressWarnings("deprecation")
	@Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if (user == null)
            user="user";
        if (password==null)
            password="password";

        auth
                .inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser(user)
                .password(password)
                .roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        if (enableSecurity){
        	http.headers().frameOptions().disable();
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/v2/api-docs", "/configuration/ui",
                            "/swagger-resources", "/configuration/security",
                            "/swagger-ui.html", "/webjars*", "/webjars/**",
                            "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security",
                            "/h2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().and()
                    .rememberMe().and()
                    .csrf().disable();

        }else{
            http
                    .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().and()
                    .rememberMe().and()
                    .csrf().disable();
        }
    }
}

