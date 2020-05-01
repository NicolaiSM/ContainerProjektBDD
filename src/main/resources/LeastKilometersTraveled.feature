Feature: Get the container that has traveled the least kilometers

  Scenario: Successful determination of container that traveled the least
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
    When Determining the container that traveled the least
    Then The container that traveled the least is found
    
  Scenario: Unsuccesful determination of container that traveled the least: no containers exist
    When Determining the container that traveled the least
    Then No container found