package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class TC2 extends BaseTest{

	
	HomePage hp ; 
	
	@Given("user is on the Travel Insurance page")
	public void user_is_on_the_travel_insurance_page() {
	    // Write code here that turns the phrase above into concrete actions
		hp= new HomePage(getDriver());
		hp.clickTravelTab();
	}

	@When("user clicks on the Region of Travel dropdown")
	public void user_clicks_on_the_region_of_travel_dropdown() {
	    // Write code here that turns the phrase above into concrete actions
		
		hp.clickCountryButton();
	
	}

	@When("user selects {string} as the destination country")
	public void user_selects_as_the_destination_country(String string) {
	    // Write code here that turns the phrase above into concrete actions
		hp.selectCountryFromDropdown(string);
	}

	@Then("{string} should be displayed as the selected country")
	public void should_be_displayed_as_the_selected_country(String country) {
		if (hp.isCountryAvailableInDropdown(country)) {
			hp.selectCountryFromDropdown(country);
            String actualCountry = hp.getSelectedCountry();
            Assert.assertEquals(actualCountry, country, "Country is not selected correctly.");
		}
	}


}
