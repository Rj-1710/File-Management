package com.mphasis.parent.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mphasis.parent.dao.UserRepository;
import com.mphasis.parent.entity.UserDetails;

public class UserDetailServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserDetailServiceImpl userDetailService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testRegisterUser_Success() {
		String userName ="User1";
		String password ="testPassword";
		String encodedPassword= "encodedPasssword123";
		when(userRepository.findByUserName(userName)).thenReturn(null);
		when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
		when(userRepository.save(any(UserDetails.class))).thenReturn(new UserDetails(userName,encodedPassword));
		
		UserDetails userDetails = userDetailService.registerUser(userName,password);
		
		assertNotNull(userDetails);
		assertEquals(userName,userDetails.getUserName());
		assertEquals(encodedPassword,userDetails.getPassword());
		
	}
	
	@Test
	public void testRegisterUser_UserAlreadyExists() {
	    String userName = "testUser";
	    when(userRepository.findByUserName(userName)).thenReturn(new UserDetails(userName, "password")); 

	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        userDetailService.registerUser(userName, "newPass");
	    });

	    assertEquals("Username already exists!", exception.getMessage());
	}
	
	@Test
	public void testUser_validCredentials() {
		String userName = "testUser";
		String password = "testPassword";
		String encodedPassword = "encodedPassword";
		UserDetails userDetails =new UserDetails(userName,encodedPassword);
		
		when(userRepository.findByUserName(userName)).thenReturn(userDetails);
		when(passwordEncoder.matches(password,encodedPassword)).thenReturn(true);
		
		String result =userDetailService.getUser(userName,password);
		assertEquals("User authenticated successfully!", result);
	}
	
	@Test
	public void testUser_InvalidCredentials() {
		String userName = "testUser";
		String password = "wrongPassword";
		UserDetails userDetails = new UserDetails(userName,"encodedPassword");
		
		when(userRepository.findByUserName(userName)).thenReturn(userDetails);
		when(passwordEncoder.matches(password,"encodedPassword")).thenReturn(false);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () ->{
			
			userDetailService.getUser(userName,password);
		});
		
		assertEquals("Invalid username or password!", exception.getMessage());
	}
	
	/*
	 * @Test public void testDeleteUser_Success() { String userName="testUser";
	 * UserDetails userDetails = new UserDetails(userName,"encodedPassword");
	 * 
	 * when(userRepository.findByUserName(userName)).thenReturn(userDetails);
	 * 
	 * assertDoesNotThrow(()->userDetailService.deleteUser(userName));
	 * verify(userRepository, times(1)).delete(userDetails); }
	 */
	
	/*
	 * @Test public void testDeleteUser_UserNotFound() {
	 * 
	 * String userName = "NonExistingUser";
	 * when(userRepository.findByUserName(userName)).thenReturn(null);
	 * 
	 * Exception exception =assertThrows(IllegalArgumentException.class, ()->{
	 * userDetailService.deleteUser(userName); });
	 * 
	 * assertEquals("User not found with username: " + userName,
	 * exception.getMessage()); }
	 */
}
