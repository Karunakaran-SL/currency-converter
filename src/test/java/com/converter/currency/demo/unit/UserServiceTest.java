package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.converter.currency.demo.WebApplication;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.repository.RoleRepository;
import com.converter.currency.demo.repository.UserRepository;
import com.converter.currency.demo.service.api.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;
	@MockBean
	private RoleRepository roleRepository;
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	public void testSave(){
		User user = new User();
		user.setUsername("test");
		user.setPassword("test123");
		Mockito.when(bCryptPasswordEncoder.encode("test123")).thenReturn("test123");
		Mockito.when(roleRepository.findAll()).thenReturn(null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User user2 = userService.save(user);
		assertEquals(user.getUsername(), user2.getUsername());
	}
	
	@Test
	public void testFindByUsername(){
		User user = new User();
		user.setUsername("test123");
		user.setPassword("test123");
		Mockito.when(userRepository.findByUsername("test123")).thenReturn(user);
		User user2 = userService.findByUsername("test123");
		assertEquals(user.getUsername(), user2.getUsername());
	}
	
	@Test
	public void testFindByUsername1(){
		User user = new User();
		user.setPassword("test123");
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));
	}
	
	@Test
	public void testFindByUsername2(){
		User user = new User();
		user.setPassword("test123");
		Mockito.when(userRepository.findAll()).thenReturn(null);
	}
	
	@Test
	public void testFindByUsername3(){
		User user = new User();
		user.setPassword("test123");
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
	}
}
