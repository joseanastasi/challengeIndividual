package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Budget;

@Repository

public interface RepositoryBudget extends JpaRepository<Budget, Integer> {
	
	@Query(value = "SELECT sum(amount) FROM budget", nativeQuery = true)
	Float sum();
	
	@Query(value = "SELECT * FROM budget order by date LIMIT 10;", nativeQuery = true)
	
	List<Budget> lastTen();
}