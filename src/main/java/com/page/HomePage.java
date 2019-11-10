package com.page;

import java.util.List;

import org.openqa.selenium.By;

import com.components.se.elements.BasePage;
import com.components.se.elements.IBaseElement;
import com.components.se.enums.Until;
import com.exception.UIException;
import com.page.contextmenu.ContextMenuPage;
import com.page.playlist.ShowPlayListPage;

public class HomePage extends BasePage {

	/*
	 * Search button
	 */
	public static final String SEARCH_BTN = "//span[@class='navbar-link__text' and text()='Search']/ancestor::a";
	public static final String SEARCH_INPUT = "[data-testid='search-input']";
	public static final String SEARCH_RESULT = "[data-testid='search-tracks-result']";

	/*
	 * Create PlayList elements
	 * 
	 */
	public static final String START_CREATE_PLAYLIST_BTN = ".CreatePlaylistButton__text";
	public static final String PLAYLIST_NANE_FIELD = ".inputBox-input"; // this class is too normal and has risk of
																		// being changed, we need ask somthing more
																		// unique
	public static final String CREATE_PLAYLIST_BTN = ".btn-green"; // this class is too normal and has risk of being
																	// changed, we need ask somthing more unique
	public static final String EXIST_PLAYLIST_NAME = "//*[@class='RootlistItemPlaylist__text-wrapper']/span[text()='%s']";

	/*
	 * User link
	 */
	public static final String USER_LNK = ".UserWidget__link";

	public HomePage() throws UIException {
		super();
	}

	/*
	 * Search and Results
	 */

	public HomePage clickSearchBtn() throws UIException {
		findElement(SEARCH_BTN, Until.Clickable).clickAsJScript();
		return this;
	}

	public HomePage searchSong(String searchTxt) throws UIException {
		findElement(SEARCH_INPUT, Until.Clickable).setText(searchTxt);
		return this;
	}

	public ContextMenuPage rightClickOnFirstSearchResult() throws UIException {
		List<IBaseElement> results = getSearchResults();
		if (results.isEmpty()) {
			throw new UIException("Cannot find any search result");
		}
		results.get(0).findElement(By.cssSelector(".react-contextmenu-wrapper div")).rightClick();
		return new ContextMenuPage();
	}

	public List<IBaseElement> getSearchResults() throws UIException {
		List<IBaseElement> results = findElements(SEARCH_RESULT, Until.ElementsVisible);
		return results;
	}

	/*
	 * Play List Section
	 */

	/**
	 * click on the create play list button from the side menu
	 * 
	 * @return
	 * @throws UIException
	 */
	public HomePage clickCreatePlayListBtn() throws UIException {
		findElement(START_CREATE_PLAYLIST_BTN, Until.Clickable).click();
		return this;
	}

	/**
	 * Click on exist playlist with the provided name
	 * 
	 * @param playlistName
	 * @return
	 * @throws UIException
	 */
	public ShowPlayListPage clickExistPlaylist(String playlistName) throws UIException {
		findElement(String.format(EXIST_PLAYLIST_NAME, playlistName), Until.Clickable).clickAsJScript();
		return new ShowPlayListPage();
	}

	/**
	 * Create a play list from pop up
	 * 
	 * @param playlistName
	 * @return
	 * @throws UIException
	 */
	public ShowPlayListPage createPlayList(String playlistName) throws UIException {
		findElement(PLAYLIST_NANE_FIELD, Until.Visible).setText(playlistName);
		// TODO: INVESTIGATE Why click as Javascript here
		// there is a strange behavior happening here and I cannot really know the
		// reason unless I can
		// talk to developer.
		// the problem is since the creation of the play list is from a pop over like
		// UI.
		// when entering the name, Selenium seems to think the send text finished and
		// then click on the button right way (only happen sometime)
		// where as in reality the rendering and animation still going on the screen.
		// by using click as Javascript, I bypass the UI simulation.
		findElement(CREATE_PLAYLIST_BTN, Until.Clickable).clickAsJScript();
		return new ShowPlayListPage();
	}
	
	/*
	 * User link
	 */
	/**
	 * click on user link and return a user profile page
	 * @return
	 * @throws UIException
	 */
	public UserProfilePage clickUserLink() throws UIException {
		findElement(USER_LNK, Until.Clickable).click();
		return new UserProfilePage();
	}
}
