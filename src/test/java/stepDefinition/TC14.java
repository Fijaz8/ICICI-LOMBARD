package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;

public class TC14 extends BaseTest{
	CarInsurancePage Cp;
	@When("User selects Got a New Vehicle option")
	public void user_selects_got_a_new_vehicle_option() {
	    // Write code here that turns the phrase above into concrete actions
		Cp = new CarInsurancePage(getDriver());
		Cp.clickGotNewVehicle();
	}

	@When("User enters a valid mobile number {string}")
	public void user_enters_a_valid_mobile_number(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Cp.enterMobile(string);
	}

	@When("User clicks on Get Quote button")
	public void user_clicks_on_get_quote_button() {
	    // Write code here that turns the phrase above into concrete actions
	    Cp.clickGetQuoteButton();
	}

	@Then("User should be redirected to the Car Model Selection page")
	public void user_should_be_redirected_to_the_car_model_selection_page() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Cp.isCarModelPageDisplayed(), "Model Page Not Displayed");
	}

}
