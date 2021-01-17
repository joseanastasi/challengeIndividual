package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository repository;
		
	
		@Override
	public Iterable<Role> getAllroles() {
		return repository.findAll();
	}


	@Override
	public Role findByName(String role) {
		return repository.findByName(role);
	}

	
	
	
}