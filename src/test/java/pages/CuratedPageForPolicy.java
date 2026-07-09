package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CuratedPageForPolicy {
	WebDriver driver;
	WebDriverWait wait;
    private static final By PLAN_TILE =      By.xpath("//div[contains(@class,'plan-curated-block')]");
	public CuratedPageForPolicy(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 PageFactory.initElements(driver, this);
	}
	
	public List<String> getAllMedicalCovers() {
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PLAN_TILE));
	    List<WebElement> tiles = driver.findElements(PLAN_TILE);
	    List<String> covers = new ArrayList<>();

	    for (WebElement tile : tiles) {
	        try {
	            String cover = tile.findElement(
	                By.xpath(".//p[@class='medcover-txt']/span")
	            ).getAttribute("textContent").trim();       // ← KEY CHANGE
	            covers.add(cover);
	        } catch (Exception e) {
	            covers.add("N/A");
	        }
	    }
	    return covers;
	}

	
	

	public int getPlanCount() {
	    List<WebElement> planTiles = driver.findElements(
	        By.xpath("//div[contains(@class,'plan-curated-block')]")
	    );
	    return planTiles.size();
	}
	
	
}


	
	

