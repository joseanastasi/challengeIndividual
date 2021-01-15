package com.example.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Budget;

@Repository
//TODO: cambiar nombre clase a BudgetRepository
public interface IBudget extends JpaRepository<Budget, Integer> {
	
	@Query(value = "SELECT sum(amount) FROM budget", nativeQuery = true)
	//TODO: en vez que String tiene que ser Float
	String sum();
	
	@Query(value = "SELECT * FROM budget order by date LIMIT 10;", nativeQuery = true)
	//TODO: debe ser lastTen
	List<Budget> lasTen();
}