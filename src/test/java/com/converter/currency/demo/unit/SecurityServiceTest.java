package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import com.converter.currency.demo.service.SecurityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceTest {
	
	@Autowired
	SecurityService securityService;
	
	@MockBean
    private UserDetailsService userDetailsService;
	
	@MockBean
    private AuthenticationManager authenticationManager;

	@Test
	public void testFindLoggedUser(){
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn("test123");
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		String result = securityService.findLoggedInUsername();
		assertEquals("test123", result);
	}
	
	@Test
	public void testautologin(){
		UserDetails value = Mockito.mock(UserDetails.class); 
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn("test123");
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(userDetailsService.loadUserByUsername("test123")).thenReturn(value);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
        		new UsernamePasswordAuthenticationToken(value,
        				"test123", value.getAuthorities());
		Mockito.when(authenticationManager.authenticate(usernamePasswordAuthenticationToken)).thenReturn(authentication);
		securityService.autologin("test123", "test123");
		usernamePasswordAuthenticationToken.setAuthenticated(false);
		securityService.autologin("test123", "test123");
	}
}
