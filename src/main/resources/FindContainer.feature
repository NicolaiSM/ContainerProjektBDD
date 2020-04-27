Feature: Find a container by one or more keywords

  Scenario: Successful search for a container
		Given a keyword "Oslo" and a keyword "Cheese"
		And a client: name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    And a list of existing containers: port "Stockholm", port "Oslo" with journey: port of origin "Oslo", destination "Stockholm", content "Cheese" with the client in the system
    When searching for a container
    Then a container has been found
    
	Scenario: Unsuccessful search: no container matches input
		Given a keyword "London" and a keyword "Cheese"
		When searching for a container
		Then the keyword does not match any container
		