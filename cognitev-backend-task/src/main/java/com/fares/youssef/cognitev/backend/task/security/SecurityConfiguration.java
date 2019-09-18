package com.fares.youssef.cognitev.backend.task.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);
	private static final String AUTH_TOKEN = "auth-token";

	@Autowired
	private AuthenticationProviderImpl authenicationProviderImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info("configure - congfiguring the app security");

		final SessionRepositoryFilter<ExpiringSession> sessionRepositoryFilter = new SessionRepositoryFilter<>(
				sessionRepository());
		HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
		headerHttpSessionStrategy.setHeaderName(AUTH_TOKEN);
		sessionRepositoryFilter.setHttpSessionStrategy(headerHttpSessionStrategy);

		http.cors().and().antMatcher("/**").authorizeRequests().antMatchers("/authentication/**", "/h2-console/**")
				.permitAll().antMatchers("/registration/**").hasAnyAuthority("USER").anyRequest().authenticated().and()
				.csrf().disable().addFilterBefore(sessionRepositoryFilter, ChannelProcessingFilter.class).httpBasic()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("configure - setting the authentication provider");
		auth.authenticationProvider(authenicationProviderImpl);
	}

	@Bean
	public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
		return new SecurityContextHolderAwareRequestFilter();
	}

	@Bean
	public SessionRepository<ExpiringSession> sessionRepository() {
		LOG.info("sessionRepository - returns the SessionRepository bean");
		return new MapSessionRepository();
	}

}
