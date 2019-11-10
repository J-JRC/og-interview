package com.page.contextmenu;

import com.components.se.elements.BasePage;
import com.components.se.enums.Until;
import com.exception.UIException;
import com.page.playlist.ShowPlayListPopPage;

public class ContextMenuPage extends BasePage {
	/*
	 * Menu
	 */
	public static final String ADD_TO_PLAYLIST_BTN = "//div[@class='react-contextmenu-item' and text()='Add to Playlist']";
	
	public ContextMenuPage() throws UIException {
		super();
	}
	
	public ShowPlayListPopPage clickOnAddToPlayList() throws UIException {
		findElement(ADD_TO_PLAYLIST_BTN, Until.Clickable).click();
		return new ShowPlayListPopPage();
	}
}
