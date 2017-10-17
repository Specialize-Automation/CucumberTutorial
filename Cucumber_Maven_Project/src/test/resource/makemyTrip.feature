#Author: aditya.kgec91@gmail.com
Feature: User SignIn validation in MakeMyTrip

  Background: 
    Given I have active accounts to sign in

  #DataTable for UserDetails
  @makemytrip-signin
  Scenario: Single User signin via facebook
  
    When MakeMytrip Home page displayed
    Then I check for presence for makemytrip logo
     And Check for Page Title
      """
      MakeMyTrip
      """
    Then I click on login option
     And Check Continue with facebook or Login with Google in Login page
    When Clicked on Continue with facebook
    Then Facebook Signin Popup Display
    Then I provide my email and click next
      | Email | aditya.kgec91@gmail.com |
     And I provide my password
      | Password | UnVzc2lhQDczMjU5OA== |
    Then Click on Login
    Then User will be able to login successfully
     And User should be displayed properly
    When User clicked on logout
    Then User should be able to logged out successfully
#-------------------------------------------------------------------------------------------------------------   
  #MMT Footer Validation authenticated
	@makemytrip-footerValidationAUTH
	Scenario: Footer Validation in authenticated stage
	
	 When MakeMytrip Home page displayed
   Then I check for presence for makemytrip logo
   Then I click on login option
     And Check Continue with facebook or Login with Google in Login page
    When Clicked on Continue with facebook
    Then Facebook Signin Popup Display
    Then I provide my email and click next
      | Email | aditya.kgec91@gmail.com |
     And I provide my password
      | Password | UnVzc2lhQDczMjU5OA== |
    Then Click on Login
    Then User will be able to login successfully
     And User should be displayed properly
     And Scroll down to check for WHY MAKE MY TRIP
      """
      The leading player in online flight bookings in India, MakeMyTrip offers great offers, some of the lowest airfares, exclusive discounts and a seamless online booking experience. Flight, hotel and holiday bookings through the desktop or mobile site is a delightfully customer friendly experience, and with just a few clicks you can complete your booking. With features like Instant Discounts, Fare Calendar, MyRewards Program, MyWallet and many more, the overall booking experience with MakeMyTrip constantly adds value to its product and continues to offer the best to its customers.
      """
     And Check for BOOKING FLIGHT WITH MMT
      """
      Book your flights tickets with India’s leading flight booking company since the year 2000. While booking flights with MakeMyTrip, you can expect the ultimate online booking experience. With premium customer service, 24/7 dedicated helpline for support, and over 5 million delighted customers, MakeMyTrip takes great pride in enabling customer satisfaction. With a cheapest flight guarantee, book your tickets at the lowest airfares. Avail great offers, exclusive deals for loyal customers and get instant updates for your flight status and fare drops.
      """
     And Check for DOMESTIC FLIGHT WITH MMT
      """
      MakeMyTrip is India’s leading player for flight bookings, and have a dominant position in the domestic flights sector. With the cheapest fare guarantee, experience great value at the lowest price. Instant notifications ensure current flight status, instant fare drops, amazing discounts, instant refunds and rebook options, price comparisons and many more interesting features.
      """
    When User clicked on logout
    Then User should be able to logged out successfully
#-------------------------------------------------------------------------------------------------------------     
  #MMT Footer Validation Unauthenticated
  @makemytrip-footerValidation
  Scenario: Footer Validation in unauthenticated stage
  
    When MakeMytrip Home page displayed
    Then I check for presence for makemytrip logo
     And Scroll down to check for WHY MAKE MY TRIP
      """
      The leading player in online flight bookings in India, MakeMyTrip offers great offers, some of the lowest airfares, exclusive discounts and a seamless online booking experience. Flight, hotel and holiday bookings through the desktop or mobile site is a delightfully customer friendly experience, and with just a few clicks you can complete your booking. With features like Instant Discounts, Fare Calendar, MyRewards Program, MyWallet and many more, the overall booking experience with MakeMyTrip constantly adds value to its product and continues to offer the best to its customers.
      """
     And Check for BOOKING FLIGHT WITH MMT
      """
      Book your flights tickets with India’s leading flight booking company since the year 2000. While booking flights with MakeMyTrip, you can expect the ultimate online booking experience. With premium customer service, 24/7 dedicated helpline for support, and over 5 million delighted customers, MakeMyTrip takes great pride in enabling customer satisfaction. With a cheapest flight guarantee, book your tickets at the lowest airfares. Avail great offers, exclusive deals for loyal customers and get instant updates for your flight status and fare drops.
      """
     And Check for DOMESTIC FLIGHT WITH MMT
      """
      MakeMyTrip is India’s leading player for flight bookings, and have a dominant position in the domestic flights sector. With the cheapest fare guarantee, experience great value at the lowest price. Instant notifications ensure current flight status, instant fare drops, amazing discounts, instant refunds and rebook options, price comparisons and many more interesting features.
      """
