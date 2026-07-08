Feature: Verify validation messages when City and Car Model are not provided

Scenario: Verify validation messages for blank City and Car Model fields

When User leaves City and Car Model fields blank
And User clicks Proceed button
Then User should see the "Please select valid city of registration" validation message
And User should see the "Please enter a valid vehicle details" validation message