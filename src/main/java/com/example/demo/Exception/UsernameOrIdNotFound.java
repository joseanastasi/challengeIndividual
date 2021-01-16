package com.example.demo.Exception;

public class UsernameOrIdNotFound extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5375227924909757596L;

	public UsernameOrIdNotFound() {
		super("Usuario o Id no encontrado");
	}
	
	public UsernameOrIdNotFound(String message) {
		super(message);
	}

}
