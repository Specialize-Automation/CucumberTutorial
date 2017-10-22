#Author: aditya.kgec91@gmail.com
Feature: Facebook create page

  Background: 
    Given IE browser only validation

  @iebrowser
  Scenario: User navigate to create page
    When Navigated to facebook login
    Then I click on create a page
     And Create page should be displayed
    Then Validate and close
