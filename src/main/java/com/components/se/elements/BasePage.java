package com.components.se.elements;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.components.se.enums.LocatorType;
import com.components.se.enums.Until;
import com.components.se.factory.BasePageFactory;
import com.components.se.gateways.BasicBrowserHelper;
import com.components.se.gateways.BasicElementGateway;
import com.components.se.gateways.BasicPageGateway;
import com.components.se.gateways.LocatorUtil;
import com.components.se.implement.BaseAnnotations;
import com.components.se.implement.DefaultElementLocator;
import com.control.ControlCenter;
import com.enums.ContextConstant;
import com.exception.UIException;
import com.reporting.ReportAdapter;

/**
 * @author yizhong.ji
 *
 */
public abstract class BasePage {
	public static final int TIMEOUT = Integer.parseInt(ControlCenter.getInstance().getParameter(ContextConstant.TIMEOUT));
	
	protected ReportAdapter reporter = ControlCenter.getInstance().getReporter();
	protected WebDriver driver;
	protected String windowHandle;
	// for logging purpose
	protected String footprint;

	/**
	 * initialize page with the webDriver
	 * 
	 * @param driver:WebDriver
	 * @throws UIException
	 *             throws a UIException
	 */
	public BasePage(WebDriver driver) throws UIException {
		try {
			this.driver = driver;
			// set main window handle
			windowHandle = BasicBrowserHelper.getCurrentWindowHandle(this.driver);

			// for logging purpose
			footprint = getNavigation(this.getClass(), "");
			BasePageFactory.initPage(this.driver, this);
		} catch (Exception e) {
			throw new UIException("initialize page object failed with an exception: ", e);
		}
	}

	/**
	 * initialize page with the webDriver
	 * 
	 * @param driver:WebDriver
	 * @throws UIException
	 *             throws a UIException
	 */
	public BasePage() throws UIException {
		try {
			if (ControlCenter.getInstance().getComponentManager().getWebDriver() == null) {
				throw new UIException("You need to instantiate web driver first");
			}
			this.driver = ControlCenter.getInstance().getComponentManager().getWebDriver();
			// set main window handle
			windowHandle = BasicBrowserHelper.getCurrentWindowHandle(this.driver);

			// for logging purpose
			footprint = getNavigation(this.getClass(), "");
			BasePageFactory.initPage(this.driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UIException("initialize page object failed with an exception: ", e);
		}
	}

	/**
	 * This public method for usage tracking
	 * 
	 * @param c:Class
	 * @param results:String
	 * @return String
	 */
	private String getNavigation(Class<?> c, String results) {
		if (c.getSuperclass() == null) {
			return "";
		}
		if (c.getSuperclass().getSimpleName().endsWith("BasePage")) {
			return c.getSimpleName();
		}
		results += getNavigation(c.getSuperclass(), results) + " -> " + c.getSimpleName();
		return results;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getFootPrint() {
		return footprint;
	}

	public String getWindowHandle() {
		return windowHandle;
	}
	
	public void scrollIntoView(IBaseElement elt, boolean isAlignTop) {
		BasicPageGateway.scrollIntoView(driver, elt.getWrappedElement(), isAlignTop);
	}
	
	/**
	 * find element based on locator and synchronization type
	 * 
	 * @param locator:String
	 * @param syncType:Until
	 * @throws UIException
	 *             throws a UIException
	 * @return BaseElement
	 */
	public IBaseElement findElement(String locator, Until waitUntil) throws UIException {
		IBaseElement baseElement = null;
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			baseElement = new BaseElement(driver,
					(WebElement) method.invoke(null, driver, LocatorUtil.determineByType(locator), TIMEOUT), locator,
					locator, footprint);
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}
		return baseElement;
	}

	/**
	 * find element based on locator and synchronization type with timeout
	 * 
	 * @param locator:String
	 * @param syncType:Until
	 * @param timeout:int
	 * @throws UIException
	 *             throws a UIException
	 * @return BaseElement
	 */
	public IBaseElement findElement(String locator, Until waitUntil, int timeout) throws UIException {
		IBaseElement baseElement = null;
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			baseElement = new BaseElement(driver,
					(WebElement) method.invoke(null, driver, LocatorUtil.determineByType(locator), timeout), locator,
					new DefaultElementLocator(driver, new BaseAnnotations(waitUntil), timeout), footprint);
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}
		return baseElement;
	}

