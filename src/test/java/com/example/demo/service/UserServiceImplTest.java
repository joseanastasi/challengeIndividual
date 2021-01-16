package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	private UserRepository userRepository;
	   
	    @Mock
	    PasswordEncoder passwordEncoder;

	@InjectMocks
    UserServiceImpl userServiceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userServiceImpl = new UserServiceImpl();
        passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Test
    public void testCreateUser() {
        Long id = 10L;
        String firstName = "pepito";
        String lastName = "diaz";
        String username = "pepeDiaz";
        String email = "cuca@cuca.com";
        String passwordEnc = passwordEncoder.encode("pepino");

                
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(passwordEnc);
        user.setEmail(email);
        
        when(userRepository.save(user)).thenReturn(user);
                
        assertEquals(user, userRepository.save(user));              
    }
       
    
//    @Override
//	public Iterable<User> getAllUsers() {
//		return repository.findAll();
//	}
//    
    
    
    @Test
    public void testGetAllUser() {

        User user1 = newUser();
        User user2 = newUser();
        List<User> userList = List.of(user1, user2);
    
        when(userRepository.findAll()).thenReturn(userList);        
        assertEquals(userList, userServiceImpl.getAllUsers());    
             
    }
        
    
    @Test
    public void testGetUserById() throws UsernameOrIdNotFound {
       
        User user = newUser();
        Long id=1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, userServiceImpl.getUserById(id));        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private User newUser(){
    
    	  Long id = 10L;
          String firstName = "pepito";
          String lastName = "diaz";
          String username = "pepeDiaz";
          String email = "cuca@cuca.com";
          String passwordEnc = passwordEncoder.encode("pepino");

                  
          User user = new User();
          user.setId(id);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setUsername(username);
          user.setPassword(passwordEnc);
          user.setEmail(email);
          
          return user;
    	   	
    	}    	
    
    
}
        
//           
//    @Test
//    public void testListNoTxn() {
//    	Budget budget = createBudget();		
//    	int noTxn = 1;  		
//        when(budgetRepository.findById(noTxn)).thenReturn(Optional.of(budget));        
//        assertEquals(Optional.of(budget), budgetService.listNoTxn(noTxn));              
//    }
//    
//    @Test
//    public void testSave() {
//    	Float amountMock = 15.23F;
//    	Budget budget = createBudget();
//        when(budgetRepository.save(budget)).thenReturn(budget);
//        
//        int res = budgetService.save(budget);
//        assertEquals(1, res);
//        assertEquals(amountMock * -1, budget.getAmount());
//    }                
//   
//    @Test
//    public void testSum() {
//    	//TODO: si corrijo el codigo de IBudget de String a Float cambiar el tipo de res
//    	Float res = 5F;    	
//        when(budgetRepository.sum()).thenReturn(res);               
//        assertEquals(res, budgetService.sum());        
//    }
//    
//    @Test
//    public void testLastTen() {
//    	Budget budget1 = createBudget();
//    	Budget budget2 = createBudget();
//    	List<Budget> budgetList = List.of(budget1, budget2);
//    			
//        when(budgetRepository.lastTen()).thenReturn(budgetList);        
//        assertEquals(budgetList, budgetService.lastTen());              
//    }
//
  

