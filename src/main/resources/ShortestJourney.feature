Feature: Get the shortest journey

  Scenario: Successful determination of the shortest journey
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
		Given New internal information
		| timestamp 	| location 	 | temperature | humidity | pressure |
		| Maj 8. 2020 | London		 | 9					 | 64				|	1				 |
		Given A container
		| port 	 | journey |
		| London | none		 |
		Given A journey
		| port of origin | destination | content | client |
		| London				 | Stockholm	 | Cheese	 | given	|
    When Determining the shortest journey
    Then The shortest journey is found
    
  Scenario: Unsuccesful determination of the shortest journey
    When Determining the shortest journey
    Then No journey found