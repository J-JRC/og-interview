package com.test.constant;

public enum User {
	YOUR_USER("YOUR USERNAME","YOUR PASSWORD");
	String username;
	String password;

	private User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
