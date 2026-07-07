Feature: ICICI Lombard Website Automation - Health & Bike Insurance Flows

  # ============================================================
  # TS_4 - Health Insurance Test Cases
  # ============================================================


  @TC_26 @TS_5 @LiveChat @Navigation
  Scenario: TC_26 - Handle Live Chat popup and navigate back to Home page
    Given User is on an inner page of ICICI Lombard site
    When User handles the Live Chat popup if it auto-opens
    And User clicks on ICICI Lombard logo to return to Home page
    Then Home page should be loaded successfully