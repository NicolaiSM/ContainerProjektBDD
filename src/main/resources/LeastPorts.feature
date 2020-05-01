
Feature: Get the container that visited the least ports

  Scenario: Successful determination of container that visited the least ports
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
    When Determining the container that visited the least ports
    Then The container that visited the least ports is found
    
  Scenario: Unsuccesful determination of container that visited the least ports: no containers exist
    When Determining the container that visited the least ports
    Then No container found