Feature: Activation of a SIM Card

  Scenario: Valid SIM Card activates
    Given a valid SIM Card
    When a request is submitted for a valid SIM Card
    Then the SIM Card gets activated and the outcome is saved in the database

  Scenario: Invalid SIM Card does not activate
    Given an invalid SIM Card
    When a request is submitted for an invalid SIM Card
    Then the SIM Card does not get activated and the outcome is saved in the database