package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.converter.currency.demo.model.Country;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.service.UserService;
import com.converter.currency.demo.validator.UserValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {
	@Mock
	BindingResult bindingResult;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@Test
	public void testsupports(){
		assertEquals(true, userValidator.supports(User.class));
	}
	
	@Test
	public void testValidate(){
		User user = new User();
		user.setUsername("hello");
		user.setPassword("hello");
		user.setPasswordConfirm("hello");
		user.setEmail("hello@gmail.com");
		user.setDob("1983-06-07");
		user.setAddress("address");
		user.setZipCode("123456789");
		user.setCity("city");
		Mockito.when(userService.findByUsername("hello")).thenReturn(null);
		userValidator.validate(user, bindingResult);
		user.setUsername("he");
		user.setPassword("he");
		user.setEmail("hel");
		user.setAddress("ad");
		user.setDob("2083-06-07");
		user.setZipCode("123");
		user.setCity("c");
		userValidator.validate(user, bindingResult);
		user.setUsername("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		user.setPassword("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		user.setEmail("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		user.setAddress("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		user.setZipCode("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		user.setCity("hesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		userValidator.validate(user, bindingResult);
		user.setUsername("hello");
		user.setPassword("hello");
		user.setPasswordConfirm("hssello");
		user.setEmail("hello");
		user.setDob("1983-0sdad");
		Mockito.when(userService.findByUsername("hello")).thenReturn(user);
		userValidator.validate(user, bindingResult);
	}
}
