Feature: Create a new container

  Scenario: Successful creation of a new container
    Given a port of origin "Stockholm"
    And a list of ports containing the port "Stockholm"
    And a list of existing containers containing a container in port "Stockholm" and in port "Copenhagen"
    When creating a new container
    Then a new container has been added to the existing containers

  Scenario: Unsuccessful creation of a new container: the port
    Given a port of origin "Bern"
    And a list of ports containing the port "Stockholm"
    And a list of existing containers containing a container in port "Stockholm" and in port "Copenhagen"
    When creating a new container
    Then the container could not be created since the port was not a validport

    
