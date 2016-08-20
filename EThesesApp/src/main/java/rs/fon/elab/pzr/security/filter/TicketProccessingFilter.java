package rs.fon.elab.pzr.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import rs.fon.elab.pzr.core.exception.InvalidTicketException;
import rs.fon.elab.pzr.core.service.authentication.AuthenticationService;
import rs.fon.elab.pzr.core.service.authentication.CustomUserDetailService;

public class TicketProccessingFilter extends GenericFilterBean {

	private static final Logger logger = Logger.getLogger(TicketProccessingFilter.class);
	
	public TicketProccessingFilter() {
		super();
	}

	private UserDetailsService customUserDetailsService;
	private AuthenticationManager authenticationManager;
	private AuthenticationService authenticationService;

	public UserDetailsService getCustomUserDetailsService() {
		return customUserDetailsService;
	}

	public void setCustomUserDetailsService(
			UserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.debug("###############TicketProcessingFilter################");
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			
			//CHECK IF VALID TICKET EXSIST AND IF SO SET AUTHENTICATION
			String ticket = httpRequest.getHeader("ticket");
			logger.debug("Ticket: "+ticket);
			try{
				String userName = authenticationService.validate(ticket);
				logger.debug("UserEmail: "+userName);
				UserDetails user = customUserDetailsService
						.loadUserByUsername(userName);
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch(Exception e){
				logger.debug(e.getMessage());
			}
		}
		chain.doFilter(request, response);		
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
