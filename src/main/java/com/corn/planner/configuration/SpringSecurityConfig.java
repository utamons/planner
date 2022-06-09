package com.corn.planner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") HttpSecurity http) throws Exception {
		http.csrf()
		    .disable()
		    .authorizeRequests()
		    .anyRequest()
		    .permitAll();
		return http.build();
	}

}
