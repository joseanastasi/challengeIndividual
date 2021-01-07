package com.example.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.demo.modelo.Budget;

public interface IBudgetService {
	public Optional<Budget>listNrotrans(int nrotrans);
	public int save(Budget b);
	public void delete(int id);
	public String sum();
	public List<Budget> lastTen();
}