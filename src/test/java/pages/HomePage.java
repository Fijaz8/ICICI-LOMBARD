package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
		}
	@FindBy(xpath ="//span[@class='scope-popup' and text()=' '")
	     private WebElement Schengenclick;
	
	@FindBy(xpath ="//span[text()='Travel']")
	WebElement travelbutton;
	
    @FindBy(xpath = "//button[@class='health-insure-member btn-traveldetails scope-popup']")
     WebElement travellingToInput;
    
    @FindBy(xpath = "//app-single-trip//ul/li/span")
    private WebElement selectedCountryChip;

	
	@FindBy(xpath ="//label[text()= 'Your travel scope']")
	WebElement travelScope;
	 @FindBy(id = "ilcountry")
	    private WebElement countryButton;

    @FindBy(xpath = "//p[normalize-space(text())='Travel Insurance']")
    private WebElement travelInsuranceOption;
    
//    
    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element);
    }
//    
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    WebDriverWait wait ;
    
    public void clickTravelInsurance() {
    	
        wait.until(ExpectedConditions.elementToBeClickable(travelInsuranceOption)).click();

    }
    public void clickTravelTab() {
        wait.until(ExpectedConditions.elementToBeClickable(travelbutton)).click();
    }
    
    public void enterDestinationCountry() {
      
        travellingToInput.click();
       
     
        
    }
    public void clickCountryButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='health-insure-member btn-traveldetails scope-popup']"))).click();
  
    }
    public String getSelectedCountry() {
        wait.until(ExpectedConditions.visibilityOf(selectedCountryChip));
        return selectedCountryChip.getText().trim();
    }
	public void clickTravelButton() {
		travelbutton.click();
	}
	public String verifytravelPage() {
		return travelScope.getText();
	}
    public void selectCountryFromDropdown(String country) {
        String xpath = "//span[@class='scope-popup' and text()='" + country + "']";
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath(xpath))
        )).click();
        
    }
     public boolean isCountryAvailableInDropdown(String country) {
            String xpath = "//app-single-trip//*[contains(@class,'dropdown-item') and normalize-space(text())='" + country + "']";
            try {
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
     
     
     
     //------------------------------ DATE -------------------------------------------

     public void switchToMainPage() {
         driver.switchTo().defaultContent();
     }


     @FindBy(xpath = "//input[@id='txtTripStartDate']")
     private WebElement travelStartDateField;

     @FindBy(xpath = "//input[@id='txtTripEndtDate']")
     private WebElement travelEndDateField;

     
     
     public void enterTravelDates(String startDate, String endDate) {
       
         scrollIntoView(travelStartDateField);
         safeClick(travelStartDateField);
         selectDateFromCalendar(startDate);
         selectDateFromCalendar(endDate);
     }
     public void openStartDatePicker() {
    		 driver.findElement(By.xpath("//input[@id='txtTripStartDate']")).click();
     }

     private void selectDateFromCalendar(String dateStr) {
    	    String day = dateStr.split("-")[0];
    	    if (day.startsWith("0")) day = day.substring(1);
    	    
    	    // STEP 1: Open the calendar
    	    WebElement dateInput = wait.until(
    	        ExpectedConditions.elementToBeClickable(By.id("txtTripStartDate"))
    	    );
    	    dateInput.click();
    	    
    	    // STEP 2: Wait for calendar to be visible
    	    wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[contains(@class,'travel-calender-left')]//table")
    	    ));
    	    
    	    // STEP 3: Locate ONLY selectable dates (exclude selectedDate1/selectedDate2)
    	    By dateLocator = By.xpath(
    	        "//div[contains(@class,'travel-calender-left')]" +
    	        "//td/div[normalize-space(text())='" + day + "'" +
    	        " and not(contains(@class,'selectedDate1'))" +
    	        " and not(contains(@class,'selectedDate2'))]"
    	    );
    	    
    	    WebElement dateCell1 = wait.until(ExpectedConditions.elementToBeClickable(dateLocator));
    	    dateCell1.click();
    	    
    	    By dateLocator2 = By.xpath(
        	        "//div[contains(@class,'travel-calender-right')]" +
        	        "//td/div[normalize-space(text())='" + day + "'" +
        	        " and not(contains(@class,'selectedDate1'))" +
        	        " and not(contains(@class,'selectedDate2'))]"
        	    );
    	    WebElement dateCell2 = wait.until(ExpectedConditions.elementToBeClickable(dateLocator2));
    	    dateCell2.click();
    	}
     public String getTravelStartDate() {

         return travelStartDateField.getAttribute("value").trim().replace("/", "-");
     }

     public String getTravelEndDate() {

         return travelEndDateField.getAttribute("value").trim().replace("/", "-");
     }

     public boolean isPastDate(String dateStr) {
         LocalDate inputDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         return inputDate.isBefore(LocalDate.now());
     }

     public boolean tryPastDateAndCheckRejection(String dateStr) {
         wait.until(ExpectedConditions.elementToBeClickable(travelStartDateField));
         scrollIntoView(travelStartDateField);
         safeClick(travelStartDateField);
         String day = dateStr.split("-")[0];
         if (day.startsWith("0")) day = day.substring(1);
         String dayXpath = "//app-calender//div[normalize-space(text())='" + day + "']";
         try {
             WebElement dayCell = driver.findElement(By.xpath(dayXpath));
             String classAttr = dayCell.getAttribute("class");
             if (classAttr != null && (classAttr.contains("disabled") || classAttr.contains("inactive"))) {
                 return true;
             }
             try {
                 dayCell.click();
             } catch (Exception ignored) {
                 return true;
             }
             String value = travelStartDateField.getAttribute("value");
             return value == null || value.isEmpty();
         } catch (Exception e) {
             return true;
         }
     }

     
     
    }
	
	 

