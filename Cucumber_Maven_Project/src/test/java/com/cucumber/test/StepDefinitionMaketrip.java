package com.cucumber.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cucumber.pages.MMTpage;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.PwdEncryption;

public class StepDefinitionMaketrip 
{
	WebDriver driver;
	Scenario scenario;
	
	@Before({"@makemytrip-footerValidation,@makemytrip-signin,@makemytrip-footerValidationAUTH"})
	public void IEBrowserSetup(Scenario scenario) throws NullPointerException
	{
		System.out.println("IE Browser Test Environment created");
		System.setProperty("webdriver.ie.driver","D:/Browser Driver/IE Driver/IEDriverServer340X32bit.exe");
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(dc);
		System.out.println("Test Environment Set up");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Executing Scenario :"+scenario.getName());
		driver.navigate().to("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@After({"@makemytrip-footerValidation,@makemytrip-signin,@makemytrip-footerValidationAUTH"})
	public void tearDown(Scenario scenario) throws NullPointerException
	{
		scenario.write("Finished scenario");
		if (scenario.isFailed())
				{
					scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"image/png");
				}
		System.out.println("Test Environment Destroyed");
		System.out.println("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}

	@Given("^I have active accounts to sign in$")
	public void userCheck() throws Throwable 
		{
			System.out.println("User having valid ID and having Password");
		}
	    
	@When("^MakeMytrip Home page displayed$")
	public void makemytripHomePage() throws Throwable 
	{
		System.out.println("Navigating to MakeMyTrip homepage");
	}

	@Then("^I check for presence for makemytrip logo$")
	public void checkMakemytriplogo() throws Throwable
	{
	    MMTpage mmt = new MMTpage(driver);
	    try
	    {
	    	if(mmt.MMTlogo().isDisplayed())
	    		{
	    			System.out.println("Logo displayed successfully");
	    		}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("No logo found");
	    	e.printStackTrace();
	    }    
	}

	@And("^Check for Page Title$")
	public void checkTitle(String title) throws Throwable 
	{
	    System.out.println("Asserting :"+ title);
	    Assert.assertTrue("MMT home page not opened",driver.getTitle().contains(title));
	}

	@Then("^I click on login option$")
	public void clicklogin() throws Throwable 
	{
		MMTpage mmt = new MMTpage(driver);
		System.out.println("Clicking on :"+mmt.MMTLogin().getText());
		mmt.MMTLogin().click();
	}

	@And("^Check Continue with facebook or Login with Google in Login page$")
	public void checkLoginOptions() throws Throwable 
	{
		MMTpage mmt = new MMTpage(driver);
		try
		{
			if(mmt.MMTLoginGoogle().isDisplayed())
			{
				System.out.println(mmt.MMTLoginGoogle().getText()+" : Displayed");
			}
		}
	    catch(NoSuchElementException e)
	    {
	    	System.out.println("Sign up with google not found");
	    	e.printStackTrace();
	    } 
//there are two iframes ( one hidden )available, moving to index 1 (2nd iframe)
		List<WebElement> frameList = driver.findElements(mmt.FBiframe);
		System.out.println("Total frame( including hidden) :"+frameList.size());
		driver.switchTo().frame(frameList.get(1));
	         try
	          {
	        	 if(mmt.MMTLoginFB().isDisplayed())
	        	 {System.out.println(mmt.MMTLoginFB().getText()+" : Displayed");}
	          }
	         catch(NoSuchElementException e)
	         {
	        	 System.out.println("Not found");
	        	 e.printStackTrace();
	         }
	}

	@When("^Clicked on Continue with facebook$")
	public void click_facebook() throws Throwable 
	{
		MMTpage mmt = new MMTpage(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", mmt.MMTLoginFB());
		System.out.println("Clicking on Facebook Login");		
		Thread.sleep(5000);
	}

	@Then("^Facebook Signin Popup Display$")
	public void facebook_Signin_Popup() throws Throwable 
	{
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Total window :"+handles.size());
		if(handles.iterator().hasNext())
		{
			driver.switchTo().window(handles.get(1));
			System.out.println("Switched to :"+driver.getTitle());
			Assert.assertTrue("Facebook login popup not displayed", driver.getTitle().equalsIgnoreCase("facebook"));
		}

	}

	@Then("^I provide my email and click next$")
	public void enterEmail(DataTable email) throws Throwable 
	{
		MMTpage mmt = new MMTpage(driver);
		List<List<String>> data = email.raw();
		for(int i=0;i<data.size();i++)
		{
			System.out.println(data.get(i).toString());
		}
		mmt.enterEmail(data.get(0).get(1).toString());
	}

	@And("^I provide my password$")
	public void enterPassWord(DataTable password) throws Throwable 
	{	
		PwdEncryption pwd = new PwdEncryption(driver);
		MMTpage mmt = new MMTpage(driver);
		List<List<String>> data = password.raw();
		for(int j=0;j<data.size();j++)
		{
			System.out.println(data.get(j).toString());
		}
		mmt.enterPass(pwd.passwordDecoder(data.get(0).get(1).toString()));
	}
	
	@Then("^Click on Login$")
	public void clickLogin() throws InterruptedException
	{
		MMTpage mmt = new MMTpage(driver);
		Assert.assertTrue("Login Button Missing", driver.findElement(mmt.Login).getAttribute("value").equals("Log In"));
		driver.findElement(mmt.Login).click();
		Thread.sleep(5000);
	}
	
	@Then("^User will be able to login successfully$")
	public void afterLoginCheck() throws Throwable 
	{
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Total window :"+handles.size());
		driver.switchTo().window(handles.get(0));
		System.out.println("Switched back to :"+driver.getTitle());
	}
	
	@And("^User should be displayed properly$")
	public void loggedUserVerify() throws Throwable 
	{
		MMTpage mmt = new MMTpage(driver);
		String a = driver.findElement(mmt.loggedUser).getText();

			if(driver.findElement(mmt.loggedUser).getText().contains("HEY ADI"))
			{
				System.out.println(a+": user Logged in successfully");
			}
	       else
		    {
			    System.out.println("User not found");
		    }
	}
	
	@When("^User clicked on logout$")
	public void loggingOut() throws Throwable
	{
		MMTpage mmt = new MMTpage(driver);
		Actions action = new Actions(driver); 
        action.moveToElement(driver.findElement(mmt.menu)).build().perform();
 
        System.out.println("Clicking on :"+driver.findElement(mmt.logout).getText());
        driver.findElement(mmt.logout).click();
        Thread.sleep(3000);
	}
	
	@Then("^User should be able to logged out successfully$")
	public void afterLogoutCheck() throws Throwable
	{
		MMTpage mmt = new MMTpage(driver);
		try
		{
			Assert.assertFalse("User still logged in", driver.findElement(mmt.loggedUser).getText().contains("HEY ADI"));
			Assert.assertTrue("User still logged in", mmt.MMTLogin().getText().equalsIgnoreCase("LOG-IN"));
			System.out.println("User logged out successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@And("^Scroll down to check for WHY MAKE MY TRIP$")
	public void footer1Check(String text)
	{
		MMTpage mmt = new MMTpage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(mmt.footer_1));
		try 
		{
			if(mmt.footer_1().isDisplayed())
			{
				System.out.println("Asserting :"+mmt.footer_1().getText()+"'s summary Text");
				Assert.assertTrue("Text1 goes missing in footer", mmt.ans_1().getText().equalsIgnoreCase(text));
			}
			
		} catch(NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
	
	@And("^Check for BOOKING FLIGHT WITH MMT$")
	public void footer2Check(String text)
	{
		MMTpage mmt = new MMTpage(driver);
		try 
		{
			if(mmt.footer_1().isDisplayed())
			{
				System.out.println("Asserting :"+mmt.footer_2().getText()+"'s summary Text");
				Assert.assertTrue("Text2 goes missing in footer", mmt.ans_2().getText().equalsIgnoreCase(text));
			}
			
		} catch(NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
		
	@And("^Check for DOMESTIC FLIGHT WITH MMT$")
	public void footer3Check(String text)
	{
		MMTpage mmt = new MMTpage(driver);
		try 
		{
			if(mmt.footer_1().isDisplayed())
			{
				System.out.println("Asserting :"+mmt.footer_3().getText()+"'s summary Text");
				Assert.assertTrue("Text3 goes missing in footer", mmt.ans_3().getText().contentEquals(text));
			}
			
		} catch(NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}	
}
