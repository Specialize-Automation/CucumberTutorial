#Author: aditya.kgec91@gmail.com
Feature: Amazon SignIn

  Background: 
    Given Chrome only validation

  @chrome
  Scenario: User navigate to AmazonWebsite
    When Amazon Home page display
    Then I click on Signin option
     And Amazon sign in page display
    Then I validate the sign in page
    And Close the Amazon site

    
    
    