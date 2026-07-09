package stepDefinition;

import java.util.List;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CuratedPageForPolicy;

public class TC7 extends BaseTest{
	List<String> medicalCovers;
	CuratedPageForPolicy cpfp;

	@Given("user is on the curated plans page for medical cover")
	public void user_is_on_the_curated_plans_page() {
		cpfp = new CuratedPageForPolicy(getDriver());
		}
	@When("user reads the medical cover of each plan")
	public void user_reads_the_medical_cover_of_each_plan() {
		medicalCovers = cpfp.getAllMedicalCovers();
		
			}
	@Then("each plan should display a medical cover amount starting with {string}")
	public void each_plan_should_display_medical_cover_starting_with(String prefix) {
		Assert.assertFalse(medicalCovers.isEmpty()
				);
		for (int i = 0; i < medicalCovers.size(); i++) {
			String cover = medicalCovers.get(i);
			Assert.assertTrue(cover.startsWith(prefix));
			} 		}
	}

