package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.travelInsurancePage;
import policy.utils.ConfigReader;

public class TC4 extends BaseTest{
	  String startDate;
	    String endDate;
	    HomePage hp ;
	    travelInsurancePage TIP;

@Given("user is on the Travel Insurance page with valid inputs filled {string}")
public void user_is_on_the_travel_insurance_page_with_valid_inputs_filled(String Country) {
    hp = new HomePage(getDriver());
    hp.clickTravelTab();
    startDate = ConfigReader.getProperty("travel.start.date");
    endDate   = ConfigReader.getProperty("travel.end.date");

    hp.enterDestinationCountry();
    hp.selectCountryFromDropdown(Country);
    hp.openStartDatePicker();
    hp.enterTravelDates(startDate, endDate);

}

@When("user clicks on the Get Quote button")
public void user_clicks_on_the_get_quote_button() {
    // Write code here that turns the phrase above into concrete actions
	hp.clickButton();
}

@Then("user should be redirected to the quote details page")
public void user_should_be_redirected_to_the_quote_details_page() {
    // Write code here that turns the phrase above into concrete actions
	TIP= new travelInsurancePage(getDriver());
}

@Then("the quote details page should display the traveler information form")
public void the_quote_details_page_should_display_the_traveler_information_form() {
    // Write code here that turns the phrase above into concrete actions
    Assert.assertEquals(TIP.getTitleOFPage(), "What is TripSecure+?");
}

}
