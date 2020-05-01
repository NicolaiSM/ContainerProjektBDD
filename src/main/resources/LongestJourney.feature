Feature: Get the longest journey

  Scenario: Successful determination of the longest journey
    Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given Ports
		| port 	 		|
		| Oslo 	 		|
		| London 		|
		| Stockholm |
		Given A container
		| port | journey |
		| Oslo | none		 |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | Stockholm	 | Cheese	 | given	|
		Given A container
		| port 	 | journey |
		| London | none		 |
		Given A journey
		| port of origin | destination | content | client |
		| London				 | Stockholm	 | Cheese	 | given	|
    When Determining the longest journey
    Then The longest journey is found
    
  Scenario: Unsuccesful determination of the longest journey
    When Determining the longest journey
    Then No journey found