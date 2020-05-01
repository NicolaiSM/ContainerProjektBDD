	Feature: Create a new container

  Scenario: Successful creation of a new container
    Given A port "Stockholm"
    And Existing ports
    | port      |
    | Stockholm |
    When Creating a new container
    Then The container is created

  Scenario: Unsuccessful creation of a new container: the port is not registered
    Given A port "Bern"
    And Existing ports
    | port      |
    | Stockholm |
    When Creating a new container
    Then The container could not be created