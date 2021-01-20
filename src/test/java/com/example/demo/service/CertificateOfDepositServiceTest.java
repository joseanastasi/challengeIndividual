package com.example.demo.service;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.modelo.Budget;
import com.example.demo.repository.RepositoryBudget;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@WebAppConfiguration
public class CertificateOfDepositServiceTest {
    
	@Mock
	private RepositoryBudget repositoryBudget;
	
	@InjectMocks
    BudgetService budgetService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    

    @Test
    public void testCertificateSave() {
   
    	Float amountMock = 15F;
    	Budget budget = new Budget();
		budget.setType("certificateOfDeposit");
		budget.setConcept("Plazo fijo");
		budget.setAmount(15F);
		budget.setDate(new Date());
	
    	
    	when(repositoryBudget.save(budget)).thenReturn(budget);
        
        int res = budgetService.save(budget);
        assertEquals(1, res);
        assertEquals(amountMock, budget.getAmount());
    }                
        
    
    
    @Test
    public void testCashCertificate() {
    	Budget budget = new Budget();
		budget.setType("certificateOfDeposit");
		budget.setConcept("Plazo fijo");
		budget.setAmount(15F);
		budget.setDate(new Date());
		
    	int noTxn =1;
    	    	
    	
    	when(repositoryBudget.findById(noTxn)).thenReturn(Optional.of(budget));               
        assertEquals(Optional.of(budget), budgetService.listNoTxn(noTxn));        
        
    
    }
    
    
    
    
    private Budget createBudget(){
    	Budget budget = new Budget();
		
    	int noTxn = 1;  
		String conceptMock = "Junit and Mockito";
		Float amountMock = 15.23F;
		Date dateMock = new Date(2020-05-05);
		
		budget.setDate(dateMock);
		budget.setConcept(conceptMock);
		budget.setNoTxn(noTxn);
		budget.setType("Egreso");
		budget.setAmount(amountMock);
		return budget;
    }    	
}
