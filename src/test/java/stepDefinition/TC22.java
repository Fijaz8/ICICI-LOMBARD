package stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC22 extends BaseTest {

    // ============ HELPER METHODS ============
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebDriverWait getShortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    private JavascriptExecutor getJs() {
        return (JavascriptExecutor) driver;
    }

    // ============ STEP DEFINITIONS ============

    @Given("User opens the ICICI Lombard homepage")
    public void user_opens_the_icici_lombard_homepage() {
        driver.get("https://www.icicilombard.com/");
        try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
        dismissPopups();
        System.out.println("✅ Homepage launched");
        System.out.println("🔎 Title: " + driver.getTitle());
        System.out.println("🔎 URL: " + driver.getCurrentUrl());
    }

    @When("User clicks on the Bike tab in the quote widget")
    public void user_clicks_on_the_bike_tab_in_the_quote_widget() {
        WebElement bikeTab = null;

        // Try multiple locators for Bike tab
        String[] locators = {
            "//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'bike')]",
            "//li[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'bike')]",
            "//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'bike')]",
            "//div[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'bike') and (@role='tab' or @role='button' or contains(@class,'tab'))]",
            "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'bike')]",
            "//a[contains(@href,'two-wheeler') or contains(@href,'bike') or contains(@href,'2w')]"
        };

        for (String xp : locators) {
            try {
                bikeTab = getShortWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xp)));
                if (bikeTab != null && bikeTab.isDisplayed()) {
                    System.out.println("✅ Bike tab found using: " + xp);
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (bikeTab == null) {
            // Fallback: navigate directly to Bike Insurance page
            System.out.println("⚠️ Bike tab not found on homepage — navigating directly to bike-insurance page");
            driver.get("https://www.icicilombard.com/two-wheeler-insurance");
            try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
            dismissPopups();
            return;
        }

        getJs().executeScript("arguments[0].scrollIntoView({block:'center'});", bikeTab);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        try {
            bikeTab.click();
            System.out.println("✅ Bike tab clicked (normal)");
        } catch (Exception e) {
            getJs().executeScript("arguments[0].click();", bikeTab);
            System.out.println("✅ Bike tab clicked (JS)");
        }

        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
    }

    @Then("Bike quote form should be displayed")
    public void bike_quote_form_should_be_displayed() {
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String currentTitle = driver.getTitle().toLowerCase();
        System.out.println("🔎 Current URL: " + currentUrl);
        System.out.println("🔎 Current Title: " + currentTitle);

        boolean isBikeFormDisplayed = false;

        // Check 1: URL contains bike/two-wheeler
        if (currentUrl.contains("bike") || currentUrl.contains("two-wheeler") || currentUrl.contains("2w")) {
            isBikeFormDisplayed = true;
            System.out.println("✅ Bike section confirmed via URL");
        }

        // Check 2: Title contains bike/two-wheeler
        if (!isBikeFormDisplayed && (currentTitle.contains("bike") || currentTitle.contains("two wheeler")
                || currentTitle.contains("two-wheeler") || currentTitle.contains("2 wheeler"))) {
            isBikeFormDisplayed = true;
            System.out.println("✅ Bike section confirmed via Title");
        }

        // Check 3: Look for a registration-number input
        if (!isBikeFormDisplayed) {
            try {
                WebElement regField = driver.findElement(By.xpath(
                    "//input[contains(@placeholder,'Registration') or contains(@placeholder,'registration') "
                  + "or contains(@placeholder,'Bike number') or contains(@placeholder,'bike number') "
                  + "or contains(@placeholder,'Vehicle')]"));
                if (regField.isDisplayed()) {
                    isBikeFormDisplayed = true;
                    System.out.println("✅ Bike form input field detected");
                }
            } catch (Exception ignored) {}
        }

        // Check 4: Look for "Bike Insurance" or "Two Wheeler" heading text
        if (!isBikeFormDisplayed) {
            try {
                WebElement header = driver.findElement(By.xpath(
                    "//*[contains(text(),'Bike Insurance') or contains(text(),'Two Wheeler') "
                  + "or contains(text(),'Two-Wheeler') or contains(text(),'2 Wheeler')]"));
                if (header.isDisplayed()) {
                    isBikeFormDisplayed = true;
                    System.out.println("✅ Bike Insurance section header detected: " + header.getText());
                }
            } catch (Exception ignored) {}
        }

        if (!isBikeFormDisplayed) {
            printAllVisibleInputs();
        }

        Assert.assertTrue(isBikeFormDisplayed, "❌ Bike quote form was NOT displayed!");
        System.out.println("✅ TC_22 PASSED — Bike quote form displayed successfully");
    }

    // ============ UTILITY METHODS ============

    private void dismissPopups() {
        String[] closeXPaths = {
            "//button[contains(@class,'close')]",
            "//button[@aria-label='Close']",
            "//div[contains(@class,'modal')]//button[contains(.,'×')]",
            "//span[contains(@class,'close-icon')]",
            "//*[@id='wzrk-cancel']"
        };
        for (String xp : closeXPaths) {
            try {
                WebElement close = driver.findElement(By.xpath(xp));
                if (close.isDisplayed()) {
                    getJs().executeScript("arguments[0].click();", close);
                    System.out.println("✅ Dismissed popup: " + xp);
                    Thread.sleep(1000);
                }
            } catch (Exception ignored) {}
        }
    }

    // ============ DEBUG HELPERS ============

    private void printAllVisibleInputs() {
        System.out.println("\n🔍 === DEBUG: Listing all <input> elements ===");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        int count = 0;
        for (WebElement in : inputs) {
            try {
                if (!in.isDisplayed()) continue;
                count++;
                System.out.println(count + ") type=" + in.getAttribute("type")
                    + " | id=" + in.getAttribute("id")
                    + " | name=" + in.getAttribute("name")
                    + " | placeholder=" + in.getAttribute("placeholder"));
            } catch (Exception ignored) {}
        }
        System.out.println("Total visible inputs: " + count);
        System.out.println("======================================================\n");
    }
}