package steps;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BasePage;

public class ContactUsSteps extends BasePage {
	
	public ContactUsSteps() throws IOException {
		super();
		
	}
	
	BasePage basePage = new BasePage();

	@Given("^user navigates to webdriveruniversity contact us form \"([^\"]*)\"$")
	public void user_navigates_to_webdriveruniversity_contact_us_form(String Url) throws Throwable {
	    
		basePage.waitForSpecificPage(Url);
		contactUsPage.getContactUsPage(Url);
	}

	@When("^user enters a valid firstname as \"([^\"]*)\"$")
	public void user_enters_a_valid_firstname_as(String FirstName) throws Throwable {
		contactUsPage.enterFirstName(FirstName);		
	}

	@When("^user enters a valid lastname as \"([^\"]*)\"$")
	public void user_enters_a_valid_lastname_as(String LastName) throws Throwable {
		contactUsPage.enterLastName(LastName);
	}

	@When("^user enters a valid email address as \"([^\"]*)\"$")
	public void user_enters_a_valid_email_address_as(String EmailAddress) throws Throwable {
		contactUsPage.enterEmailAddress(EmailAddress);
	}

	@When("^user enters comment as \"([^\"]*)\"$")
	public void user_enters_comment_as(String Comment) throws Throwable {
		contactUsPage.enterComments(Comment);
	}

	@When("^user clicks on the submit button\"$")
	public void user_clicks_on_the_submit_button() throws Throwable {
		contactUsPage.clickOnSubmiButton();
	}

	@Then("^user gets successful welcome message as \"([^\"]*)\"$")
	public void user_gets_successful_welcome_message_as(String Status) throws Throwable {
	    String ExpStatusMassage = driver.findElement(By.xpath("//h1[contains(text(),'Thank You for your Message!')]")).getText();
		Assert.assertEquals(ExpStatusMassage, Status);		
	}
	
	@When("^user enters an invalid email address as \"([^\"]*)\"$")
	public void user_enters_an_invalid_email_address_as(String EmailAddress) throws Throwable {
		contactUsPage.enterEmailAddress(EmailAddress);
	}

	@Then("^user gets an error welcome message as \"([^\"]*)\"$")
	public void user_gets_an_error_welcome_message_as(String Status) throws Throwable {
		String ErrorStatusMessage = driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(ErrorStatusMessage, Status);
	}

}
