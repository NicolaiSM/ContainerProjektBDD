
Feature: Get the container that visited the most ports

  Scenario: Successful determination of container that visited the most ports
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
		| Oslo | given	 |
		Given A journey
		| port of origin | destination | content | client |
		| Oslo					 | Stockholm	 | Cheese	 | given	|
		Given Internal information
		| timestamp 	| location 	 | temperature | humidity | pressure |
		| Maj 8. 2020 | London		 | 9					 | 64				|	1				 |
		| Maj 9. 2020 | Stockholm	 | 9					 | 64				|	1				 |
		Given A journey
		| port of origin | destination | content | client |
		| Stockholm			 | London			 | Cheese	 | given	|
		Given A container
		| port 	 | journey |
		| London | none		 |
    When Determining the container that visited the most ports
    Then The container that visited the most ports is found
    
  Scenario: Unsuccesful determination of container that visited the most ports: no containers exist
    When Determining the container that visited the most ports
    Then No container found