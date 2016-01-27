package com.users.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true, proxyTargetClass=true)
@Import(value=BackendPersistenceConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired UserDetailsService userDetailsService;
	@Autowired DataSource dataSource;
	@Autowired Environment env;
	
	static {
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	http.authorizeRequests().anyRequest().fullyAuthenticated();
//        http.httpBasic();
//        http.formLogin()
//        	.defaultSuccessUrl(env.getProperty("apiBaseURL") + "/getLayout")
//        		.failureUrl(env.getProperty("apiBaseURL") + "/getLayout")
//        	.and()
//        	.logout()
//        		.logoutSuccessUrl(env.getProperty("apiBaseURL") + "/getLayout")
//        	.and()
//        	;
        
        http
        .httpBasic().and()
        .authorizeRequests().anyRequest().fullyAuthenticated().and()
        .csrf().disable();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
    }
}
