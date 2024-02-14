package ime.book_app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final ConfigurationPropertyValues cpv;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
                .csrf(AbstractHttpConfigurer::disable) 
                .authorizeHttpRequests(auth -> {
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("**")).permitAll();
                	auth.anyRequest().authenticated();
                })
                .headers(head-> head.frameOptions(f->f.sameOrigin())) // H2 database
                .httpBasic(Customizer.withDefaults())
                .formLogin( f -> f.loginPage("/login").defaultSuccessUrl("/success").permitAll())
        		.logout(LogoutConfigurer::permitAll)
                .build();		
	}
	

	@Bean
	UserDetailsService users() {

		String passwd = cpv.getPassValue();
		
		UserDetails user = User.builder()
			.username("user")
			.password(passwordEncoder().encode(passwd))
			.roles("USER")
			.build();
		
		UserDetails admin = User.builder()
			.username("admin")
			.password(passwordEncoder().encode(passwd))
			.roles("USER", "ADMIN")
			.build();
		
		return new InMemoryUserDetailsManager(user, admin);
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
