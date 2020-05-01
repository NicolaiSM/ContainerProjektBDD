
Feature: Register Port

  Scenario: Successful registration
    Given A port to be registered "Copenhagen"
    When Registering the port
    Then Port is registered

  Scenario: Unsuccessful registration: port already exists
    And Ports
    | port      |
    | Stockholm |
    And A port to be registered "Stockholm"
    When Registering the port
    Then Port is not registered