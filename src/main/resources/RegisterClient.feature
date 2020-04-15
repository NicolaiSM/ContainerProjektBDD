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
Feature: Register Client
	
  @tag1
  Scenario: Succesful registration of client
    Given a database of clients with attributes name: "Xlbyg", address: "Englandsvej 102, 2300 København", contactperson name "Rasmus Seebach", contactperson email "RB@xlbyg.com" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When registering a new client with name "Netto", address "Langesvej 2, 4600 Køge", contactperson with name "David Bo", email of contactperson "DB@netto.com"
    Then Client is registered
  
  @tag1
  Scenario: Unsuccesful registration of client because client name is already in use
    Given a database of clients with attributes name: "Xlbyg", address: "Englandsvej 102, 2300 København", contactperson name "Rasmus Seebach", contactperson email "RB@xlbyg.com" and name: "NovaNordisk", address: "Englandsvej 103, 2300 København", contactperson name: "Thomas Dhal", contactperson email: "TD@NN.com"
    When registering a new client with name "Xlbyg", address "Englandsvej 102, 2300 København", contactperson with name "Rasmus Seebach", email of contactperson "RB@xlbyg.com"
    Then Client is not registered



