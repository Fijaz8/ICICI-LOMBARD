package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath ="//span[text()='Travel']")
	WebElement travelbutton;
	
	@FindBy(xpath ="//label[text()= 'Your travel scope']")
	WebElement travelScope;
	
	public void clickTravelButton() {
		travelbutton.click();
	}
	public String verifytravelPage() {
		return travelScope.getText();
	}

}
