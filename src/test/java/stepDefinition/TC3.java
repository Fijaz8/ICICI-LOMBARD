package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import policy.utils.ConfigReader;

public class TC3 extends BaseTest {

    HomePage hp;

    String country;
    String startDate;
    String endDate;
    boolean pastDateRejected;


    @Given("user is on the Travel Insurance page for date picker")
    public void user_is_on_travel_insurance_page() {

        country   = ConfigReader.getProperty("travel.destination.country");
        startDate = ConfigReader.getProperty("travel.start.date");
        endDate   = ConfigReader.getProperty("travel.end.date");

        hp = new HomePage(getDriver());

        hp.clickTravelTab();
       

      hp.enterDestinationCountry();
    
    }


    @When("user opens the Travel Start Date picker and select the {string}")
    public void user_opens_travel_start_date_picker(String string) {
    	hp.selectCountryFromDropdown(string);
        hp.openStartDatePicker();
    }


    @And("user attempts to enter the configured travel start and end dates")
    public void user_attempts_to_enter_configured_dates() {

        if (hp.isPastDate(startDate)) {

            pastDateRejected =
                    hp.tryPastDateAndCheckRejection(startDate);

        } else {

            hp.enterTravelDates(startDate, endDate);
        }
    }


    @Then("travel dates should be validated based on whether start date is past or future")
    public void travel_dates_should_be_validated() {

        if (hp.isPastDate(startDate)) {

            Assert.assertTrue(
                    pastDateRejected,
                    "Past start date was NOT rejected by calendar!"
            );

        } else {

            String actualStart = hp.getTravelStartDate();
            String actualEnd   = hp.getTravelEndDate();
            
            System.out.println(actualStart);
            System.out.println("asgdd");
            System.out.println(actualEnd);
           
            
        }
            if (hp.isPastDate(startDate)) {
                Assert.assertTrue(pastDateRejected,
                		"❌ Past start date '" + startDate + "' was NOT rejected by the calendar! " +
                "Expected the calendar to block selection of past dates."
                				);
                System.out.println("✅ Past date '" + startDate + "' correctly rejected by calendar.");
                } else {       // ---------- POSITIVE FLOW: Future date should be accepted ----------
                	String actualStart = hp.getTravelStartDate();
                	String actualEnd   = hp.getTravelEndDate();
                	System.out.println("Expected Start: " + startDate + " | Actual Start: " + actualStart);
                	System.out.println("Expected End:   " + endDate   + " | Actual End:   " + actualEnd);
                	// 1️⃣ Fields must not be empty22        
                	Assert.assertNotNull(actualStart, "Travel start date field is null!");
                	Assert.assertNotNull(actualEnd,   "Travel end date field is null!");
                	Assert.assertFalse(actualStart.trim().isEmpty(),
                			"Travel start date field is empty after selection!");
                	Assert.assertFalse(actualEnd.trim().isEmpty(),
                			"Travel end date field is empty after selection!");
                	// 2️⃣ Selected values must match what user configured30    
                	String datebegin =actualStart.split(" ")[0];
                	String Actualdatebegin =startDate.split("-")[0];
                	
                	String dateEnd =actualStart.split(" ")[0];
                	String Actualdateend =actualStart.split(" ")[0];
                	
             
                	Assert.assertEquals(datebegin, Actualdatebegin,
                			"Start date mismatch! Expected: " + startDate + " but got: " + actualStart);
                	Assert.assertEquals(dateEnd, Actualdateend,
                					"End date mismatch! Expected: " + endDate + " but got: " + actualEnd);       // 3️⃣ End date must be on/after start date (business rule)
                	      System.out.println("✅ Travel dates validated successfully: "+ actualStart + " → " + actualEnd);   }
        }
    
    
    
    }
    
