package com.page.playlist;

import com.components.se.elements.BasePage;
import com.components.se.enums.Until;
import com.exception.UIException;

public class ShowPlayListPopPage extends BasePage {
	public ShowPlayListPopPage() throws UIException {
		super();
	}
	
	
	public void clickOnPlaylist(String playlistName) throws UIException {
		/**
		 * okay what am I doing here, this is a very bad way to locate a element
		 * but the only way to make click on the playlist is to click on the cover, and to click on the cover we need to
		 * find it based on the playlist that is a child element of a the cover element's following sibling.
		 * 
		 * I wrote this due to this interview, but in real life I will avoid this as much as possible by ask developer to add something easier
		 * for automator to use
		 */
		String xpath = 	"//*[@class='mo-info']//span[text()='"+playlistName+"']/ancestor::div[@class='mo-info']/preceding-sibling::div/div";

		findElement(xpath, Until.Clickable).click();
	}
}
