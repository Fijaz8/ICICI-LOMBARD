
Feature: Verify validation message for invalid mobile number when purchasing insurance for a new vehicle
Background:
When User CLicks Car Insurance Nav in Home Page

Then User Redirects to Car Insurance Page

Scenario Outline: Verify validation message for invalid mobile number

When User selects Got a New Vehicle
And User enters an invalid mobile number "<mobile>"
And User clicks on Get Quote
Then User should see a "Please enter a valid mobile number" validation message

Examples:
|mobile|
|996203130|