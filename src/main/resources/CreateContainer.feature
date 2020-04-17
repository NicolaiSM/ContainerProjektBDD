Feature: Create a new container

  Scenario: Successful creation of a new container
    Given a port of origin "Stockholm"
    And a list of ports containing the port "Stockholm"
    When creating a new container
    Then a new container has been added to the existing containers

  Scenario: Unsuccessful creation of a new container: the port is not registered
    Given a port of origin "Bern"
    And a list of ports containing the port "Stockholm"
    When creating a new container
    Then the container could not be created since the port was not a validport

    
