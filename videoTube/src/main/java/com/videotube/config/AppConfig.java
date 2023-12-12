package com.videotube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.videotube.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class AppConfig  {
  
   
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
  }
	
 @Bean
 public SecurityFilterChain configration(HttpSecurity http) throws Exception{
	 http.authorizeHttpRequests(
			 auth-> auth.requestMatchers(HttpMethod.POST,"/users/register").permitAll().anyRequest()
			 .authenticated())
	 .csrf(c->c.disable())
	 .formLogin(Customizer.withDefaults())
	 .httpBasic(Customizer.withDefaults());
	 return http.build();
	 
			 
			 
 }
	
	
}
