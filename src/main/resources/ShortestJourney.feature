Feature: Get the shortest journey

  Scenario: Successful determination of the shortest journey
    Given a list of existing ports "Oslo", "Stockholm" and "London"
    And a container: port "Oslo" with journey: port of origin "Oslo", destination "Stockholm", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    And internal information: timestamp "13:44:32 May 8. 2020", location "Stockholm", temperature 9, humidity 64, pressure 1
    And a new journey with port of origin "Stockholm", destination "London", content "Cheese" with the same client
    And internal information: timestamp "14:44:32 May 9. 2020", location "Oslo", temperature 8, humidity 63, pressure 2
    When determining the shortest journey
    Then the shortest journey is found
    
  Scenario: Unsuccesful determination of the shortest journey
    When determining the shortest journey
    Then no journey found