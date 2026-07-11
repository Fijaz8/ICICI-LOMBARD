Feature: Verify validation messages when City and Car Model are not provided

Background:
When User CLicks Car Insurance Nav in Home Page

Then User Redirects to Car Insurance Page

When User selects Got a New Vehicle option

@TS3
Scenario Outline: Verify validation messages for blank City and Car Model fields

And User enters a valid mobile number "<mobile>"
And User clicks on Get Quote button
Then User should be redirected to the Car Model Selection page
When User leaves City and Car Model fields blank
And User clicks Proceed button
Then User should see the "Please select valid city of registration" validation message

Examples:
|mobile|
|9962031305|