Feature: Update a journey

  Scenario: Successful update of a journey
    Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given Ports
		| port 	 		|
		| Oslo 	 		|
		| London 		|
		| Stockholm |
		Given A container
		| port 	 | journey |
		| Oslo 	 | given	 |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | London		   | Cheese	 | given	|
		Given New internal information
		| timestamp 	| location 	 | temperature | humidity | pressure |
		| Maj 8. 2020 | Stockholm	 | 9					 | 64				|	1				 |
		When Updating a journey
		Then The journey is updated
		
		
    Scenario: Successful update of a journey: journey is done
    Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given Ports
		| port 	 		|
		| Oslo 	 		|
		| London 		|
		| Stockholm |
		Given A container
		| port 	 | journey |
		| Oslo 	 | given	 |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | London		   | Cheese	 | given	|
		Given New internal information
		| timestamp 	| location | temperature | humidity | pressure |
		| Maj 8. 2020 | London	 | 9					 | 64				|	1				 |
		When Updating a journey
		Then The journey has ended
		
    Scenario: Unsuccessful update of a journey: port is not valid
    Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given Ports
		| port 	 		|
		| Oslo 	 		|
		| London 		|
		| Stockholm |
		Given A container
		| port 	 | journey |
		| Oslo 	 | given	 |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | London		   | Cheese	 | given	|
		Given New internal information
		| timestamp 	| location 	 | temperature | humidity | pressure |
		| Maj 8. 2020 | Bern	  	 | 9					 | 64				|	1				 |
		When Updating a journey
		Then The journey is not updated
		
    Scenario: Unsuccesful update of a journey: Container has no journey
		Given Ports
		| port 	 		|
		| Oslo 	 		|
		| London 		|
		| Stockholm |
		Given A container
		| port 	 | journey |
		| Oslo 	 | none		 |
		Given New internal information
		| timestamp 	| location 	 | temperature | humidity | pressure |
		| Maj 8. 2020 | Bern	  	 | 9					 | 64				|	1				 |
		When Updating a journey
		Then The journey is not updated
    