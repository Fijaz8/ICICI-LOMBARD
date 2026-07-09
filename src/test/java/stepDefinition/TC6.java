package stepDefinition;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CuratedPageForPolicy;

public class TC6 extends BaseTest{
	CuratedPageForPolicy cpfp;
	@Given("user is on the curated plans page")
	public void user_is_on_the_curated_plans_page() {
	    // Write code here that turns the phrase above into concrete actions
		 cpfp= new CuratedPageForPolicy(getDriver());
	}

	@When("user scrolls through all plan tiles in the carousel")
	public void user_scrolls_through_all_plan_tiles_in_the_carousel() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.print(cpfp.getPlanCount());
	}

	@Then("all plan details should be captured and printed with total count")
	public void all_plan_details_should_be_captured_and_printed_with_total_count() {
	    int planCount = cpfp.getPlanCount();
	    System.out.println("Total curated plans found: " + planCount);

	    Assert.assertTrue(planCount > 0,
	        "❌ No curated plans were found on the page!");
	}
	

}
