package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;

public class TC16 extends BaseTest{
	CarInsurancePage Cp;

@When("User gives value for city {string}")
public void user_gives_value_for_city(String string) {
    // Write code here that turns the phrase above into concrete actions
	Cp = new CarInsurancePage(getDriver());
	Cp.enterCityName(string);
}

@When("User selects valid Car Model")
public void user_selects_valid_car_model() {
    // Write code here that turns the phrase above into concrete actions
	Cp.clickValidCarModel();
}

@When("User clicks the Proceed button")
public void user_clicks_proceed_button() {
    // Write code here that turns the phrase above into concrete actions
	Cp.clickProceedButton();
}

@Then("User should be redirected to the plan selection page")
public void user_should_be_redirected_to_the_plan_selection_page() {
    // Write code here that turns the phrase above into concrete actions
	Assert.assertTrue(Cp.checkPlanElementVisible(), "User not directed to Plan Selection Page");
}


}
