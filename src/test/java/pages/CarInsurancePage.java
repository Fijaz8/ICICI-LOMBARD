package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarInsurancePage{
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	
	public CarInsurancePage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@id='car-registration']")
	WebElement RegNo;
	
	@FindBy(xpath="//input[@id='car-registration-mob']")
	WebElement MobNo;
	
	@FindBy(xpath="//button[text()='Get quote']")
	WebElement GetQuoteButton;
	
	@FindBy(xpath="//span[text()=' Please enter a valid vehicle registration no ']")
	WebElement RegistrationNoError;
	
	@FindBy(xpath="//span[text()=' Please enter a valid mobile number ']")
	WebElement MobileNoError;
	
	@FindBy(xpath="//a[contains(text(), 'Got a new vehicle')]")
	WebElement GotNewVehicle;
	
	@FindBy(xpath="//input[@id='carRg']")
	WebElement CarRegistration;
	
	
	public boolean areFieldsEmpty() {
		String regNoValue = RegNo.getAttribute("value");
		String mobNoValue = MobNo.getAttribute("value");
		
		return regNoValue.trim().isEmpty() && mobNoValue.trim().isEmpty();
	}
	
	public void clickGetQuoteButton() {
		GetQuoteButton.click();
	}
	
//	public boolean validateMobileError(String message) {
//		return MobileNoError.getText().trim().equals(message);
//	}
//	
//	public boolean validateRegNoError(String message) {
//		return RegistrationNoError.getText().trim().equals(message);
//	}
	
	public boolean verifyMessage(String message) {
		return MobileNoError.getText().trim().equals(message) || RegistrationNoError.getText().trim().equals(message);
	}
	
	public void clickGotNewVehicle() {
		GotNewVehicle.click();
	}
	
	public void enterMobile(String mobile) {
		MobNo.sendKeys(mobile);
	}
	
	public boolean isCarModelPageDisplayed() {
		return CarRegistration.isDisplayed();
	}

}
