package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controler.BudgetController;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.modelo.Budget;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@WebAppConfiguration
public class BudgetControllerTest {

	@Mock
	private IBudgetService budgetServiceMock;

	
	@InjectMocks
	private BudgetController budgetController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
	}
	
	
	@Test
	public void test_listBudgetEntryFound() throws Exception { 
		Budget budget1 = createBudget();
    	Budget budget2 = createBudget();
    	
    	List<Budget> budgetList = List.of(budget1, budget2);
    	Float s = 5F;
	
		when(budgetServiceMock.lastTen()).thenReturn(budgetList);	
		when(budgetServiceMock.sum()).thenReturn(s);	

		mockMvc.perform(get("/list")).andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("budgets", budgetList))
				.andExpect(model().attribute("suma", s));

		verify(budgetServiceMock, times(1)).lastTen();
		verify(budgetServiceMock, times(1)).sum();

		verifyNoMoreInteractions(budgetServiceMock);
	}
		

	
	@Test
	public void test_editBudgetEntryFound() throws Exception { 
		Budget budget = createBudget();
		int noTxn = 1;  
	
		when(budgetServiceMock.listNoTxn(noTxn)).thenReturn(Optional.of(budget));	
		
		boolean isActive =true;
		
		mockMvc.perform(get("/edit/{noTxn}", 1)).andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(model().attribute("isActive", isActive))
				.andExpect(model().attribute("budget", Optional.of(budget)));

		verify(budgetServiceMock, times(1)).listNoTxn(1);
		verifyNoMoreInteractions(budgetServiceMock);
	}
	
	@Test
	public void test_addBudgetEntryFound() throws Exception { 
		Budget budget = createBudget();
		  		
		
		
		mockMvc.perform(get("/add")).andExpect(status().isOk())
				.andExpect(view().name("form"));
		}
	

	
//	
//	@Test
//	
//	public void testSaveBudget() throws Exception { 
//		  		
//		Budget b = createBudget();
//	    BindingResult result =  new BeanPropertyBindingResult(b, "budget");
//
//	    
//		when(budgetServiceMock.save(b)).thenReturn(1);
//
//		BudgetController budgetController = new BudgetController();
////		mockMvc.perform(post("/save")).andExpect(status().isOk())
////		.andExpect(view().name("redirect:/list"));
//	
//		
//		String view = budgetController.save(b, result);
//
//	    assertEquals("redirect:/list", view);
//
//				
//		verify(budgetServiceMock, times(1)).save(b);
//
//		}
		
	 private Budget createBudget(){
	    	Budget budget = new Budget();
			
	    	int noTxn = 1;  
			String conceptMock = "Junit and Mockito";
			Float amountMock = 15.23F;
			Date dateMock = new Date(2020-05-05);
			
			budget.setDate(dateMock);
			budget.setNoTxn(noTxn);
			budget.setType("Egreso");
			budget.setAmount(amountMock);
			return budget;
	    }    	
}
