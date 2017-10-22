#Author: aditya.kgec91@gmail.com
Feature: User Registration in MecuryFlight site

  Background: 
    Given I've a valid set of data and access to Registration Page
	 
	  #DataTable for UserDetails using raw()
  @Registration1
  Scenario: Single User Registration
    When Registration page Displayed
    Then I enter valid data on page
      | FirstName       | Aditya     |	
      | LastName        | Roy        |
      | Phone           | 7501451189 |
      | Email           | a@test.com |
      | UserName        | aditya     |
      | Password        | test123    |
      | ConfirmPassword | test123    |
    Then Click on Submit Button
     And Thank you for registering: should be displayed
    Then Click on Signoff
     And Close the Browser
     
     #Using Map in DataTable
  @Registration2
  Scenario: Multiple User Registration using HASHMAP
    When Registration page Displayed
    Then I enter valid data on registration page and verify post-registration
      | firstname | lastname | phone      | email        | username | password | confirmpassword |
      | Aditya    | Roy      | 7501451187 | a@text.com   | aditya91 | test123  | test123         |
      | Rakesh    | Sinha    | 7589866698 | b@rars.com   | rakesh90 | test123  | test123         |
      | Preety    | Sharma   | 8598745805 | preety@t.com | preety18 | test123  | test123         |
     And Close the Browser
     
	  
  #Using POJO in DataTable
  @Registration3
  Scenario: Multiple User Registration using POJO
    When Registration page Displayed
    Then I enter valid data on registration page and check if registration is successfull
      | firstname | lastname | phone      | email        | username | password | confirmpassword |
      | Aditya    | Roy      | 7501451187 | a@text.com   | aditya91 | test123  | test123         |
      | Rakesh    | Sinha    | 7589866698 | b@rars.com   | rakesh90 | test123  | test123         |
      | Preety    | Sharma   | 8598745805 | preety@t.com | preety18 | test123  | test123         |
     And Close the Browser