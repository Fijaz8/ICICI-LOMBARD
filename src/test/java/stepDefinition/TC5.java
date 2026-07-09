package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.travelInsurancePage;

public class TC5 extends BaseTest{
	travelInsurancePage TIP;
@Given("user is on the quote details page")
public void user_is_on_the_quote_details_page() {
	  TIP=new travelInsurancePage(getDriver());
}

@When("user enters a valid {string} and {string}")
public void user_enters_a_valid_mobile_number_and_email_address(String mobileNo,String emailId) {
    // Write code here that turns the phrase above into concrete actions
	TIP.enterMobileNo(mobileNo);
	TIP.enterEmailId(emailId);
}

@When("select the number of persons")
public void select_the_number_of_persons() {
    // Write code here that turns the phrase above into concrete actions
	TIP.addElement();
}

@And("user clicks on the Continue button")
public void the_form_should_be_submitted_successfully() {
    // Write code here that turns the phrase above into concrete actions
	TIP.clickContinueButton();
}

@Then("user should be redirected to the curated plans page")
public void user_should_be_redirected_to_the_curated_plans_page() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println(TIP.getCurtedpageText());
	String actual = TIP.getCurtedpageText().replaceAll("\\s+", " ").trim();
	String expected = "Benefits curated for you Share quote";

	Assert.assertEquals(actual, expected);
}
}
