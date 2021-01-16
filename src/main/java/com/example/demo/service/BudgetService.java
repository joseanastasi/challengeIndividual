package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.modelo.Budget;
import com.example.demo.repositories.RepositoryBudget;



@Service
public class BudgetService implements IBudgetService{
	
	@Autowired
	private RepositoryBudget repositoryBudget;
	
	
	@Override
	public Optional<Budget> listNoTxn(int noTxn) {
		return repositoryBudget.findById(noTxn);
	}

	@Override
	public int save(Budget b) {
		
if(b.getType().equals("Egreso")) {			
		 	b.setAmount(b.getAmount() * -1);
		}
		int res=0;
		Budget budget = repositoryBudget.save(b);
		if (budget != null) {
			res=1;
		} 		
		return res;
	}
		
	@Override
	public void delete(int noTxn) {
		repositoryBudget.deleteById(noTxn);		
	}

	@Override
	public Float sum() {
		Float res = 0F;
		if(repositoryBudget.sum() != null) {
			res = repositoryBudget.sum();
		}		
		return res;
	}

	@Override
	public List<Budget> lastTen() {
		return (List<Budget>)repositoryBudget.lastTen();
	}
}