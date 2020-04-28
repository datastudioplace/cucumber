package pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
	public @FindBy(xpath = "//input[@placeholder='First Name']") WebElement textfield_FirstName;
	public @FindBy(xpath = "//input[@name='last_name']") WebElement textfield_LastName;
	public @FindBy(xpath = "//input[@name='email']") WebElement textfield_EmailAddress;
	public @FindBy(xpath = "//textarea[@name='message']") WebElement textfield_Message;
	public @FindBy(xpath = "//div[@class='text-center']//input[2]") WebElement button_Submit;
	
	public ContactUsPage() throws IOException {
		super();
	}
	public ContactUsPage getContactUsPage(String Url) throws IOException {
		getDriver().get(Url);
		return new ContactUsPage();
	}
	
	public ContactUsPage enterFirstName(String FirstName) throws Exception {
		sendKeysToWebElement(textfield_FirstName, FirstName);
		return new ContactUsPage();
	}
	
	public ContactUsPage enterLastName(String LastName) throws Exception {
		sendKeysToWebElement(textfield_LastName, LastName);
		return new ContactUsPage();
	}
	
	public ContactUsPage enterEmailAddress(String EmailAddress) throws Exception {
		sendKeysToWebElement(textfield_EmailAddress, EmailAddress);
		return new ContactUsPage();
	}
	
	public ContactUsPage enterComments(String Comment) throws Exception {
		sendKeysToWebElement(textfield_Message, Comment);
		return new ContactUsPage();
	}
	
	public ContactUsPage clickOnSubmiButton() throws Exception {
		waitAndClickElement(button_Submit);
		return new ContactUsPage();
	}
	
	public ContactUsPage confirmContactUsFormSubmissionWasSuccessful() throws Exception {
		WebElement thanksContactUsPage = getDriver().findElement(By.xpath(".//*[@id='contact_reply']/h1"));
		WaitUntilWebElementIsVisible(thanksContactUsPage);
		Assert.assertEquals("thankyouforyourmessage!", thanksContactUsPage.getText().toLowerCase().replaceAll("[ ()0-9]", ""));
		return new ContactUsPage();
	}

}
