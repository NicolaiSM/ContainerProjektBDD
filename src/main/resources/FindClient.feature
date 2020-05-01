Feature: Find clients based one or more of it attributes 

	Background: attributes is contactperson firstname, contacperson lastname, contactperson email, client adress and client name

	Scenario: one or more cleints found matching one or more keywords
		Given Keywords
		| keywords 	 |
		| Netto 	 	 |
		| Ole Hansen |
    Given Clients
  	| clientname   | address 		     | contactperson  | email			  | password |
		| Brugsen		   | Hybenvej 3 		 | Ole Hansen		  | OH@live.com | admin1   |
		| Ghetto Netto | Englandsvej 100 | Thomas Dhal		| TD@nn.com 	| admin2	 |
    When Finding clients that matches keyword
    Then One or more clients found
		
	Scenario: no clients found matching one or more keywords
		Given Keywords
		| keywords 	 |
		| Netto 	 	 |
		| Ole Hansen |
    When Finding clients that matches keyword
    Then No clients are found
		