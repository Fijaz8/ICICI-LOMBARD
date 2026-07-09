Feature: Verify user can edit the car details in the Plan page 

Background: 
When User CLicks Car Insurance Nav in Home Page
Then User Redirects to Car Insurance Page
And User selects Got a New Vehicle option

Scenario Outline:
Verify Car Details are Editable 

When User enters a valid mobile number "<mobile>"
And User clicks on Get Quote button
Then User should be redirected to the Car Model Selection page

When User gives value for city "<cityName>"
And User selects valid Car Model
And User clicks the Proceed button

When User clicks Edit details option 
And User checks Car details are editable 
Then User clicks Update button 

Examples:
| mobile     | cityName |
| 9962031305 | PUNE    |