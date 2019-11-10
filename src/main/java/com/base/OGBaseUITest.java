package com.base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

import com.enums.Component;
import com.enums.ContextConstant;

public class OGBaseUITest extends BaseTest {
	private static final Logger logger = Logger.getLogger(OGBaseUITest.class);

	/**
	 * @throws Exception 
	 * 
	 */
	@BeforeClass(alwaysRun = true)
	public void beforeClassCustom() throws Exception {
		try {
			this.controlCenter.getComponentManager().register(Component.SELENIUM);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	
	/**
	 * 
	 */
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodCustom(Method m, ITestContext context, ITestResult testResult) throws Exception {
		try {
			this.controlCenter.getComponentManager().initilize(Component.SELENIUM);
			this.controlCenter.getComponentManager().getWebDriver().get(controlCenter.getParameter(ContextConstant.APPLICATION_SERVER));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@AfterMethod(alwaysRun=true)
	public void afterMethodCustom() throws Exception {
		try {
			this.controlCenter.getComponentManager().destory(Component.SELENIUM);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
}
