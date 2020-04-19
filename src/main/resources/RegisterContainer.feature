Feature: Register a container

  Scenario: Successful registration of container
    Given a client: name "Netto", address "Nyvej 1", contact person "Jens Ole", email "JO@netto.com"
    And information about the journey: port of origin "Oslo", destination "London", content "Cheese"
    And a list of existing containers: port "Oslo", port "London"
    When registrering a container
    Then container is registred

	Scenario: Unsuccessful registration of container: no inactive container in port
    Given a client: name "Netto", address "Nyvej 1", contact person "Jens Ole", email "JO@netto.com"
    And information about the journey: port of origin "Oslo", destination "London", content "Cheese"
    And a list of existing containers: port "London", port "Oslo" with journey port of origin "Oslo", destination "London", content "Cheese" with the client in the system
    When registrering a container
    Then container is not registred
    
  Scenario: Unsuccessful registration of container: ports are not valid
    Given a client: name "Netto", address "Nyvej 1", contact person "Jens Ole", email "JO@netto.com"
    And information about the journey: port of origin "Oslo", destination "London", content "Cheese"
    And a list of existing containers: port "Oslo"
    When registrering a container
    Then container is not registred