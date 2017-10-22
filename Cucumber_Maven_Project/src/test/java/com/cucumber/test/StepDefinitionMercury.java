package com.cucumber.test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionMercury 
{
	WebDriver driver;
	Scenario scenario;
	
	@Before({"@Registration1,@Registration3"})
	public void testSetup(Scenario scenario)
	{
		  this.scenario= scenario;
		  driver = new FirefoxDriver();
		  System.out.println("Test Environment Set up ");
		  System.out.println("----------------------------------------------------------------------");
		  System.out.println("Executing Scenario :"+scenario.getName());
		  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@After({"@Registration1,@Registration3"})
	public void tearDown(Scenario scenario)
	{
		scenario.write("Finished scenario");
		if (scenario.isFailed())
				{
					scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"image/png");
				}
		System.out.println("Test Environment Destroyed ");
		System.out.println("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}
	
	@Given("^I've a valid set of data and access to Registration Page$")
	public void userValidityCheck() throws Throwable 
	{
/*		String mainwindow = driver.getWindowHandle();
		driver.switchTo().window(mainwindow);*/
		scenario.write("Navigating to Mercury Flight Demo Page");
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		driver.manage().window().maximize();
	}

	@When("^Registration page Displayed$")
	public void userOnRegistration() throws Throwable 
	{
		scenario.write("Navigating Registration page");
		WebDriverWait wait = new WebDriverWait(driver,10);	 	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER"))); 


//*********using javascript executor to open the registration page in same window********
	    WebElement register = driver.findElement(By.linkText("REGISTER"));	   
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('target','_self');",register);
	    register.click();  
	}

	@Then("^I enter valid data on page$")
	public void provideData(DataTable userData) throws Throwable 
	{
		List<List<String>> data = userData.raw();
		for(int i=0;i<data.size();i++)
		{
			System.out.println(data.get(i).toString());
		}
//******filling up details in register page******/
	    driver.findElement(By.cssSelector("input[name*='firstName']")).sendKeys(data.get(0).get(1).toString());
	    driver.findElement(By.cssSelector("input[name*='lastName']")).sendKeys(data.get(1).get(1).toString());
	    driver.findElement(By.cssSelector("input[name*='phone']")).sendKeys(data.get(2).get(1).toString());
	    driver.findElement(By.cssSelector("input[id*='userName']")).sendKeys(data.get(3).get(1).toString());
	    driver.findElement(By.xpath("//input[@id='email'][@name='email']")).sendKeys(data.get(4).get(1));
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(data.get(5).get(1));
	    driver.findElement(By.xpath("//input[starts-with(@name,'confirmP')]")).sendKeys(data.get(6).get(1)); 	
	
	    //scenario.write("Entering User Registration Details");
	}
	
	@Then("^I enter valid data on registration page and check if registration is successfull$")
	public void provideUserData(List<UserData> details) throws Throwable 
	{
	 	scenario.write("Entering User Registration Details");
		System.out.println("Total users :"+details.size());
		for(UserData user :details)
		{					 		 
			 System.out.println("Registering :"+user.getFirstName()+" "+user.getLastName());
			 
			 	driver.findElement(By.cssSelector("input[name*='firstName']")).sendKeys(user.getFirstName());
			    driver.findElement(By.cssSelector("input[name*='lastName']")).sendKeys(user.getLastName());
			    driver.findElement(By.cssSelector("input[name*='phone']")).sendKeys(user.getPhone());
			    driver.findElement(By.cssSelector("input[id*='userName']")).sendKeys(user.getEMail());
			    driver.findElement(By.xpath("//input[@id='email'][@name='email']")).sendKeys(user.getUserName());
			    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(user.getPassword());
			    driver.findElement(By.xpath("//input[starts-with(@name,'confirmP')]")).sendKeys(user.getConfirmPassword());
			    
			    driver.findElement(By.xpath("//input[contains(@src,'submit.gif')]")).click();
			    
//asserting post registration			    
			    String msg = driver.findElement(By.xpath("//*[contains(text(),'Thank you for registering')]")).getText();
			    System.out.println(msg);
			    Assert.assertTrue("Text is not getting displayed properly",msg.contains("Thank you for registering."));    

//going to register link after post successful registration of other user
			    WebElement register = driver.findElement(By.linkText("REGISTER"));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('target','_self');",register);
			    		   register.click();
		}
	}

	@Then("^I enter valid data on registration page and verify post-registration$")
	public void userData(DataTable usertable) throws Throwable 
	{	 	
	scenario.write("Entering User Registration Details");
		for (Map<String, String> user : usertable.asMaps(String.class,String.class))
		   {	
			    System.out.println("Registering :"+user.get("firstname")+" "+user.get("lastname"));
			 	driver.findElement(By.cssSelector("input[name*='firstName']")).sendKeys(user.get("firstname"));
			    driver.findElement(By.cssSelector("input[name*='lastName']")).sendKeys(user.get("lastname"));
			    driver.findElement(By.cssSelector("input[name*='phone']")).sendKeys(user.get("phone"));
			    driver.findElement(By.cssSelector("input[id*='userName']")).sendKeys(user.get("email"));
			    driver.findElement(By.xpath("//input[@id='email'][@name='email']")).sendKeys(user.get("username"));
			    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(user.get("password"));
			    driver.findElement(By.xpath("//input[starts-with(@name,'confirmP')]")).sendKeys(user.get("confirmpassword"));
			    
//click submit button			    
			    driver.findElement(By.xpath("//input[contains(@src,'submit.gif')]")).click();
//asserting post registration			    
			    String msg = driver.findElement(By.xpath("//*[contains(text(),'Thank you for registering')]")).getText();
			    System.out.println(msg);
			    Assert.assertTrue("Text is not getting displayed properly",msg.contains("Thank you for registering."));    

//going to register link after post successful registration of other user
			    WebElement register = driver.findElement(By.linkText("REGISTER"));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('target','_self');",register);
			    		   register.click();
			 }
			 
	}

	@Then("^Click on Submit Button$")
	public void clickSubmit() throws Throwable 
	{
		driver.findElement(By.xpath("//input[contains(@src,'submit.gif')]")).click();
	}

	@Then("^Thank you for registering: should be displayed$")
	public void registerText() throws Throwable 
	{	
		String msg = driver.findElement(By.xpath("//*[contains(text(),'Thank you for registering')]")).getText();
	    System.out.println(msg);
	    Assert.assertTrue("Text is not getting displayed properly",msg.contains("Thank you for registering."));    
	}

	@Then("^Click on Signoff$")
	public void clickSignOff() throws Throwable 
	{
		System.out.println("Clicking on ::"+driver.findElement(By.xpath("//*[contains(text(),'SIGN-OFF')]")).getText());
		driver.findElement(By.xpath("//*[contains(text(),'SIGN-OFF')]")).click();
	}

}

