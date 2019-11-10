package com.page;

import com.components.se.elements.BasePage;
import com.components.se.enums.LocatorType;
import com.components.se.enums.Until;
import com.exception.UIException;
import com.test.constant.User;

public class LoginPage extends BasePage{

	public static final String USERNAME_INPUT = "login-username";
	public static final String PASSWORD_INPUT = "login-password";
	public static final String LOGIN_INPUT = "login-button";

	public LoginPage() throws UIException {
		super();
	}
	
	/**
	 * Login to application
	 * @param username
	 * @param password
	 * @return
	 * @throws UIException
	 */
	public HomePage login(String username, String password) throws UIException {
		this.findElement(USERNAME_INPUT, Until.Visible).setText(username);
		this.findElement(PASSWORD_INPUT, Until.Visible).setText(password);
		this.findElement(LOGIN_INPUT, Until.Clickable).click();
		
		return new HomePage();
	}

	/**
	 * Login to application
	 * @param user
	 * @return
	 * @throws UIException
	 */
	public HomePage login(User user) throws UIException {
		this.findElement(USERNAME_INPUT, LocatorType.ID, Until.Visible).setText(user.getUsername());
		this.findElement(PASSWORD_INPUT,  LocatorType.ID, Until.Visible).setText(user.getPassword());
		this.findElement(LOGIN_INPUT,  LocatorType.ID, Until.Clickable).click();
		
		return new HomePage();
	}
	
}
