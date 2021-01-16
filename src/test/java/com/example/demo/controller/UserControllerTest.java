package com.example.demo.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controler.UserController;
import com.example.demo.dto.ChangePasswordForm;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@WebAppConfiguration
public class UserControllerTest {

	@Mock
	private UserService userServiceMock;
	@Mock
	private RoleService roleServiceMock;
    @Mock
    PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
//	chequear el new User	
	@Test
	public void testSignup() throws Exception { 
	
		
		Role role1 = createRole();
    	List<Role> roleList = Arrays.asList(role1);
		String name ="USER";
				
		when(roleServiceMock.findByName(name)).thenReturn(role1);
		

		mockMvc.perform(get("/signup")).andExpect(status().isOk())
				.andExpect(view().name("user-form/user-signup"))
				.andExpect(model().attribute("signup", true))
				.andExpect(model().attribute("userForm", new User()))
				.andExpect(model().attribute("roles", roleList));
				
		verify(roleServiceMock, times(1)).findByName(name);
		verifyNoMoreInteractions(roleServiceMock);
	}
	
//	chequear el new User
	@Test
	public void testUserForm() throws Exception { 
	
		User user1 = newUser();
	    User user2 = newUser();
	    List<User> userList = List.of(user1, user2);
		
		Role role1 = createRole();
		Role role2 = createRole();
		List<Role> roleList = List.of(role1,role2);
		
		
		when(userServiceMock.getAllUsers()).thenReturn(userList);
		when(roleServiceMock.getAllroles()).thenReturn(roleList);


		mockMvc.perform(get("/userForm")).andExpect(status().isOk())
				.andExpect(view().name("user-form/user-view"))
				.andExpect(model().attribute("userForm", new User()))
				.andExpect(model().attribute("userList", userList))
				.andExpect(model().attribute("roles", roleList))
				.andExpect(model().attribute("listTab","active"));

				
		verify(roleServiceMock, times(1)).getAllroles();
		verify(userServiceMock, times(1)).getAllUsers();
		
		verifyNoMoreInteractions(roleServiceMock);
	}
	
	
	@Test
	public void testEditUserForm() throws Exception { 
	
		Long id =1L;
		
		User user1 = newUser();
	    User user2 = newUser();
	    List<User> userList = List.of(user1, user2);
		
		Role role1 = createRole();
		Role role2 = createRole();
		List<Role> roleList = List.of(role1,role2);
		
		User userToEdit = new User();
		
		when(userServiceMock.getAllUsers()).thenReturn(userList);
		when(roleServiceMock.getAllroles()).thenReturn(roleList);
		

		mockMvc.perform(get("/editUser/{id}",1L)).andExpect(status().isOk())
				.andExpect(view().name("user-form/user-view"))
				.andExpect(model().attribute("userForm", userToEdit))
				.andExpect(model().attribute("userList", userList))
				.andExpect(model().attribute("roles", roleList))
				.andExpect(model().attribute("formTab", "active"))
				.andExpect(model().attribute("passwordForm", new ChangePasswordForm(id)))
				.andExpect(model().attribute("editMode", "true"));
				
		verify(roleServiceMock, times(1)).getAllroles();
		verify(userServiceMock, times(1)).getAllUsers();
		
		verifyNoMoreInteractions(roleServiceMock);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  private User newUser(){
		    
    	  Long id = 1L;
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
			

	
	 private Role createRole(){
	    	Role role = new Role();
			
	    	String name = "USER";
			String description = "ROLE_USER";
						
			role.setName(name);
			role.setDescription(description);
			return role;
	    }    	
}
