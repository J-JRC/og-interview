package com.page.playlist;

import java.util.List;

import com.components.se.elements.BasePage;
import com.components.se.elements.IBaseElement;
import com.components.se.enums.Until;
import com.exception.UIException;

public class ShowPlayListPage extends BasePage {

	/*
	 * Show PlayList elements
	 * 
	 */
	// these two sets of selector only shows up one at a time due to the screen resolution.
	// when the width of the application smaller than 1200, the small locator will be visible
	public static final String PLAYLIST_NAME_LARGE = ".mo-info span";
	public static final String PLAYLIST_CREATED_BY_LARGE = ".mo-meta span a"; 
	
	// Another crazy xpath
	// I don't really have a better way to distinguish the actual song and recommended song
	public static final String SONG_LIST = "//section[not(contains(@class,'PlaylistRecommendedTracks__list'))]/ol[@class='tracklist']//li[@class='tracklist-row']";

	public static final String PLAYLIST_NAME_SMALL = ".TrackListHeader__entity-name span";
	public static final String PLAYLIST_CREATED_BY_SMALL = ".inputBox-input"; // this class is too normal and has risk of being changed, we need ask somthing more unique

	
	public ShowPlayListPage() throws UIException {
		super();
	}

	/**
	 * when the width of the application smaller than 1200, the small locator will be visible
	 * @return
	 * @throws UIException
	 */
	public String getName() throws UIException {
		if(isScreenLarge()) {
			return this.findElement(PLAYLIST_NAME_LARGE, Until.Visible).getText();
		}else {
			return this.findElement(PLAYLIST_NAME_SMALL, Until.Visible).getText();
		}
	}
	
	/**
	 * when the width of the application smaller than 1200, the small locator will be visible
	 * @return
	 * @throws UIException
	 */
	public String getAuthor() throws UIException {
		if(isScreenLarge()) {
			return findElement(PLAYLIST_CREATED_BY_LARGE, Until.Visible).getText();
		}else {
			return findElement(PLAYLIST_NAME_SMALL, Until.Visible).getText();
		}
	}
	
	
	public List<IBaseElement> getSongsInPlayList() throws UIException{
		return findElements(SONG_LIST, Until.ElementsVisible);
	}
	/**
	 * Private methods
	 */
	/**
	 * a function determine if the current screen resolution is small or not
	 * seems the application's responsive design break at 1200 width.
	 * we will assume for now that if the width of the screen is smaller than 1200, we consider it as small
	 * otherwise, it is large
	 */
	private boolean isScreenLarge() {
		return (this.driver.manage().window().getSize().getWidth() >= 1200);
	}
}
