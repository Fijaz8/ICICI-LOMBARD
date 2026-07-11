Feature: Verify user is redirected to the Plan Selection page after entering valid vehicle details

Background:
When User CLicks Car Insurance Nav in Home Page
Then User Redirects to Car Insurance Page
And User selects Got a New Vehicle option

@TS3
Scenario Outline: Verify redirection to Plan Selection page with valid details

When User enters a valid mobile number "<mobile>"
And User clicks on Get Quote button
Then User should be redirected to the Car Model Selection page

When User gives value for city "<cityName>"
And User selects valid Car Model
And User clicks the Proceed button
Then User should be redirected to the plan selection page

Examples:
| mobile     | cityName |
| 9962031305 | PUNE    |