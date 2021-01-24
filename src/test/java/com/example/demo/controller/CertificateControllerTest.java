package com.example.demo.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

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

import com.example.demo.controler.CertificateOfDepositController;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.interfaceService.ICertificateOfDepositService;
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.CertificateOfDeposit;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@WebAppConfiguration
public class CertificateControllerTest {

	@Mock
	private ICertificateOfDepositService certificateServiceMock;
	@Mock
	private IBudgetService budgetServiceMock;

	
	@InjectMocks
	private CertificateOfDepositController certificateOfDepositController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(certificateOfDepositController).build();
	}
	
	
	@Test
	public void testAddCertificateOfDeposit() throws Exception { 
	
		   	Float s = 5F;
			when(budgetServiceMock.sum()).thenReturn(s);	

		mockMvc.perform(get("/certificate")).andExpect(status().isOk())
				.andExpect(view().name("certificateOfDeposit"))
				.andExpect(model().attribute("availableMoney",s))
				.andExpect(model().attribute("certificate", instanceOf(CertificateOfDeposit.class)));

		verify(budgetServiceMock, times(1)).sum();

		verifyNoMoreInteractions(budgetServiceMock);
	}

	
	
	@Test
	public void testEditCertificate() throws Exception { 
		int noTxn = 1;  
		Date today = new Date();
		
		doNothing().when(certificateServiceMock).cashCertificate(noTxn, today);
		
		
		
		mockMvc.perform(get("/cashCertificate/{noTxn}", 1)).
		andExpect(redirectedUrlPattern("/list*"));


		verify(certificateServiceMock, times(1)).cashCertificate(noTxn, today);
		verifyNoMoreInteractions(certificateServiceMock);
	}
	
	
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
