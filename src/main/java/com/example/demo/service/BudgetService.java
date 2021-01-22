package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.modelo.Budget;
import com.example.demo.repository.RepositoryBudget;
import com.example.demo.repository.UserRepository;



@Service
public class BudgetService implements IBudgetService{
	
	@Autowired
	private RepositoryBudget repositoryBudget;
	

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public Optional<Budget> listNoTxn(int noTxn) {
		return repositoryBudget.findById(noTxn);
	}

	
	@Override
	public int save(Budget b) {
		
if(b.getType().equals("Pagos")) {			
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
	public Float sum() throws Exception {
		Float res = 0F;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		User myUser = userRepository.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		
		Long userId=myUser.getId();
										
		if(repositoryBudget.sum(myUser.getId()) != null) {
			res = repositoryBudget.sum(myUser.getId());
		}		
		return res;
	}


	@Override
	public List<Budget> lastTen() throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		User myUser = userRepository.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		
		Long userId=myUser.getId();
						
		
		return (List<Budget>)repositoryBudget.lastTen(userId);
	}

}