Feature: Update client information

Background: We assume that the we are already given an existing client. Which is in the system.

  Scenario: Successful update of client information
    Given a client with name: "Netto", address: "Hindbærsnittevej 23", contactperson name: "Jens Børge", contactperson email: "Jens.Børge@live.dk", password "admin"
    And Client wants to update the client information "clientName" to "GhettoNetto" and "address" to "Hollywood"
    When Change previous client information to the given information
    Then Client has been updated
    
    
  Scenario: Unsuccessful update of client information: client name already exists
    Given a list of clients with attributes; name: "Brugsen", address: "Hybenvej 3", contactperson name "Ole Hansen", contactperson email "OH@live.dk", password: "admin1" and name: "GhettoNetto", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com", password: "admin2"
    Given a client with name: "Netto", address: "Hindbærsnittevej 23", contactperson name: "Jens Børge", contactperson email: "Jens.Børge@live.dk", password "admin"
    And Client wants to update the client information "clientName" to "GhettoNetto" and "address" to "Hollywood"
    When Change previous client information to the given information
    Then Client has not been updated
    


    
      
      