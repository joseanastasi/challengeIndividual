package com.example.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.modelo.Budget;

public interface IBudgetService {
	public Optional<Budget>listNoTxn(int noTxn);
	public void delete(int id);
	public Float sum() throws Exception;
	public List<Budget> lastTen() throws Exception;
	int save(Budget b);
}