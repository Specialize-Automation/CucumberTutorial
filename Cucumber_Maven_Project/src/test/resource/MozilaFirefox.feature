#Author: aditya.kgec91@gmail.com
Feature: Facebook forgot account

  Background: 
    Given Firefox browser only validation

  @mozila
  Scenario: User navigate to FUIP flow
    When facebook home page display
    Then I click on forgot account link
     And Forgot password page should be displayed
    Then I close my browser
