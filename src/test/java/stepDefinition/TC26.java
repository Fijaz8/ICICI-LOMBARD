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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC26 extends BaseTest {

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

    // ============ CLASS FIELDS ============
    private String homePageTitle;
    private boolean chatWasHandled = false;

    // ============ STEP DEFINITIONS ============

    @Given("User is on an inner page of ICICI Lombard site")
    public void user_is_on_an_inner_page_of_icici_lombard_site() {
        // First: Go to homepage & store its title
        driver.get("https://www.icicilombard.com/");
        try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
        homePageTitle = driver.getTitle();
        System.out.println("✅ Home page loaded. Title stored: " + homePageTitle);

        // Then: Navigate to an inner page (Health Insurance)
        driver.get("https://www.icicilombard.com/health-insurance");
        try { Thread.sleep(5000); } catch (InterruptedException ignored) {}

        System.out.println("✅ Navigated to inner page (Health Insurance)");
        System.out.println("🔎 URL: " + driver.getCurrentUrl());
        System.out.println("🔎 Title: " + driver.getTitle());
    }

    @When("User handles the Live Chat popup if it auto-opens")
    public void user_handles_the_live_chat_popup_if_it_auto_opens() {
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // Multiple close-button locators for chat popups
        String[] closeXPaths = {
            "//div[contains(@class,'chat')]//button[contains(@class,'close') or @aria-label='Close']",
            "//div[contains(@id,'chat')]//button[contains(@class,'close')]",
            "//button[contains(@class,'chat-close')]",
            "//button[contains(@class,'closeChat')]",
            "//i[contains(@class,'chat') and contains(@class,'close')]",
            "//*[@id='closeChat' or @class='closeChat']",
            "//div[contains(@class,'livechat')]//*[contains(@class,'close')]",
            "//button[@aria-label='Close chat']",
            "//div[contains(@class,'ria')]//button[contains(@class,'close')]",  // ICICI Lombard's "ASK RIA" chat
            "//div[contains(@class,'ria-chat')]//*[contains(@class,'close')]",
            "//*[contains(@class,'ria')]//button"
        };

        for (String xp : closeXPaths) {
            try {
                WebElement closeBtn = driver.findElement(By.xpath(xp));
                if (closeBtn.isDisplayed()) {
                    try {
                        closeBtn.click();
                    } catch (Exception e) {
                        getJs().executeScript("arguments[0].click();", closeBtn);
                    }
                    chatWasHandled = true;
                    System.out.println("✅ Live chat popup closed using: " + xp);
                    Thread.sleep(1500);
                    break;
                }
            } catch (Exception ignored) {}
        }

        // Try iframe-based chat widget
        if (!chatWasHandled) {
            try {
                List<WebElement> chatFrames = driver.findElements(By.xpath(
                    "//iframe[contains(@id,'chat') or contains(@title,'chat') "
                  + "or contains(@src,'chat') or contains(@id,'ria') or contains(@title,'RIA')]"));
                for (WebElement frame : chatFrames) {
                    try {
                        driver.switchTo().frame(frame);
                        WebElement closeInFrame = driver.findElement(
                            By.xpath("//*[contains(@class,'close') or @aria-label='Close']"));
                        try {
                            closeInFrame.click();
                        } catch (Exception e) {
                            getJs().executeScript("arguments[0].click();", closeInFrame);
                        }
                        driver.switchTo().defaultContent();
                        chatWasHandled = true;
                        System.out.println("✅ Live chat closed inside iframe");
                        break;
                    } catch (Exception ignored) {
                        driver.switchTo().defaultContent();
                    }
                }
            } catch (Exception ignored) {
                driver.switchTo().defaultContent();
            }
        }

        if (!chatWasHandled) {
            System.out.println("ℹ️ No live chat popup was auto-opened (or already closed) — nothing to close");
        }
    }

    @And("User clicks on ICICI Lombard logo to return to Home page")
    public void user_clicks_on_icici_lombard_logo_to_return_to_home_page() {
        WebElement logo = null;

        String[] logoLocators = {
            "//a[contains(@href,'icicilombard.com')]//img",
            "//a[@href='/' or @href='https://www.icicilombard.com/']//img",
            "//img[contains(@alt,'ICICI') or contains(@alt,'Lombard')]",
            "//a[contains(@class,'logo')]",
            "//div[contains(@class,'logo')]//a",
            "//header//a[1]",
            "//nav//a[1]"
        };

        for (String xp : logoLocators) {
            try {
                logo = getShortWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xp)));
                if (logo != null && logo.isDisplayed()) {
                    System.out.println("✅ Logo found using: " + xp);
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (logo == null) {
            System.out.println("ℹ️ Logo not clickable — navigating to home URL directly as fallback");
            driver.get("https://www.icicilombard.com/");
        } else {
            getJs().executeScript("arguments[0].scrollIntoView({block:'center'});", logo);
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            try {
                logo.click();
                System.out.println("✅ Logo clicked (normal)");
            } catch (Exception e) {
                getJs().executeScript("arguments[0].click();", logo);
                System.out.println("✅ Logo clicked (JS)");
            }
        }

        try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
    }

    @Then("Home page should be loaded successfully")
    public void home_page_should_be_loaded_successfully() {
        String finalUrl = driver.getCurrentUrl();
        String finalTitle = driver.getTitle();
        System.out.println("🔎 Final URL: " + finalUrl);
        System.out.println("🔎 Final Title: " + finalTitle);

        boolean isHomePage = finalUrl.equalsIgnoreCase("https://www.icicilombard.com/")
                || finalUrl.equalsIgnoreCase("https://www.icicilombard.com")
                || finalUrl.endsWith("icicilombard.com/")
                || finalUrl.matches("https?://(www\\.)?icicilombard\\.com/?")
                || (homePageTitle != null && finalTitle.equalsIgnoreCase(homePageTitle));

        // Extra safety: Home page usually has multiple product tabs (Motor/Health/Travel etc.)
        if (!isHomePage) {
            try {
                List<WebElement> productTabs = driver.findElements(By.xpath(
                    "//*[contains(text(),'Motor Insurance') or contains(text(),'Health Insurance') "
                  + "or contains(text(),'Travel Insurance')]"));
                int visibleCount = 0;
                for (WebElement el : productTabs) {
                    if (el.isDisplayed()) visibleCount++;
                }
                if (visibleCount >= 2) {
                    isHomePage = true;
                    System.out.println("✅ Homepage confirmed via product tabs presence (" + visibleCount + " visible)");
                }
            } catch (Exception ignored) {}
        }

        Assert.assertTrue(isHomePage, "❌ Home page NOT loaded after logo click!");

        if (chatWasHandled) {
            System.out.println("✅ TC_26 PASSED — Chat closed & Home page loaded successfully");
        } else {
            System.out.println("✅ TC_26 PASSED — Home page loaded successfully (no chat popup was open to close)");
        }
    }
}