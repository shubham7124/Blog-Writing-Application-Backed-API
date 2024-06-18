package com.banckend.blogappapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.security.AuthenticationFilter;
import com.banckend.blogappapi.security.CustomUserDetailService;
import com.banckend.blogappapi.security.JwtAuthenticationEnrtyPoint;



@Configuration
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

	@Autowired
	private CustomUserDetailService customeUserDetailService;
	@Autowired
	private JwtAuthenticationEnrtyPoint jwtAuthenticationEnrtyPoint;
	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		 http
//		 .csrf()
//		 .disable()
//	        .authorizeRequests()
//	        .requestMatchers("/api/login").permitAll()
//	        .anyRequest()
//	        .authenticated()
//	        .and()
//	        .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEnrtyPoint)
//	        .and()
//	        .sessionManagement()
//	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf(csrf -> csrf.disable())
        .authorizeRequests()
         .requestMatchers("/api/auth/login",
        		 "/v2/api-docs",
        		 "/v3/api-docs",
        		 "/swagger-resources/**",
        		 "/swagger-ui/**",
        		 "/webjars/**",
        		 "/api/users/normaluser"
        		  ).permitAll()
     
//         permint all GET request are public allowed 
         .requestMatchers("/swagger-ui/**", 
        		 "/v3/api-docs/**" ).permitAll() 
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtAuthenticationEnrtyPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		

      

		 http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
    }
	
//	@Bean
//	protected int  configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		auth.userDetailsService(this.customeUserDetailService).passwordEncoder(passwordEncoder());
//		
//		return 0;
//	}
	
	
	 @Bean
	 public DaoAuthenticationProvider daoAuthenticationProvider()
 {
	
		 
	
		
		 DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		 provider.setUserDetailsService(customeUserDetailService);
		 provider.setPasswordEncoder(passwordEncoder());
		 
		 String pass=new BCryptPasswordEncoder().encode("123");
//		 System.out.println("passsword in DaoAuthentication =="+pass);
	    	 
		 return provider;
	 }
	 
	 
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configure) throws Exception {
		return configure.getAuthenticationManager();
		
	}
	
}
