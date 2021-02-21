package com.azart.entities;

public class User {
	private String _id;
	private String email;
	private String name;
	private String password;
	private String role;

	public User() {
	}

	public User(String _id, String email, String name, String password, String role) {
		this._id = _id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
