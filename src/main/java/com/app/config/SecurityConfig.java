package com.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.app.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig  {
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
			csrf(csrf -> csrf.disable())
			.cors(cors -> {})
			.sessionManagement(session -> 
							session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			.authorizeHttpRequests(auth -> auth
					 .requestMatchers("/reviews/{id}/approve").hasRole("ADMIN")
					 .requestMatchers("/reviews/{id}/reject").hasRole("ADMIN")
					 .requestMatchers("/reviews/stats").hasRole("ADMIN")
					 .requestMatchers("/reviews/top-fraud-users").hasRole("ADMIN")
					 .requestMatchers("/reviews?page=0&size=10").hasAnyRole("USER","ADMIN")
					 .requestMatchers("/reviews/filter").hasAnyRole("USER","ADMIN")
					 .requestMatchers("/reviews/status").hasAnyRole("USER","ADMIN")
					.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/users/**").permitAll()
					.requestMatchers("/products/**").permitAll()
					.anyRequest().authenticated()
				)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
	}
	
	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(List.of("http://localhost:5173"));
		
		configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
		
		return urlBasedCorsConfigurationSource;
	}
}
