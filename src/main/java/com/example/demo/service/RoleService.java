package com.example.demo.service;



import com.example.demo.entity.Role;

public interface RoleService {

	public Iterable<Role> getAllroles();


	public Role findByName(String role);
	
	
	
	
}

