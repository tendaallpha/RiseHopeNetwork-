package cm.genie6.risehope.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import cm.genie6.risehope.service.AccountService;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationManager authenticationManager;
	@Autowired
	private AccountService userService;
	private AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager).userDetailsService(userService)
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/css/font-awesome.min.css", "/**", "/css/main.css")
				.permitAll().anyRequest().fullyAuthenticated().and().formLogin().loginPage("/authentification")
				.defaultSuccessUrl("/home", true).and().logout().permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}