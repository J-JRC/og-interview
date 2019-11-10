package com.page;

import com.components.se.elements.BasePage;
import com.components.se.enums.Until;
import com.exception.UIException;

public class LandingPage extends BasePage {
	/**
	 * All the locater I am using are not the best choice at all
	 * But the unique class come with the element does seems like auto generated
	 * I am not 100% sure if it will change when the test run in a different computer or IP address
	 * Therefore, I decide to use XPath. During really automation scenario, this is where we need to
	 * ask developer to add unique id or class for us to find the element.
	 */
	public static final String LOGIN_BTN = "//button[text()='Log in']";

	public LandingPage() throws UIException {
		super();
	}
	
	public LoginPage clickLoginBtn() throws UIException {
		this.findElement(LOGIN_BTN, Until.Clickable).click();
		return new LoginPage();
	}
}
