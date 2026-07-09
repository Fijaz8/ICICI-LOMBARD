package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class travelInsurancePage {
	WebDriver driver;
	WebDriverWait wait;
	public travelInsurancePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="//h1[text()='What is TripSecure+?']")
    private WebElement Schengenclick;
	
	@FindBy(xpath ="//input[@id='mul-no']")
    private WebElement mobileId;
	
	@FindBy(xpath ="//input[@id='mul-em']")
    private WebElement emailId;
	
	@FindBy(xpath ="//div[@class='il-done-btn'] //a[text()='Continue']")
    private WebElement continueButton;
	
	@FindBy(xpath ="(//a[@class='btn-plus'])[1]")
    private WebElement plusForZeroToFifty;
	
	@FindBy(xpath ="//h3[@class='travel-plan-title']")
    private WebElement titleofpage;
	
	public void enterMobileNo(String val) {
		mobileId.sendKeys(val);
	}
	public void enterEmailId(String email) {
		emailId.sendKeys(email);
	}
	
	public String getTitleOFPage() {
		return Schengenclick.getText();
	}
	public void addElement() {
		plusForZeroToFifty.click();
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	public String getCurtedpageText() {
		
		WebElement planTitle = wait.until(
			    ExpectedConditions.presenceOfElementLocated(
			        By.xpath("//h3[@class='travel-plan-title']")
			    )
			);
	    
		return planTitle.getText();
	}
	
	
}
