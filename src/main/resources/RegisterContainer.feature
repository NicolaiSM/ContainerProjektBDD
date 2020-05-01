Feature: Register a container

  Scenario: Successful registration of container
  	Given A client
  	| clientname | address | contactperson | email			  | password |
		| Netto	  	 | Nyvej 1 | Jens Ole 		 | JO@netto.com | admin    |
    And Information about the journey
    | port of origin | destination | content |
		| Oslo					 | London		   | Cheese	 |
		And Ports
		| port 	 |
		| Oslo 	 |
		| London |
		And Containers
		| port 	 | journey |
		| Oslo 	 | none		 |
    When Registrering a container
    Then Container is registred

	Scenario: Unsuccessful registration of container: no inactive container in port
  	Given A client
  	| clientname | address | contactperson | email			  | password |
		| Netto	  	 | Nyvej 1 | Jens Ole 		 | JO@netto.com | admin    |
    And Information about the journey
    | port of origin | destination | content |
		| Oslo					 | London		   | Cheese	 |
		And Ports
		| port 	 |
		| Oslo 	 |
		| London |
		And Containers
		| port 	 | journey |
		| Oslo 	 | given	 |
		And A journey
		| port of origin | destination | content | client |
		| Oslo					 | London		   | Cheese	 | given	|
    When Registrering a container
    Then Container is not registred
    
  Scenario: Unsuccessful registration of container: ports are not valid
  	Given A client
  	| clientname | address | contactperson | email			  | password |
		| Netto	  	 | Nyvej 1 | Jens Ole 		 | JO@netto.com | admin    |
    And Information about the journey
    | port of origin | destination | content |
		| Oslo					 | London		   | Cheese	 |
		And Ports
		| port 	 |
		| Oslo 	 |
    When Registrering a container
    Then Container is not registred