Feature: Find a container by one or more keywords

  Scenario: Successful search for a container
		Given Keywords
		| keywords |
		| Oslo 	 	 |
		| Cheese 	 |
		Given Ports
		| port 			|
		| Oslo 		  |
		| Stockholm |
		Given Containers
  	| port 			| journey	|
		| Oslo			| given		|
		Given A client
  	| clientname | address | contactperson | email		 | password |
		| HM	  		 | Nyvej 2 | Jens Ole 		 | JO@hm.com | admin    |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | Stockholm   | Cheese	 | given	|
    When Searching for a container
    Then A container has been found
    
	Scenario: Unsuccessful search: no container matches input
		Given Keywords
		| keywords |
		| Oslo 	 	 |
		| Cheese 	 |
		When Searching for a container
		Then No containers found
		