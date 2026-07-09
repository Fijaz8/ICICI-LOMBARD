package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class TC11 extends BaseTest{
	static HomePage Hp;

	@When("User CLicks Car Insurance Nav in Home Page")
	public void user_c_licks_car_insurance_nav_in_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		Hp = new HomePage(getDriver());
		Hp.clickCarInsurance();
		
	}

	@Then("User Redirects to Car Insurance Page")
	public void user_redirects_to_car_insurance_page() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(Hp.verifyCarInsurancePage().contains("Car Insurance"));
	}
	
	

}