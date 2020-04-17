
Feature: Register Port

  Scenario: Successful registration
    Given a list of ports containing "Copenhagen"
    And a port to be registered "Stockholm"
    When Registering the port
    Then Port is registered

  Scenario: Unsuccessful registration: port already exists
  	Given a list of ports containing "Stockholm"
    And a port to be registered "Stockholm"
    When Registering the port
    Then Port is not registered