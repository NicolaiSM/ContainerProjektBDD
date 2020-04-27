Feature: Update a container

  Scenario: Successful update of a journey
    Given a list of existing ports "Oslo", "Stockholm" and "London"
    And a container: port "Oslo" with journey: port of origin "Oslo", destination "Stockholm", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    And new internal information: timestamp "13:44:32 May 8. 2020", location "London", temperature 9, humidity 64, pressure 1
    When updating a journey
    Then the journey is updated
    
    Scenario: Successful update of a journey: journey is done
    Given a list of existing ports "Oslo", "Stockholm" and "London" 
    And a container: port "Oslo" with journey: port of origin "Oslo", destination "London", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    And new internal information: timestamp "13:44:32 May 8. 2020", location "London", temperature 9, humidity 64, pressure 1
    When updating a journey
    Then the journey has ended
    
    Scenario: Unsuccessful update of a journey: port is not valid
    Given a list of existing ports "Oslo", "Stockholm" and "London" 
    And a container: port "Oslo" with journey: port of origin "Oslo", destination "London", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com", password "admin"
    And new internal information: timestamp "13:44:32 May 8. 2020", location "Bern", temperature 9, humidity 64, pressure 1
    When updating a journey
    Then the journey is not updated
    
    Scenario: Unsuccesful update of a journey: Container has no journey
    Given a list of existing ports "Oslo", "Stockholm" and "London" 
    And a container: port "Oslo" with no journey
    And new internal information: timestamp "13:44:32 May 8. 2020", location "Bern", temperature 9, humidity 64, pressure 1
    When updating a journey
    Then the journey is not updated
    
    
    