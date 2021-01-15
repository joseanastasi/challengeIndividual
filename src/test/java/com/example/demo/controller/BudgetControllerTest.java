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
	
//	@Test
//	public void test_deleteBudgetEntryFound() throws Exception {
//
//
//		Budget budget = new Budget();
//		Long noTxn = 1L;  
//		String conceptMock = "Junit and Mockito";
//		Float amountMock = 15.23F;
//		Date dateMock = new Date(2020-05-05);
//		
//		budget.setDate(dateMock);
//		budget.setConcept(conceptMock);
//		budget.setNoTxn(noTxn);
//		budget.setAmount(amountMock);
//		
//		when(newsServiceMock.getBudgetById(noTxn)).thenReturn(budget);
//
//		mockMvc.perform(get("/delete/{noTxn}", 1L)).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/admin/news"));
//
//		ArgumentCaptor<Long> formObjectArgument = ArgumentCaptor.forClass(Long.class);
//		verify(newsServiceMock).deleteNewsById(formObjectArgument.capture());
//
//		Long formObject = formObjectArgument.getValue();
//
//		assertNotNull(formObject);
//		assertTrue(formObject.equals(1L));
//
//		verify(newsServiceMock, times(1)).deleteNewsById(1L);
//		verify(newsServiceMock, times(1)).getNewsById(1L);
//		verifyNoMoreInteractions(newsServiceMock);
//	}

//	@GetMapping("/delete/{noTxn}")
//	public String delete(@PathVariable int noTxn, Model model) {
//		service.delete(noTxn);
//		return "redirect:/list";
//	}	
		

	
	@Test
	public void test_editBudgetEntryFound() throws Exception { 
		Budget budget = new Budget();
		int noTxn = 1;  
		String conceptMock = "Junit and Mockito";
		Float amountMock = 15.23F;
		Date dateMock = new Date(2020-05-05);
		
		budget.setDate(dateMock);
		budget.setConcept(conceptMock);
		budget.setNoTxn(noTxn);
		budget.setAmount(amountMock);
				
	
		when(budgetServiceMock.listNoTxn(noTxn)).thenReturn(Optional.of(budget));	
		
		boolean isActive =true;
		
		mockMvc.perform(get("/edit/{noTxn}", 1)).andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(model().attribute("isActive", isActive))
				.andExpect(model().attribute("budget", Optional.of(budget)));

		verify(budgetServiceMock, times(1)).listNoTxn(1);
		verifyNoMoreInteractions(budgetServiceMock);
	}
		

}
