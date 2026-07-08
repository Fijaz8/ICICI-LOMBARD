package stepDefinition;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC15 extends BaseTest{
	@When("User leaves City and Car Model fields blank")
	public void user_leaves_city_and_car_model_fields_blank() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("User clicks Proceed button")
	public void user_clicks_proceed_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should see the {string} validation message")
	public void user_should_see_the_validation_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
