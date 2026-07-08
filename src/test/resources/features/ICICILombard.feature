Feature: ICICI Lombard Website Automation - Health & Bike Insurance Flows

  # ============================================================
  # TS_4 - Health Insurance Test Cases
  # ============================================================


  @TC_20 @TS_4 @HealthInsurance @NegativeTest
  Scenario: TC_20 - Validate invalid mobile and email format warnings captured into List
    Given User launches ICICI Lombard website for health insurance
    When User enters invalid mobile "99999"
    And User enters invalid email "abc@@test"
    And User clicks on Get Quote button for health
    Then Validation errors should be captured into a List and printed

 

 @TC_21 @TS_4 @HealthInsurance @PositiveTest
  Scenario: TC_21 - Validate successful form submission with valid data and navigate back to Home
    Given User opens ICICI Lombard Health Insurance form
    When User selects gender as "Male"
    And User selects members Self and Spouse
    And User enters valid DOB "01/01/1990"
    And User enters valid mobile number "9876543210"
    And User enters valid email "testuser@test.com"
    And User enters valid pincode "411001"
    And User accepts the Terms and Conditions
    When User clicks Get Quote button to submit form
    Then User should be redirected to the premium plans page
    And User clicks on ICICI Lombard logo and verifies home page


  # ============================================================
  # TS_5 - Bike Insurance & Site-wide Test Cases
  # ============================================================

 @TC_22 @TS_5 @BikeInsurance @Navigation
  Scenario: TC_22 - Navigate to Bike Insurance tab on homepage
    Given User opens the ICICI Lombard homepage
    When User clicks on the Bike tab in the quote widget
    Then Bike quote form should be displayed


 @TC_23 @TS_5 @BikeInsurance @NegativeTest
  Scenario: TC_23 - Validate invalid bike registration format warning
    Given User opens ICICI Lombard homepage and navigates to Bike tab
    When User enters invalid bike registration number "ABCD1234"
    And User clicks Get Quote on Bike form
    Then Error message should be captured for invalid registration format
 

  @TC_24 @TS_5 @BikeInsurance @PositiveTest
  Scenario: TC_24 - Verify Bike quote submission with valid dummy data
    Given User opens ICICI Lombard site and lands on Bike tab
    When User enters valid bike registration "MH12AB1234"
    And User enters valid mobile number for bike "9876543210"
    And User enters valid email for bike "biketest@test.com"
    And User accepts Terms and Conditions on Bike form
    When User clicks Get Quote on Bike form to submit
    Then User should be redirected to bike details or OTP page



   @TC_25 @TS_5 @Search
  Scenario: TC_25 - Verify site-wide Search option works
    Given User opens ICICI Lombard homepage for search
    When User clicks on the site-wide search icon
    And User types "roadside assistance" into the search box and presses Enter
    Then Search results page should display relevant links


 

  @TC_26 @TS_5 @LiveChat @Navigation
  Scenario: TC_26 - Handle Live Chat popup and navigate back to Home page
    Given User is on an inner page of ICICI Lombard site
    When User handles the Live Chat popup if it auto-opens
    And User clicks on ICICI Lombard logo to return to Home page
    Then Home page should be loaded successfully