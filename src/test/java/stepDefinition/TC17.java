package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;

public class TC17 extends BaseTest{
	CarInsurancePage Cp;
	@When("User clicks Edit details option")
	public void user_clicks_edit_details_option() {
		Cp = new CarInsurancePage(getDriver());
	    // Write code here that turns the phrase above into concrete actions
		Cp.clickEditDetailsOption();
	}

	@When("User checks Car details are editable")
	public void user_checks_car_details_are_editable() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Cp.isModelEditable());
	}

	@Then("User clicks Update button")
	public void user_clicks_update_button() {
	    // Write code here that turns the phrase above into concrete actions
		Cp.clickUpdateButton();
	}

}
