package com.components.se.gateways;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.control.ControlCenter;
import com.exception.UIException;
import com.reporting.ReportAdapter;

public class BasicElementGateway {
	protected static ReportAdapter reporter = ControlCenter.getInstance().getReporter();

	private BasicElementGateway(){
	}
	
	/**
	 * Find element based on if the element is present
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the driver to find the element based on
	 * @param timeout int
	 * @return WebElement
	 * @throws UIException 
	 */
	public static WebElement findElementByPresence(WebDriver driver, By locator, int timeout)
			throws UIException {
		WebElement webElement = null;
		try {
			webElement = CustomSeleniumGateway.presenceOfElementLocated(driver, locator, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : findElementByPresence: " + "[ " + locator + " ]" + " with timeout: ", e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("findElementByPresence: " + "[ " + locator + " ]" + " with timeout: " + timeout, e);
		}
		return webElement;
	}

	/**
	 * Find element based on if the element is NOT present, by default the timeout is 1 second
	 * meaning if the framework does not find the element within 1 second it will assume it is not present
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the driver to find the element based on
	 * @param timeout int
	 * @return WebElement
	 * @throws UIException 
	 */
	public static WebElement findElementByNotPresence(WebDriver driver, By locator, int timeout)
			throws UIException {
		WebElement webElement = null;
		try {
			webElement = CustomSeleniumGateway.presenceOfElementLocated(driver, locator, 1);
		} catch (Exception e) {
			reporter.info("findElementByNotPresence: " + "[ " + locator + " ]" + " with timeout: ", " " , BasicPageGateway.saveAsScreenShot(driver));
		}
		return webElement;
	}
	
	/**
	 * Find all elements based on if the element is present
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the page.driver to find the element based on
	 * @param timeout int           
	 * @return List. list of WebElement
	 * @throws UIException 
	 */
	public static List<WebElement> findElementsByPresence(WebDriver driver, By locator, int timeout)
			throws UIException {
		List<WebElement> webElements = null;
		try {
			webElements = CustomSeleniumGateway.presenceOfAllElementsLocatedBy(driver, locator, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : findElementsByPresence: " + "[ " + locator + " ]" + " with timeout: ", e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("findElementsByPresence: " + "[ " + locator + " ]" + " with timeout: " + timeout, e);
		}

		return webElements;
	}
	/**
	 * Find element based on if the element is clickable
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the driver to find the element based on
	 * @param timeout int
	 * @return WebElement
	 * @throws UIException 
	 */
	public static WebElement findElementByClickable(WebDriver driver, By locator, int timeout)
			throws UIException {
		WebElement webElement = null;
		try {
			webElement = CustomSeleniumGateway.elementToBeClickable(driver, locator, timeout);
		} catch (Exception e) {
			reporter.fail("findElementByClickable: " + "[ " + locator + " ]" + " with timeout: ", e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("findElementByClickable: " + "[ " + locator + " ]" + " with timeout: " + timeout, e);
		}
		return webElement;
	}

	/**
	 * Find element based on if the element is visible
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the driver to find the element based on
	 * @param timeout int
	 * @return WebElement
	 * @throws UIException 
	 */
	public static WebElement findElementByVisible(WebDriver driver, By locator, int timeout)
			throws UIException {
		WebElement webElement = null;
		try {
			webElement = CustomSeleniumGateway.visibilityOfElementLocated(driver, locator, timeout);
		} catch (Exception e) {
			reporter.fail("findElementByVisible: " + "[ " + locator + " ]" + " with timeout: ", e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("findElementByVisible: " + "[ " + locator + " ]" + " with timeout: " + timeout, e);
		}
		return webElement;

	}

	/**
	 * Find element based on if the element is invisible
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            locator defined in the properties file specific to the current
	 *            page for the the driver to find the element based on
	 * @param timeout int
	 * @return WebElement
	 * @throws UIException 
	 */
	public static WebElement findElementByInvisible(WebDriver driver, By locator, int timeout)
			throws UIException {
		WebElement webElement = null;
		try {
			CustomSeleniumGateway.waitUntilElementIsInvisible(driver, locator, timeout);
		} catch (Exception e) {
			reporter.fail("findElementByInvisible: " + "[ " + locator + " ]" + " with timeout: ", e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("findElementByInvisible: " + "[ " + locator + " ]" + " with timeout: " + timeout, e);
		}
		return webElement;
	}

	
	
	/**
	 * Wait until the element detached from DOMt. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the enable to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementNotPresent(WebDriver driver, WebElement element, int timeout) throws UIException {
		try {
			CustomSeleniumGateway.waitUntilElementIsStale(driver, element, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : waitForElementToEnable: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("waitForElementToEnable: [" + element.toString() + "]", e);
		}
	}
	
	/**
	 * Wait until given element become Clickable. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the Click to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementToClickable(WebDriver driver, WebElement element, int timeout) throws UIException {
		try {
			CustomSeleniumGateway.waitForElementToBeClickable(driver, element, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : waitForElementToClickable: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("waitForElementToClickable: [" + element.toString() + "]", e);
		}
	}
	
	/**
	 * Wait until the enable of given element. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the enable to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementToEnable(WebDriver driver, WebElement element, int timeout) throws UIException {
		try {
			CustomSeleniumGateway.waitForElementToBeEnabled(driver, element, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : waitForElementToEnable: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("waitForElementToEnable: [" + element.toString() + "]", e);
		}
	}
	
	/**
	 * Wait until the disable of given element. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the enable to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementToDisable(WebDriver driver, WebElement element, int timeout) throws UIException {
		try {
			CustomSeleniumGateway.waitForElementToBeDisabled(driver, element, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : waitForElementToDisable: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("waitForElementToDisable: [" + element.toString() + "]", e);
		}
	}

	


	


	/**
	 * Wait until the display of given element. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the enable to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementToDisplay(WebDriver driver, WebElement element, int timeout) throws UIException {		
		try {
			CustomSeleniumGateway.waitForElementToBeDisplayed(driver, element, timeout);
		} catch (Exception e) {
			if(element == null) {
				reporter.fail("[FAILED] : waitForElementToDisplay with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
				throw new UIException("waitForElementToDisplay ", e);
			}else {
				reporter.fail("[FAILED] : waitForElementToDisplay: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
				throw new UIException("waitForElementToDisplay: [" + element.toString() + "]", e);
			}
		}
	}


	/**
	 * Wait until the display of given element. 
	 * @param driver
	 *            selenium webdriver
	 * @param element
	 *            Predefined web element for the enable to check upon
	 * @param timeout:int
	 *				Timeout either from user or context file parameter
	 * @throws UIException 
	 */
	public static void waitForElementToDisappear(WebDriver driver, WebElement element, int timeout) throws UIException {		
		try {
			CustomSeleniumGateway.waitForElementToBeDisappear(driver, element, timeout);
		} catch (Exception e) {
			reporter.fail("[FAILED] : waitForElementToDisppear: " + "[ " + element.toString() + " ]" + " with timeout: " + timeout, e, BasicPageGateway.saveAsScreenShot(driver));
			throw new UIException("waitForElementToDisppear: [" + element.toString() + "]", e);
		}
	}
}
