package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
		}
	
	 
	
	String parentWindow;
	
	@FindBy(xpath ="//span[text()='Travel']")
	WebElement travelbutton;
	
	@FindBy(xpath ="//label[text()= 'Your travel scope']")
	WebElement travelScope;
	
	@FindBy(xpath = "(//a[contains(text(), 'Motor')])[1]")
	WebElement NavElement;
	
	@FindBy(xpath="//a[contains(text(),'Car Insurance')]")
	WebElement CarInsurance;
	
	public void clickTravelButton() {
		travelbutton.click();
	}
	public String verifytravelPage() {
		return travelScope.getText();
	}
	
	
	public void  clickCarInsurance() {
		parentWindow = driver.getWindowHandle();
		actions.moveToElement(NavElement).perform();
		wait.until(ExpectedConditions.visibilityOf(CarInsurance));
		CarInsurance.click();
		
	}
	
	public String verifyCarInsurancePage() {

		wait.until(driver -> driver.getWindowHandles().size() > 1);

		for(String window : driver.getWindowHandles()) {

		if(!window.equals(parentWindow)) {
			driver.switchTo().window(window);
			break;
		}
		}
		return driver.getTitle();

	}
	
	
	

}
