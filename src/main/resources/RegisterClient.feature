Feature: Register Client
	
  Scenario: Succesful registration of client
  	Given information about a client; name "Netto", address "Langesvej 2, 4600 Køge", contactperson with name "David Bo", email of contactperson "DB@netto.com"
    When registering the client
    Then Client is registered
  
  Scenario: Unsuccesful registration of client because client name is already in use
    Given information about a client; name "Xlbyg", address "Englandsvej 102, 2300 København", contactperson with name "Rasmus Seebach", email of contactperson "RB@xlbyg.com"
    Given a list of clients with attributes; name: "Xlbyg", address: "Englandsvej 102, 2300 København", contactperson name "Rasmus Seebach", contactperson email "RB@xlbyg.com" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When registering the client
    Then Client registration failed



