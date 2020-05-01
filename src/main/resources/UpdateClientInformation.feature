Feature: Update client information

Background: We assume that the we are already given an existing client. Which is in the system.

  Scenario: Successful update of client information
    Given A client
  	| clientname | address 		 				 | contactperson | email				| password |
		| Netto 		 | Hindbærsnittevej 23 | Jens Børge		 | JB@netto.com | admin    |
    And Information that should be changed
    | key 			 | value 				|
    | clientName | Ghetto Netto |
    | address 	 | Hollywood 1	|
    When Updating the client
    Then Client has been updated
    
    
  Scenario: Unsuccessful update of client information: client name already exists
    Given Clients
  	| clientname   | address 		     | contactperson  | email			  | password |
		| Brugsen		   | Hybenvej 3 		 | Ole Hansen		  | OH@live.com | admin1   |
		| Ghetto Netto | Englandsvej 100 | Thomas Dhal		| TD@nn.com 	| admin2	 |
    Given A client
  	| clientname | address 		 				 | contactperson | email				| password |
		| Netto 		 | Hindbærsnittevej 23 | Jens Børge		 | JB@netto.com | admin    |
    And Information that should be changed
    | key 			 | value 				|
    | clientName | Ghetto Netto |
    | address 	 | Hollywood 1	|
    When Updating the client
    Then Client has not been updated
    


    
      
      