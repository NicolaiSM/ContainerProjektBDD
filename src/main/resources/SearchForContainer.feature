@tag
Feature: Search for a container by a keyword

  @tag1
  Scenario: Successful search for a container
    Given a keyword "Cheese"
    And a list of existing containers [id: "CON-11223344", port: "Oslo", port of origin: "Helsinki", destination: "London," content: "Cheese", company: "Netto"]
    When searching for a container
    Then a container has been found
	Scenario: Unsuccessful search: no container matches input
		Given: a keyword "Cheese"
		And a list of existing containers [id: "CON-11223344", port: "Oslo", port of origin: "Helsinki", destination: "London," content: "Bacon", company: "Netto"]
		When searching for a container
		Then the keyword does not match any container
		