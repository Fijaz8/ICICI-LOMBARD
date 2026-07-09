package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;

public class TC15 extends BaseTest{
	CarInsurancePage Cp;
	@When("User leaves City and Car Model fields blank")
	public void user_leaves_city_and_car_model_fields_blank() {
	    // Write code here that turns the phrase above into concrete actions
		Cp = new CarInsurancePage(getDriver());
	    Assert.assertTrue(Cp.isCarModelAndCityBlank(), "Car City is Not Blank");
	}

	@When("User clicks Proceed button")
	public void user_clicks_proceed_button() {
	    // Write code here that turns the phrase above into concrete actions
		Cp.clickProceedButton();
	}

	@Then("User should see the {string} validation message")
	public void user_should_see_the_validation_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Cp.validateCityError(string));
	}

}
