# og-interview

Before run

1. Driver: I have include a Chrome driver for version 78. Please make sure your Chrome is using the same version; if you want to run for other browser, simply put the driver under resources/drivers
make sure you give the correct name to the executable. You can find the proper name under Class ContorlCenter
2. OS: It should work in both windows and Mac. Although I mainly develop in Mac, all windows configuration has been considered
3. You need to add your username and password to the user enum
go to java.com.test.constant.User enum and add your user name and password as the following, by doing this, you don't need to change the test

	YOUR_USER("Your username","your password");
or if you want, you can change the test as well to pass your username and password in the test

	loginPage.login("Your username", "Your password");
	
I overload the login function to have two different signatures

#Library
* I use selenium with TestNG for the sake of this interview question.
Everything is setup. All you need to do is to open Eclipse or other Java IDE. 
Import the project as Gradle. It should download all the jar for you.
* TestNG: https://testng.org/doc/documentation-main.html
* Selenium: Not sure if I need to explain this :)
* ExtendReport local: http://extentreports.com/

# Component Explanation: What is ..?

* IBaseElement: I wrap my own object around Selenium Element to enable better logging
* ControlCenter: An Singleton class that handle all the information flow through the execution of the test
* ComponentAdapter and Manager: I intent to build the framework as easy to add different component such as selenium for UI, rest for API, appium for mobile, or maybe db driver for future db connection. the adapter and manager allow the framework to easily add or remove the component involved in for a specific project
* Reporting: Extent report has been added to enrich the local report for better issue analysis locally. After each run, you will find a html report under folder reports.develop
* Logging: Log4j was added as well for logging purpose
* Listener: Two listener are implemented for extent report and console output, you can deactivate them by simply removing the listener from the run_test.xml file

# Package Structure
* All tests are store in test folder. I create two tests and 7 page object to go with it
* Page object can be found in folder 'page'

# How to run
Depends on the type/version of your Java IDE. you might need to install TestNG first before you can run it on your IDE.
If you have the IDE setup, simple right click on file run_test.xml and choose Run As > TestNG Suite