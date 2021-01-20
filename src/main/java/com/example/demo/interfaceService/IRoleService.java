package com.example.demo.interfaceService;



import com.example.demo.entity.Role;

public interface IRoleService {

	public Iterable<Role> getAllroles();


	public Role findByName(String role);
	
	
	
	
}

