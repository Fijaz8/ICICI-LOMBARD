package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	@FindBy(xpath="//a[text()='Proceed']")
	WebElement ProceedButton;
	
	@FindBy(xpath="//span[text()='Please select valid city of registration']")
	WebElement CityError;
	
	@FindBy(xpath="//input[@id='makeModelName']")
	WebElement CarModelInputBox;
	
	@FindBy(xpath="//div[contains(text(),'MARUTI')]")
	WebElement CarMake;
	
	@FindBy(xpath="//div[contains(text(),'ALTO')]")
	WebElement CarModel;
	
	@FindBy(xpath="//div[contains(text(),' ALTO 800 LXI ')]")
	WebElement CarVariants;
	
	@FindBy(xpath="//li[@id='planpage-active' and text()='1. Choose plan']")
	WebElement PlanPageElement;
	
	@FindBy(xpath="(//a[text()='Edit details'])[2] ")
	WebElement EditOption;
	
	@FindBy(xpath="//input[@id='makeModelName']")
	WebElement MakeModelName;
	
	@FindBy(xpath="//a[text()='Update']")
	WebElement UpdateButton;
	
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
	
	public boolean isCarModelAndCityBlank() {
		return CarRegistration.getAttribute("value").isEmpty();
	}
	
	public void clickProceedButton() {
		ProceedButton.click();
	}
	
	public boolean validateCityError(String errorMsg) {
		return CityError.getText().trim().equals(errorMsg);
	}
	
	public void enterCityName(String city) {

		CarRegistration.sendKeys(city);


		WebElement cityOption = wait.until(
		    ExpectedConditions.elementToBeClickable(
		        By.xpath("//*[contains(text(),'MAHARASHTRA-PUNE')]")));

		cityOption.click();

	}
	
	public void clickValidCarModel() {
		CarModelInputBox.click();
		CarMake.click();
		CarModel.click();
		CarVariants.click();
	}
	
	public boolean checkPlanElementVisible() {
		return PlanPageElement.isDisplayed();
	}
	
	public void clickEditDetailsOption() {

		wait.until(ExpectedConditions.elementToBeClickable(EditOption));

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", EditOption);


	}
	
	public boolean isModelEditable() {
		return MakeModelName.isEnabled();
	}
	
	public void clickUpdateButton() {
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", UpdateButton);
	}
}