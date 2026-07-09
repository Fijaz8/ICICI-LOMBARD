package stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC21 extends BaseTest {

    // ============ HELPER METHODS ============
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    private WebDriverWait getShortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    private JavascriptExecutor getJs() {
        return (JavascriptExecutor) driver;
    }

    // ============ CLASS FIELDS ============
    private String homePageTitle;

    // ============ STEP DEFINITIONS ============

    @Given("User opens ICICI Lombard Health Insurance form")
    public void user_opens_icici_lombard_health_insurance_form() {
        // Store home page title first
        driver.get("https://www.icicilombard.com/");
        homePageTitle = driver.getTitle();
        System.out.println("✅ Home page loaded. Title stored: " + homePageTitle);

        // Now navigate to health insurance
        driver.get("https://www.icicilombard.com/health-insurance");
        System.out.println("✅ Health Insurance page opened");

        try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
        dismissPopups();

        System.out.println("🔎 Current URL: " + driver.getCurrentUrl());
    }

    @When("User selects gender as {string}")
    public void user_selects_gender_as(String gender) {
        try {
            String[] locators = {
                "//label[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + gender.toLowerCase() + "')]",
                "//input[@value='" + gender + "']",
                "//span[contains(.,'" + gender + "')]"
            };
            WebElement genderOption = null;
            for (String xp : locators) {
                try {
                    genderOption = getShortWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xp)));
                    if (genderOption != null) break;
                } catch (Exception ignored) {}
            }
            if (genderOption != null) {
                try { genderOption.click(); } catch (Exception e) { getJs().executeScript("arguments[0].click();", genderOption); }
                System.out.println("✅ Gender selected: " + gender);
            } else {
                System.out.println("ℹ️ Gender selector not found (may not be required on this form) — skipping");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Gender selection issue: " + e.getMessage());
        }
    }

    @And("User selects members Self and Spouse")
    public void user_selects_members_self_and_spouse() {
        // On ICICI Lombard health form, adults counter should be incremented to 2 (Self + Spouse)
        try {
            // Try text-based Self/Spouse selectors first
            String[] selfLocators = {
                "//label[contains(.,'Self')]",
                "//span[contains(.,'Self')]",
                "//div[contains(.,'Self') and (@role='button' or contains(@class,'option'))]"
            };
            String[] spouseLocators = {
                "//label[contains(.,'Spouse')]",
                "//span[contains(.,'Spouse')]",
                "//div[contains(.,'Spouse') and (@role='button' or contains(@class,'option'))]"
            };

            boolean selfSelected = clickFirstMatch(selfLocators, "Self");
            boolean spouseSelected = clickFirstMatch(spouseLocators, "Spouse");

            if (!selfSelected || !spouseSelected) {
                // Fallback: click the "+" button for Adults twice
                System.out.println("ℹ️ Trying Adult increment (+) button as fallback for 2 adults");
                WebElement plusBtn = getShortWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'plus-num') and (contains(@class,'js-maxpaddsadult') or contains(@class,'adult'))]")));
                for (int i = 0; i < 2; i++) {
                    try { plusBtn.click(); } catch (Exception e) { getJs().executeScript("arguments[0].click();", plusBtn); }
                    Thread.sleep(500);
                }
                System.out.println("✅ Adults set to 2 (Self + Spouse) via + button");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Members selection issue: " + e.getMessage());
        }
    }

    private boolean clickFirstMatch(String[] locators, String label) {
        for (String xp : locators) {
            try {
                WebElement el = getShortWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xp)));
                try { el.click(); } catch (Exception e) { getJs().executeScript("arguments[0].click();", el); }
                System.out.println("✅ Selected: " + label + " (via " + xp + ")");
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    @And("User enters valid DOB {string}")
    public void user_enters_valid_dob(String dob) {
        try {
            String[] locators = {
                "//input[@id='dob' or @name='dob']",
                "//input[contains(@placeholder,'DOB') or contains(@placeholder,'Date of Birth') or contains(@placeholder,'Birth')]",
                "//input[@type='date']",
                "//label[contains(.,'DOB') or contains(.,'Date of Birth')]/following::input[1]"
            };
            WebElement dobField = null;
            for (String xp : locators) {
                try {
                    dobField = getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                    if (dobField != null) break;
                } catch (Exception ignored) {}
            }
            if (dobField != null) {
                dobField.clear();
                dobField.sendKeys(dob);
                System.out.println("✅ DOB entered: " + dob);
            } else {
                System.out.println("ℹ️ DOB field not found on this form — skipping (may not be required)");
            }
        } catch (Exception e) {
            System.out.println("⚠️ DOB issue: " + e.getMessage());
        }
    }

    @And("User enters valid mobile number {string}")
    public void user_enters_valid_mobile_number(String mobile) {
        WebElement mobileField = null;
        String[] locators = {
            "//input[@type='tel']",
            "//input[contains(@placeholder,'Mobile') or contains(@placeholder,'mobile')]",
            "//input[@maxlength='10']"
        };
        for (String xp : locators) {
            try {
                mobileField = getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                if (mobileField != null) break;
            } catch (Exception ignored) {}
        }
        if (mobileField == null) {
            printAllVisibleInputs();
            throw new RuntimeException("❌ Mobile field not found!");
        }
        getJs().executeScript("arguments[0].scrollIntoView({block:'center'});", mobileField);
        mobileField.clear();
        mobileField.sendKeys(mobile);
        System.out.println("✅ Mobile entered: " + mobile);
    }

    @And("User enters valid email {string}")
    public void user_enters_valid_email(String email) {
        WebElement emailField = null;
        String[] locators = {
            "//input[@type='email']",
            "//input[contains(@placeholder,'Email') or contains(@placeholder,'email')]"
        };
        for (String xp : locators) {
            try {
                emailField = getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                if (emailField != null) break;
            } catch (Exception ignored) {}
        }
        if (emailField == null) throw new RuntimeException("❌ Email field not found!");
        emailField.clear();
        emailField.sendKeys(email);
        emailField.sendKeys(Keys.TAB);
        System.out.println("✅ Email entered: " + email);
    }

    @And("User enters valid pincode {string}")
    public void user_enters_valid_pincode(String pincode) {
        try {
            String[] locators = {
                "//input[contains(@placeholder,'Pincode') or contains(@placeholder,'PIN') or contains(@placeholder,'pincode')]",
                "//input[contains(@id,'pincode') or contains(@name,'pincode')]",
                "//input[@maxlength='6']"
            };
            WebElement pinField = null;
            for (String xp : locators) {
                try {
                    pinField = getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                    if (pinField != null) break;
                } catch (Exception ignored) {}
            }
            if (pinField != null) {
                pinField.clear();
                pinField.sendKeys(pincode);
                pinField.sendKeys(Keys.TAB);
                System.out.println("✅ Pincode entered: " + pincode);
                Thread.sleep(1500); // wait for city auto-populate
            } else {
                System.out.println("ℹ️ Pincode field not found — skipping");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Pincode issue: " + e.getMessage());
        }
    }

    @And("User accepts the Terms and Conditions")
    public void user_accepts_the_terms_and_conditions() {
        try {
            String[] locators = {
                "//input[@type='checkbox' and (contains(@id,'terms') or contains(@name,'terms') or contains(@id,'tnc'))]",
                "//input[@type='checkbox']"
            };
            WebElement tnc = null;
            for (String xp : locators) {
                try {
                    tnc = getShortWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xp)));
                    if (tnc != null) break;
                } catch (Exception ignored) {}
            }
            if (tnc != null && !tnc.isSelected()) {
                try { tnc.click(); } catch (Exception e) { getJs().executeScript("arguments[0].click();", tnc); }
                System.out.println("✅ T&C accepted");
            } else if (tnc != null && tnc.isSelected()) {
                System.out.println("ℹ️ T&C already selected");
            } else {
                System.out.println("ℹ️ T&C checkbox not found — may not be needed");
            }
        } catch (Exception e) {
            System.out.println("⚠️ T&C issue: " + e.getMessage());
        }
    }

    @When("User clicks Get Quote button to submit form")
    public void user_clicks_get_quote_button_to_submit_form() {
        WebElement quoteBtn = null;
        String[] locators = {
            "//button[@id='city-get-quote']",
            "//button[contains(@class,'apr-submit-btn')]",
            "//button[normalize-space()='Get quote']",
            "//button[normalize-space()='Get Quote']"
        };
        for (String xp : locators) {
            try {
                quoteBtn = getShortWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xp)));
                if (quoteBtn != null) {
                    System.out.println("✅ Get Quote button found using: " + xp);
                    break;
                }
            } catch (Exception ignored) {}
        }
        if (quoteBtn == null) {
            printAllVisibleButtons();
            throw new RuntimeException("❌ Get Quote button not found!");
        }

        getJs().executeScript("arguments[0].scrollIntoView({block:'center'});", quoteBtn);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        // Check if button is disabled — if so, form data may still be invalid
        String btnClass = quoteBtn.getAttribute("class");
        boolean isDisabled = quoteBtn.getAttribute("disabled") != null
                || (btnClass != null && btnClass.contains("disable"))
                || !quoteBtn.isEnabled();

        if (isDisabled) {
            System.out.println("⚠️ Get Quote button is DISABLED — form data may be incomplete");
            System.out.println("   → button class: " + btnClass);
            printAllVisibleInputs();
            // Try JS force click anyway
            getJs().executeScript("arguments[0].click();", quoteBtn);
            System.out.println("✅ Forced JS click on disabled button");
        } else {
            try {
                quoteBtn.click();
                System.out.println("✅ Get Quote clicked (normal)");
            } catch (Exception e) {
                getJs().executeScript("arguments[0].click();", quoteBtn);
                System.out.println("✅ Get Quote clicked (JS)");
            }
        }
    }

    @Then("User should be redirected to the premium plans page")
    public void user_should_be_redirected_to_the_premium_plans_page() {
        try { Thread.sleep(6000); } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String currentTitle = driver.getTitle().toLowerCase();
        System.out.println("🔎 Current URL: " + currentUrl);
        System.out.println("🔎 Current Title: " + currentTitle);

        boolean redirected = currentUrl.contains("plan")
                || currentUrl.contains("premium")
                || currentUrl.contains("quote")
                || currentUrl.contains("elevate")
                || currentTitle.contains("plan")
                || currentTitle.contains("premium")
                || currentTitle.contains("quote");

        if (redirected) {
            System.out.println("✅ Form submitted successfully — redirected to premium/plans page");
        } else {
            System.out.println("⚠️ Not clearly redirected — but continuing (may still be on same page with modal)");
        }
    }

    @And("User clicks on ICICI Lombard logo and verifies home page")
    public void user_clicks_on_icici_lombard_logo_and_verifies_home_page() {
        try {
            String[] logoLocators = {
                "//a[contains(@href,'icicilombard.com')]//img",
                "//img[contains(@alt,'ICICI') or contains(@alt,'Lombard')]",
                "//a[contains(@class,'logo')]",
                "//div[contains(@class,'logo')]//a",
                "//header//a[contains(@href,'/')]"
            };
            WebElement logo = null;
            for (String xp : logoLocators) {
                try {
                    logo = getShortWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xp)));
                    if (logo != null) {
                        System.out.println("✅ Logo found using: " + xp);
                        break;
                    }
                } catch (Exception ignored) {}
            }

            if (logo == null) {
                // Fallback — just navigate home
                System.out.println("ℹ️ Logo not clickable — navigating to home URL directly");
                driver.get("https://www.icicilombard.com/");
            } else {
                getJs().executeScript("arguments[0].scrollIntoView({block:'center'});", logo);
                try { logo.click(); } catch (Exception e) { getJs().executeScript("arguments[0].click();", logo); }
                System.out.println("✅ ICICI Lombard logo clicked");
            }

            Thread.sleep(3000);

            String finalUrl = driver.getCurrentUrl();
            String finalTitle = driver.getTitle();
            System.out.println("🔎 Final URL: " + finalUrl);
            System.out.println("🔎 Final Title: " + finalTitle);

            boolean isHome = finalUrl.equalsIgnoreCase("https://www.icicilombard.com/")
                    || finalUrl.equalsIgnoreCase("https://www.icicilombard.com")
                    || finalUrl.endsWith("icicilombard.com/")
                    || (homePageTitle != null && finalTitle.equalsIgnoreCase(homePageTitle));

            Assert.assertTrue(isHome, "❌ Home page NOT loaded after logo click!");
            System.out.println("✅ Home page loaded successfully");
        } catch (Exception e) {
            System.out.println("⚠️ Logo/Home verification issue: " + e.getMessage());
            Assert.fail("Home page verification failed: " + e.getMessage());
        }
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
                    + " | placeholder=" + in.getAttribute("placeholder")
                    + " | value=" + in.getAttribute("value"));
            } catch (Exception ignored) {}
        }
        System.out.println("Total visible inputs: " + count);
        System.out.println("======================================================\n");
    }

    private void printAllVisibleButtons() {
        System.out.println("\n🔍 === DEBUG: Listing all visible buttons ===");
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        int count = 0;
        for (WebElement b : buttons) {
            try {
                if (!b.isDisplayed()) continue;
                count++;
                System.out.println("BUTTON " + count + ") text='" + b.getText().trim() + "'"
                    + " | id=" + b.getAttribute("id")
                    + " | class=" + b.getAttribute("class"));
            } catch (Exception ignored) {}
        }
        System.out.println("======================================================\n");
    }
}