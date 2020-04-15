#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: find clients based one or more of it attributes 

	Background: attributes is contactperson firstname, contacperson lastname, contactperson email, client adress and client name

	Scenario: one or more cleints found matching one or more keywords
		Given a name for a client "Netto" and a contactperson name "Jens Bearge"
    Given a database of clients with attributes name: "Netto", address: "Hindbearsnittevej 23", contactperson name "Jens Bearge", contactperson email "Jens.Bearge@live.dk" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When Finding clients that matches keyword
    Then Check if any client is found
		
	Scenario: no clients found matching one or more keywords
		Given a name for a client "Topsil" and a contactperson name "Julius Ceaser"
    Given a database of clients with attributes name: "Netto", address: "Hindbearsnittevej 23", contactperson name " Jens Bearge", contactperson email "Jens.Bearge@live.dk" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When Finding clients that matches keyword
    Then Check that no result is found
		