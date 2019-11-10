package com.test.sample;

import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.base.OGBaseUITest;
import com.components.se.elements.IBaseElement;
import com.exception.UIException;
import com.page.HomePage;
import com.page.LandingPage;
import com.page.LoginPage;
import com.page.playlist.ShowPlayListPage;
import com.test.constant.User;

public class TestCreatePlayList extends OGBaseUITest {

	@Test(groups = { "og-interview" })
	public void testCreatePlayList() throws UIException {
		String playListName = "My Test Play List";

		reporter.info("Login to application");
		LandingPage landing = new LandingPage();
		LoginPage loginPage = landing.clickLoginBtn();
		
		reporter.info("Enter login credential");
		HomePage homePage = loginPage.login(User.YOUR_USER);
		
		reporter.info("Create Play List");
		ShowPlayListPage playlistPage = homePage
		.clickCreatePlayListBtn()
		.createPlayList(playListName);
		
		reporter.info("Verify the playlist is created and has correct name");
		assertion.verifyEquals(playlistPage.getName(), playListName);
		assertion.verifyEquals(playlistPage.getAuthor(), User.YOUR_USER.getUsername());
	}
	
	@Test(groups = { "og-interview" })
	public void testAddASongToExistingPlayList() throws UIException {
		/**
		 * This section below should be created by API
		 * but I don't have Spotify API, so I have no choice but to use UI :)
		 */
		String playListName = "My Exist Play List";

		reporter.info("Login to application");
		LandingPage landing = new LandingPage();
		LoginPage loginPage = landing.clickLoginBtn();
		
		reporter.info("Enter login credential");
		HomePage homePage = loginPage.login(User.YOUR_USER);
		
		reporter.info("Create Play List");
		ShowPlayListPage playlistPage = homePage
		.clickCreatePlayListBtn()
		.createPlayList(playListName);
		assertion.verifyEquals(playlistPage.getName(), playListName);
		assertion.verifyEquals(playlistPage.getAuthor(), User.YOUR_USER.getUsername());
		/**
		 * This section below should be created by API
		 * but I don't have Spotify API, so I have no choice but to use UI :)
		 */
		
		reporter.info("Search a song and added to the play list");
		homePage
		.clickSearchBtn()
		.searchSong("Beethoven No.9")
		.rightClickOnFirstSearchResult()
		.clickOnAddToPlayList()
		.clickOnPlaylist(playListName);
		
		reporter.info("Verify the song is added to the playlist");
		List<IBaseElement> songs = homePage.clickExistPlaylist(playListName).getSongsInPlayList();
		assertion.verifyEquals(songs.size(), 1);
	}
	
	@AfterMethod(alwaysRun = true)
	public void logout() throws UIException {
		reporter.info("Logout");
		new HomePage().clickUserLink().logout();
	}
}
