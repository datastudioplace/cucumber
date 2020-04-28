package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pages.BasePage;
import utilities.DriverFactory;

public class MasterHooks extends DriverFactory {
	
	@Before
	public void setup() {
		driver = getDriver();
		log.info("Launching the browser.........");
	}
	
	@After
	public void tearDownAndScreenshotOnFailure(Scenario scenario) {
		try {
			if(driver != null && scenario.isFailed()) {
				//scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				BasePage.captureScreenshot();
				log.info("deleting the cookies.........");
				driver.manage().deleteAllCookies();
				log.info("cookies deleted.........");
				driver.quit();
				driver = null;
			}
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			System.out.println("Methods failed: tearDownAndScreenshotOnFailure, Exception: " + e.getMessage());
			log.info("Methods failed: tearDownAndScreenshotOnFailure, Exception: "+ e.getMessage());
		}
}
}
