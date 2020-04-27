Feature: Find journey by one or more keywords

  Scenario: Successful search for a journey
    Given a keyword "Oslo" and a keyword "Cheese"
    And a list of existing journeys: port of origin "Oslo", destination "London", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    When searching for a journey
    Then a journey has been found
	Scenario: Unsuccessful search: no journey matches input
		Given a keyword "London" and a keyword "Cheese"
		When searching for a journey
		Then the keyword does not match any journey
		