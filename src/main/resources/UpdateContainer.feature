Feature: Update a container

  Scenario: Successful update of a journey
    Given a journey with port of origin "Oslo", destination "London", content "Cheese" with the client: client name "HM", address "Nyvej 2", contact person "Jens Ole", email "jo@hm.com"
    And a datacollection of the exisiting internal information [timestamp: ["12:13:0020 May 6. 2020","20:17:7845 May 6. 2020","14:01:2487 May 7. 2020"] location: ["Oslo","Copenhagen","Stockholm"], temperature: [8,9,8], humidity: [60,65,63], pressure: [1,1,1]]
    And a list of the new internal information [timestamp: "13:44:3242 May 8. 2020", location: "Helsinki", temperature: 9, humidity: 64, pressure: 1]
    When updating a journey
    Then the journey is updated