	/**
	 * find element based on locator and synchronization type
	 * 
	 * @param locator:String
	 * @param type:LocatorType
	 * @param syncType:Until
	 * @return BaseElement
	 * @throws UIException
	 *             throws a UIException
	 */
	public IBaseElement findElement(String locator, LocatorType type, Until waitUntil) throws UIException {
		IBaseElement baseElement = null;
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			baseElement = new BaseElement(driver,
					(WebElement) method.invoke(null, driver, LocatorUtil.determineByType(locator, type), TIMEOUT),
					locator, locator, footprint);
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * find element based on locator and synchronization type
	 * 
	 * @param locator:String
	 * @param type:LocatorType
	 * @param syncType:Until
	 * @param timeout:int
	 * @return BaseElement
	 * @throws UIException
	 *             throws a UIException
	 */
	public IBaseElement findElement(String locator, LocatorType type, Until waitUntil, int timeout) throws UIException {
		IBaseElement baseElement = null;
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			baseElement = new BaseElement(driver,
					(WebElement) method.invoke(null, driver, LocatorUtil.determineByType(locator, type), timeout),
					locator, locator, footprint);
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * find element based on locator and synchronization types
	 * 
	 * @param locator:String
	 * @param syncType:Until
	 * @throws UIException
	 *             throws a UIException
	 * @return BaseElement
	 */
	@SuppressWarnings("unchecked")
	public List<IBaseElement> findElements(String locator, Until waitUntil) throws UIException {
		List<IBaseElement> baseElement = new ArrayList<IBaseElement>();
		if (!Until.ElementsPresent.getMethodName().equals(waitUntil.getMethodName())) {
			throw new UIException("initializeElements does not support the HowTo " + waitUntil.getMethodName());
		}
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			List<WebElement> temp = (List<WebElement>) method.invoke(null, driver, LocatorUtil.determineByType(locator), TIMEOUT);
			for (WebElement e : temp) {
				baseElement.add(new BaseElement(driver, e, locator, locator, footprint));
			}
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * find element based on locator and synchronization types with timeout
	 * 
	 * @param locator:String
	 * @param syncType:Until
	 * @param timeout:int
	 * @throws UIException
	 *             throws a UIException
	 * @return BaseElement
	 */
	@SuppressWarnings("unchecked")
	public List<IBaseElement> findElements(String locator, Until waitUntil, int timeout) throws UIException {

		List<IBaseElement> baseElement = new ArrayList<IBaseElement>();
		if (!Until.ElementsPresent.getMethodName().equals(waitUntil.getMethodName())) {
			throw new UIException("initializeElements does not support the HowTo " + waitUntil.getMethodName());
		}

		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			List<WebElement> temp = (List<WebElement>) method.invoke(null, driver, LocatorUtil.determineByType(locator),
					timeout);
			for (WebElement e : temp) {
				baseElement.add(new BaseElement(driver, e, locator, locator, footprint));
			}
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * find element based on locator and synchronization types
	 * 
	 * @param locator:String
	 * @param type:LocatorType
	 * @param syncType:Until
	 * @return BaseElement
	 * @throws UIException
	 *             throws a UIException
	 */
	@SuppressWarnings("unchecked")
	public List<IBaseElement> findElements(String locator, LocatorType type, Until waitUntil) throws UIException {

		List<IBaseElement> baseElement = new ArrayList<IBaseElement>();
		if (!Until.ElementsPresent.getMethodName().equals(waitUntil.getMethodName())) {
			throw new UIException("This method does not support the given method " + waitUntil.getMethodName());
		}
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			List<WebElement> temp = (List<WebElement>) method.invoke(null, driver,
					LocatorUtil.determineByType(locator, type), TIMEOUT);
			for (WebElement e : temp) {
				baseElement.add(new BaseElement(driver, e, locator, locator, footprint));
			}
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * find element based on locator and synchronization types
	 * 
	 * @param locator:String
	 * @param type:LocatorType
	 * @param syncType:Until
	 * @param timeout:int
	 * @return BaseElement
	 * @throws UIException
	 *             throws a UIException
	 */
	@SuppressWarnings("unchecked")
	public List<IBaseElement> findElements(String locator, LocatorType type, Until waitUntil, int timeout)
			throws UIException {
		List<IBaseElement> baseElement = new ArrayList<IBaseElement>();
		if (!Until.ElementsPresent.getMethodName().equals(waitUntil.getMethodName())) {
			throw new UIException("This method does not support the given method " + waitUntil.getMethodName());
		}
		try {
			Method method = BasicElementGateway.class.getDeclaredMethod(waitUntil.getMethodName(),
					new Class[] { WebDriver.class, By.class, int.class });
			List<WebElement> temp = (List<WebElement>) method.invoke(null, driver,
					LocatorUtil.determineByType(locator, type), timeout);
			for (WebElement e : temp) {
				baseElement.add(new BaseElement(driver, e, locator, locator, footprint));
			}
		} catch (Exception e) {
			throw new UIException("find element based on locator and synchronization type failed witn an exception: ", e);
		}

		return baseElement;
	}

	/**
	 * To a new frame.
	 * 
	 * @param frame:String
	 * @throws UIException
	 *             throws a UIException
	 */
	public void switchToFrame(String frame) throws UIException {
		BasicPageGateway.waitForFrameToLoadAndSwitch(driver, frame, TIMEOUT);
	}

	/**
	 * Switch to Default content.
	 */
	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Open new Window.
	 * 
	 * @return an open window
	 * @throws UIException
	 */
	public String openWindow() throws UIException {
		return BasicBrowserHelper.openWindow(driver);
	}

	/**
	 * Switch to the given Window/tab from the current page object. For example:
	 * page.switchToWindow(otherWindow)
	 * 
	 * @param windowHandle:String
	 * @throws UIException
	 */
	public void switchToWindow(String windowHandle) throws UIException {
		BasicBrowserHelper.switchToWindow(driver, windowHandle);
	}

	/**
	 * Switch to the Window/tab the current page object belongs to. For example:
	 * page.switchToWindow(otherWindow)
	 * 
	 * @throws UIException
	 */
	public void switchToWindow() throws UIException {
		BasicBrowserHelper.switchToWindow(driver, windowHandle);
	}

	/**
	 * Close Window/tab that the current page object belongs to.
	 * 
	 * @throws UIException
	 */
	public void closeWindow() throws UIException {
		BasicBrowserHelper.closeWindow(driver, windowHandle);
	}

	/**
	 * Take a screenshot of the current page
	 * 
	 * @return a File contains screenshot
	 * @throws UIException
	 *             throws a UIException
	 */
	public File takeScreenShot() throws UIException {
		return BasicPageGateway.saveAsScreenShot(driver);
	}
	
	protected void waitImplicitly(int seconds) throws Exception {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			throw new Exception("Something is wrong when try to wait for operations in Thread.sleep");
		}
	}
}
