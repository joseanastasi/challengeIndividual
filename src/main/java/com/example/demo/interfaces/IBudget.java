package com.example.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Budget;

@Repository
public interface IBudget extends JpaRepository<Budget, Integer> {
	
	@Query(value = "SELECT sum(amount) FROM budget", nativeQuery = true)
	String sum();
	
	@Query(value = "SELECT * FROM budget order by type LIMIT 10;", nativeQuery = true)
	List<Budget> lasTen();
}