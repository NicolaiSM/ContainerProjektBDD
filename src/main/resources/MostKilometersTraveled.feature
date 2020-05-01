Feature: Get the container that has traveled the most kilometers
  
  Scenario: Successful determination of container that traveled the most
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
    When Determining the container that traveled the most
    Then The container that traveled the most is found
  
  Scenario: Unsuccesful determination of container that traveled the most: no containers exist
    When Determining the container that traveled the most
    Then No container found