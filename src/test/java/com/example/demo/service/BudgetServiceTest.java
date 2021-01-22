//package com.example.demo.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.example.demo.modelo.Budget;
//import com.example.demo.repository.RepositoryBudget;
//
//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
//@WebAppConfiguration
//public class BudgetServiceTest {
//    
//	@Mock
//	private RepositoryBudget budgetRepository;
//	
//	@InjectMocks
//    BudgetService budgetService;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
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
//    private Budget createBudget(){
//    	Budget budget = new Budget();
//		
//    	int noTxn = 1;  
//		String conceptMock = "Junit and Mockito";
//		Float amountMock = 15.23F;
//		Date dateMock = new Date(2020-05-05);
//		
//		budget.setDate(dateMock);
//		budget.setNoTxn(noTxn);
//		budget.setType("Egreso");
//		budget.setAmount(amountMock);
//		return budget;
//    }    	
//}
