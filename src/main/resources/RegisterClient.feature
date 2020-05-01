Feature: Register Client
	
  Scenario: Succesful registration of client
  	Given Information about a client
  	| clientname | address 		 | contactperson | email				| password |
		| Netto			 | Langesvej 2 | David Bo			 | DB@netto.com | admin    |
    When Registering the client
    Then Client is registered
  
  Scenario: Unsuccesful registration of client because client name is already in use
  	Given Information about a client
  	| clientname | address 		 | contactperson | email				| password |
		| XL-byg		 | Langesvej 2 | David Bo			 | DB@xl.com    | admin    |
		Given Clients
  	| clientname   | address 		     | contactperson  | email			| password |
		| XL-byg		   | Englandsvej 102 | Rasmus Seebach	| RB@xl.com | admin1   |
		| Novo Nordisk | Ribevej 4  		 | Thomas Dhal		| TD@nn.com | admin2	 |
    When Registering the client
    Then Client could not be registered



