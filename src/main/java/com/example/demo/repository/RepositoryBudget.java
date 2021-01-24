package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.modelo.Budget;

@Repository

public interface RepositoryBudget extends JpaRepository<Budget, Integer> {
	
	
	
	@Query(value = "SELECT sum(amount) FROM budget WHERE user_id = :id", nativeQuery = true)
	Float sum(@Param("id") long id);
			
	@Query(value = "SELECT * FROM budget_manager.budget WHERE user_id= :id order by date LIMIT 10;", nativeQuery = true)
	List<Budget> lastTen(@Param("id") long id);
		
}				

