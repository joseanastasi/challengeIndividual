package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.Exception.UsernameOrIdNotFound;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@WebAppConfiguration
public class UserServiceImplTest {
    
	@Mock
	private UserRepository userRepositoryMock;
	   
    @Mock
    PasswordEncoder passwordEncoder;
	    
    @Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
    UserServiceImpl userServiceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Test
    public void testCreateUser() throws Exception {
    User user = newUser();
    	
        when(userRepositoryMock.save(user)).thenReturn(user);    
        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(Optional.empty());                        
        assertEquals(user, userServiceImpl.createUser(user));              
    }

    @Test
    public void testCreateUserFailBecauseUserAlreadyRegistered() throws Exception {
    User user = newUser();
    	
        when(userRepositoryMock.save(user)).thenReturn(user);
        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
                                
        try {
        	userServiceImpl.createUser(user);
        	Assert.fail();
        } catch(Exception e)
        {
        }
        
    }

       
    @Test
    public void testGetAllUser() {

        User user1 = newUser();
        User user2 = newUser();
        List<User> userList = List.of(user1, user2);
    
        when(userRepositoryMock.findAll()).thenReturn(userList);        
        assertEquals(2, userList.size());    
        
        assertEquals(userList, userServiceImpl.getAllUsers());    
             
    }
        
    
    @Test
    public void testGetUserById() throws UsernameOrIdNotFound {
       
        User user = newUser();
        Long id=1L;
        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, userServiceImpl.getUserById(id));        
                
    }
       
    
    @Test
    public void testDeleteUser() throws UsernameOrIdNotFound {
       
        User user = newUser();
        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(user));
        userServiceImpl.deleteUser(1L);
		verify(userRepositoryMock, times(1)).delete(user);

    }
    
    
    
    private User newUser(){
    
    	  Long id = 1L;
          String firstName = "pepito";
          String lastName = "diaz";
          String username = "pepeDiaz";
          String email = "cuca@cuca.com";
          String password="pepino";
          String passwordEnc = passwordEncoder.encode(password);
          String confirmPassword = passwordEnc;

                  
          User user = new User();
          user.setId(id);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setUsername(username);
          user.setEmail(email);
          user.setPassword(passwordEnc);
          user.setConfirmPassword(confirmPassword);
          
          return user;
    	   	
    	}    	
    
    
}
        

  

