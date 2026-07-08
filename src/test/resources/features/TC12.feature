
Feature: Verify validation messages for mandatory fields

Scenario: Verify validation messages when car registration number and mobile number are blank

When User leaves Car Registration Number and Mobile Number fields blank
And User clicks Get Quote
Then User should see "Please enter a valid vehicle registration no" validation message
And User should see "Please enter a valid mobile number" validation message
