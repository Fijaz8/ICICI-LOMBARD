Feature: Travel Insurance Navigation

  Scenario: Verify Travel Insurance page navigation

    Given user launches the ICICI Lombard website
    When user clicks on Travel Insurance from the top navigation menu
    Then the Travel Insurance quote form should be visible
    
    

Scenario: Verify user can select a Schengen country as travel region11 
   Given user is on the Travel Insurance page
       When user clicks on the Region of Travel dropdown
           And user selects "Schengen" as the destination country
               Then "Schengen" should be displayed as the selected country

 Scenario: Verify travel date selection behavior based on configured dates

    Given user is on the Travel Insurance page for date picker
    When user opens the Travel Start Date picker and select the "Schengen"
    And user attempts to enter the configured travel start and end dates
    Then travel dates should be validated based on whether start date is past or future