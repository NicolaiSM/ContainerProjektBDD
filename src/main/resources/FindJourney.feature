Feature: Find journey by one or more keywords

  Scenario: Successful search for a journey
		Given Keywords
		| keywords |
		| Oslo 	 	 |
		| Cheese 	 |
		Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given Ports
		| port	 |
		| Oslo   |
		| Tokyo  |
		| London |
		Given Containers
		| port 	|
		| Oslo 	|
		| Tokyo |
		Given Journeys
		| port of origin | destination | content | client |
		| Oslo					 | London		   | Cheese	 | given	|
		| Tokyo					 | London		   | Milk		 | given	|
    When Searching for a journey
    Then A journey has been found
	Scenario: Unsuccessful search: no journey matches input
		Given Keywords
		| keywords |
		| Oslo 	 	 |
		| Cheese 	 |
		When Searching for a journey
		Then The keyword does not match any journey
		