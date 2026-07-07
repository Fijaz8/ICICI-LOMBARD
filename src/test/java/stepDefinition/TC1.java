package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
public class TC1 extends BaseTest{
	HomePage hp;
	
	
@Given("user launches the ICICI Lombard website")
public void user_launches_the_icici_lombard_website() {
    // Write code here that turns the phrase above into concrete actions
	hp= new HomePage(getDriver());
}

@When("user clicks on Travel Insurance from the top navigation menu")
public void user_clicks_on_from_the_top_navigation_menu() {
    // Write code here that turns the phrase above into concrete actions
    hp.clickTravelButton();
}



@Then("the Travel Insurance quote form should be visible")
public void the_travel_insurance_quote_form_should_be_visible() {
    // Write code here that turns the phrase above into concrete actions
    Assert.assertEquals(
    		hp.verifytravelPage(),
    		"Your travel scope"        
    		);
}


}
