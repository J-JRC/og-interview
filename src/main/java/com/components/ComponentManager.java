package com.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.components.se.SeleniumAdapter;
import com.enums.Component;

public class ComponentManager {
	private static final Logger LOGGER = Logger.getLogger(ComponentManager.class);

	protected List<ComponentAdapter> components;
	protected Map<Component, ComponentAdapter> componentsMap;
	protected Map<String,String> parameters;
	
	
	public ComponentManager(Map<String,String> parameters) throws Exception {
		this.components = new ArrayList<ComponentAdapter>();
		this.componentsMap = new HashMap<Component, ComponentAdapter>();
		this.parameters = parameters;
	}
	
	public void register(Component component) throws Exception {
		ComponentAdapter test = null;
		if (component.equals(Component.SELENIUM)) {
			test = new SeleniumAdapter(parameters);

		} else {
			throw new Exception("Cannot find Component "+ component);
		}
		
		components.add(test);
		componentsMap.put(component, test);
		LOGGER.debug("component size: " + components.size());
	}
	
	public void initilizeAll() throws Exception {
		for(ComponentAdapter ta: components) {
			ta.initilize();
		}
	}
	
	public void initilize(Component component) throws Exception {
		if(componentsMap.containsKey(component)) {
			componentsMap.get(component).initilize();
		}
	}

	
	public void destory(Component component) throws Exception {
		if(componentsMap.containsKey(component)) {
			componentsMap.get(component).destory();
		}
	}
	
	public WebDriver getWebDriver() {
		if(componentsMap.containsKey(Component.SELENIUM)) {
			return ((SeleniumAdapter) componentsMap.get(Component.SELENIUM)).getDriver();
		}
		return null;
	}
}
