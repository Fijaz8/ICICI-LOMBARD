package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarInsurancePage;
import pages.HomePage;

public class TC12 extends BaseTest{
	static CarInsurancePage Cp;
	static HomePage Hp;
	@When("User leaves Car Registration Number and Mobile Number fields blank")
	public void user_leaves_car_registration_number_and_mobile_number_fields_blank() {
	    // Write code here that turns the phrase above into concrete actions
		Hp = new HomePage(getDriver());
		Hp.clickCarInsurance();
		String title = Hp.verifyCarInsurancePage();
		System.out.println(title);
		Cp = new CarInsurancePage(getDriver());
		Assert.assertTrue(Cp.areFieldsEmpty(), "Registration and Mobile Number are not empty");
	}

	@When("User clicks Get Quote")
	public void user_clicks_get_quote() {
		
	    // Write code here that turns the phrase above into concrete actions
		Cp.clickGetQuoteButton();
	}

	@Then("User should see {string} validation message")
	public void user_should_see_validation_message(String string) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Cp.verifyMessage(string), "Validation message not displayed");
	}
	

}
