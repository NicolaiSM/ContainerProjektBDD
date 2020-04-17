
Feature: Create new journey

  Scenario: Succesful creation of journey
    Given information of new journey with Port Of Origin "Stockholm", Destination "Copenhagen", Content "Milk" and a Client
    And list of all ports "Stockholm" and "Copenhagen"
    When Creating a Journey
    Then Check that new journey is created

	Scenario: Unsuccesful creation of journey
		Given Port Of Origin "Stockholm", Destination "Moon", Content "Milk" and a Client
		And list of all ports "Stockholm" and "Copenhagen"
		When Creating a Journey
    Then Check that new journey is not created