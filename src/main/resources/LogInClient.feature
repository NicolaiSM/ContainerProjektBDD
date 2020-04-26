Feature: Get client that is logged in

	Scenario: Successful login
		Given clients
		| clientname | address | contactperson | email     | password |
		| Netto 	 	 | Vej 1   | Jens Ole      | JO@n.dk   | admin	  |
		| OK		 	   | Vej 2   | Niels Ole     | NO@n.dk   | admin2	  |
		And a username "Netto" and a password "admin"
		When logging in
		Then client is logged in
		
	Scenario: Unsuccessful login: wrong password
		Given clients
		| clientname | address | contactperson | email     | password |
		| Netto 	 	 | Vej 1   | Jens Ole      | JO@n.dk   | admin	  |
		| OK		 	   | Vej 2   | Niels Ole     | NO@n.dk   | admin2	  |
		And a username "Netto" and a password "1234"
		When logging in
		Then client is not logged in
		
	Scenario: Unsuccessful login: wrong username
		Given a username "Netto" and a password "admin"
		When logging in
		Then client is not logged in