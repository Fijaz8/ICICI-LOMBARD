Feature: Verify user is redirected to the Car Model Selection page after entering a valid mobile number for a new vehicle

Scenario Outline: Verify redirection to Car Model Selection page with valid mobile number

When User selects Got a New Vehicle option
And User enters a valid mobile number "<mobile>"
And User clicks on Get Quote button
Then User should be redirected to the Car Model Selection page

Examples:
| mobile     |
| 9962031305 |