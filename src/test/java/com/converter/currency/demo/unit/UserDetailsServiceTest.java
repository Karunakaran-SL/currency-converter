package com.converter.currency.demo.unit;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import com.converter.currency.demo.model.Role;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.repository.UserRepository;
import com.converter.currency.demo.service.impl.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceTest {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testLoadUserByUsernam(){
		User user = new User();
		user.setUsername("hello");
		user.setPassword("hello");
		user.setPasswordConfirm("hello");
		user.setEmail("hello@gmail.com");
		user.setDob("1983-06-07");
		user.setAddress("address");
		user.setZipCode("123456789");
		user.setCity("city");
		Set<User> userSet = new HashSet<>();
		userSet.add(user);
		Role role = new Role();
		role.setId(10L);
		role.setName("Admin");
		role.setUsers(userSet);
		role.getId();
		role.getName();
		role.getUsers();
		user.getRoles();
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		user.setRoles(roleSet);
		try {
			Mockito.when(userRepository.findByUsername("hello")).thenReturn(user);
			UserDetails details = userDetailsService.loadUserByUsername("hello");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
