package com.cucumber.test;

	import cucumber.api.PendingException;
	import cucumber.api.java.en.And;
	import cucumber.api.java.en.When;

	public class StepDefinitionDocString 
	{
		@When("^You Navigated to a the Website$")
		public void navigatedWebsite() throws Throwable 
		{
			System.out.println("Navigated to Website");
		}
		@And("^Check the website and found the details:$")
		public void checkDetails(String details) throws PendingException 
		{
			System.out.println("\nChecking Details");
			System.out.println("\nExpected Text : "+details);
			String websiteTxt = "Doc Strings are handy for passing a larger piece of text to a step definition.\nIf you need to specify information in a scenario that won't fit on a single line,use a DocString.";
			
					if (websiteTxt.equals(details)==true)
						{
							System.out.println("\nExpected Text Display");
						}
					else
				        	System.out.println("\nExpected Text Not Display");
		}
	}