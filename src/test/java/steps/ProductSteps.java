package steps;

import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BasePage;
import utilities.DriverFactory;


public class ProductSteps extends DriverFactory {
	
    @Given("^user navigates to \"([^\"]*)\" website$")
    public void user_navigates_to_something_website(String URL) throws Throwable {
		BasePage basePage = new BasePage();
		basePage.waitForSpecificPage(URL);
		getDriver().get(URL);
		
    }
    
    
	@When("^user clicks on Special Offer text link as \"([^\"]*)\"$")
	public void user_clicks_on_Special_Offer_text_link_as(String ELEMENTLOCATOR) throws Throwable {
		BasePage basePage = new BasePage();
		basePage.WaitUntilWebElementIsVisibleUsingByLocator(By.cssSelector(ELEMENTLOCATOR));
		getDriver().findElement(By.cssSelector(ELEMENTLOCATOR)).click();
	}

	@Then("^user should be presented with a promo alert as \"([^\"]*)\"$")
	public void user_should_be_presented_with_a_promo_alert_as(String arg1) throws Throwable {
		productsPage.printSpecialOffersVoucherCode();
		productsPage.clickOnProceedButton_Popup();

	}

}
