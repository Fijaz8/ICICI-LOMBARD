package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;

public class TC13 extends BaseTest{
	
	CarInsurancePage Cp;
	@When("User selects Got a New Vehicle")
	public void user_selects_got_a_new_vehicle() {
	    // Write code here that turns the phrase above into concrete actions
		Cp = new CarInsurancePage(getDriver());
		Cp.clickGotNewVehicle();
	}

	@When("User enters an invalid mobile number {string}")
	public void user_enters_an_invalid_mobile_number(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Cp.enterMobile(string);
	}

	@When("User clicks on Get Quote")
	public void user_clicks_on_get_quote() {
	    // Write code here that turns the phrase above into concrete actions
		Cp.clickGetQuoteButton();
	}

	@Then("User should see a {string} validation message")
	public void user_should_see_a_validation_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Cp.verifyMessage(string), "Validation message not displayed");
	}


}
