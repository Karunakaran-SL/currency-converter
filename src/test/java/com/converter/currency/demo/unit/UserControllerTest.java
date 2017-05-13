package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.converter.currency.demo.model.Country;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.service.api.SecurityService;
import com.converter.currency.demo.service.api.UserService;
import com.converter.currency.demo.validator.UserValidator;
import com.converter.currency.demo.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	UserController controller;
	
	@MockBean
    private UserService userService;

	@MockBean
    private SecurityService securityService;
	
	@Mock
	BindingResult bindingResult;

	@MockBean
    private UserValidator userValidator;
	
	@Test
	public void testRegistration(){
		Model model = new ExtendedModelMap();
		String result = controller.registration(model);
		assertEquals("registration", result);
	}
	
	@Test
	public void testRegistration2(){
		User user = new User();
		user.setAddress("hello");
		user.setCity("hello");
		user.setCountry(Country.INDIA);
		user.setDob("hello");
		user.setEmail("hello@gmail.com");
		user.setId(10L);
		user.setPassword("hello");
		user.setPasswordConfirm("hello");
		user.setRoles(null);
		user.setUsername("hello");
		user.setZipCode("hello");
		Mockito.when(userService.save(user)).thenReturn(user);
		Model model = new ExtendedModelMap();
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		String result = controller.registration(user, bindingResult, model);
		assertEquals("redirect:/welcome", result);
		assertNotNull(user.getAddress());
		assertNotNull(user.getCity());
		assertNotNull(user.getCountry());
		assertNotNull(user.getDob());
		assertNotNull(user.getEmail());
		assertNotNull(user.getId());
		assertNotNull(user.getPassword());
		assertNotNull(user.getPasswordConfirm());
		assertEquals(null, user.getRoles());
		assertNotNull(user.getUsername());
		assertNotNull(user.getZipCode());
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		result = controller.registration(user, bindingResult, model);
		assertEquals("registration", result);
	}

	@Test
	public void testLogin(){
		assertEquals("login",controller.login(new ExtendedModelMap(), null, null));
		assertEquals("login",controller.login(new ExtendedModelMap(), "Test", "Test"));
	}
}
