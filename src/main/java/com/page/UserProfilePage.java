package com.page;

import com.components.se.elements.BasePage;
import com.components.se.enums.Until;
import com.exception.UIException;

public class UserProfilePage extends BasePage{
	public static final String LOGOUT = ".logout-button";

	public UserProfilePage() throws UIException {
		super();
	}
	
	public void logout() throws UIException {
		this.findElement(LOGOUT, Until.Clickable).click();
	}	
}
