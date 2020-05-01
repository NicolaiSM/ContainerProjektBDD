Feature: Get client that is logged in

	Scenario: Successful login
		Given Clients
		| clientname | address | contactperson | email     | password |
		| Netto 	 	 | Vej 1   | Jens Ole      | JO@n.dk   | admin	  |
		| OK		 	   | Vej 2   | Niels Ole     | NO@n.dk   | admin2	  |
		And A username "Netto" and a password "admin"
		When Logging in
		Then Client is logged in
		
	Scenario: Unsuccessful login: wrong password
		Given Clients
		| clientname | address | contactperson | email     | password |
		| Netto 	 	 | Vej 1   | Jens Ole      | JO@n.dk   | admin	  |
		| OK		 	   | Vej 2   | Niels Ole     | NO@n.dk   | admin2	  |
		And A username "Netto" and a password "1234"
		When Logging in
		Then Client is not logged in
		
	Scenario: Unsuccessful login: wrong username
		Given A username "Netto" and a password "admin"
		When Logging in
		Then Client is not logged in