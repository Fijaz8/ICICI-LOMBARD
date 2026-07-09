Feature: Travel Insurance Navigation
  Scenario: Verify Travel Insurance page navigation
    Given user launches the ICICI Lombard website
    When user clicks on Travel Insurance from the top navigation menu
    Then the Travel Insurance quote form should be visible

  Scenario: Verify user can select a Schengen country as travel region
    Given user is on the Travel Insurance page
    When user clicks on the Region of Travel dropdown
    And user selects "Schengen" as the destination country
    Then "Schengen" should be displayed as the selected country

  Scenario: Verify travel date selection behavior based on configured dates
    Given user is on the Travel Insurance page for date picker
    When user opens the Travel Start Date picker and select the "Schengen"
    And user attempts to enter the configured travel start and end dates
    Then travel dates should be validated based on whether start date is past or future

  Scenario: Verify clicking Get Quote button redirects to the quote details page
    Given user is on the Travel Insurance page with valid inputs filled "Schengen"
    When user clicks on the Get Quote button
    Then user should be redirected to the quote details page
    And the quote details page should display the traveler information form

  Scenario: Verify form submission with valid mobile, email and T&C accepted
    Given user is on the Travel Insurance page with valid inputs filled "Schengen"
    When user clicks on the Get Quote button
    And user is on the quote details page
    When user enters a valid "9944500943" and "vh12327@velhightech.com"
    And select the number of persons
    And user clicks on the Continue button
    Then user should be redirected to the curated plans page



    
  Scenario: Capture all curated travel plans from the carousel
    Given user is on the Travel Insurance page with valid inputs filled "Schengen"
    When user clicks on the Get Quote button
    And user is on the quote details page
    When user enters a valid "9944500943" and "vh12327@velhightech.com"
    And select the number of persons
    And user clicks on the Continue button

   Given user is on the curated plans page    
	When user scrolls through all plan tiles in the carousel
	Then all plan details should be captured and printed with total count
 
 
 
 
 
   Scenario: Verify each plan displays medical cover amount
     Given user is on the Travel Insurance page with valid inputs filled "Schengen"
    When user clicks on the Get Quote button
    And user is on the quote details page
    When user enters a valid "9944500943" and "vh12327@velhightech.com"
    And select the number of persons
    And user clicks on the Continue button

   Given user is on the curated plans page for medical cover
      
      When user reads the medical cover of each plan
       Then each plan should display a medical cover amount starting with "$"