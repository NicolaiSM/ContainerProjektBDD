Feature: find clients based one or more of it attributes 

	Background: attributes is contactperson firstname, contacperson lastname, contactperson email, client adress and client name

	Scenario: one or more cleints found matching one or more keywords
		Given a keyword "Netto" and a keyword "Jens Bearge"
    Given a list of clients with attributes; name: "Netto", address: "Hindbearsnittevej 23", contactperson name "Jens Bearge", contactperson email "Jens.Bearge@live.dk" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When Finding clients that matches keyword
    Then Check if any client is found
		
	Scenario: no clients found matching one or more keywords
		Given a keyword "Topsil" and a keyword "Julius Ceaser"
    When Finding clients that matches keyword
    Then Check that no result is found
		