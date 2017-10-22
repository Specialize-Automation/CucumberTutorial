package com.cucumber.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.PwdEncryption;
import cucumber.api.java.en.Then;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StepDefinitionFacebook
{
	WebDriver driver;
	Scenario scenario;

	/* we passing more than two tag name, for which Before/After method need to execute
	@Before({"@RegressionTest1,@RegressionTest2"})
	@After({"@RegressionTest1,@RegressionTest2"})
	***********************************************************************************/
	
	@Before({"@RegressionTest2"})
	public void testSetup(Scenario scenario) throws NullPointerException
	{
		driver = new FirefoxDriver();
		System.out.println("Test Environment Set up");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Executing Scenario :"+scenario.getName());
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	@After({"@RegressionTest2"})
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
	
  @Given("^I am a FaceBook User and have credentials$")
  public void userCheck() throws Throwable 
  {
	  System.out.println("User having valid ID and having Password");
  }
  @When("^I enter facebook URL$")
  public void navigateFaceBook() throws Throwable
  {
	  //System.setProperty("webdriver.gecko.driver","D:\\FireFox Gecko Driver\\geckodriver.exe");
	  driver.navigate().to("https://www.facebook.com");
	  driver.manage().window().maximize();
	  //scenario.write("Navigating TO FACEBOOK");
  }
  @Then("^I went to facebook Page$")
  public void facebookLogin() throws Throwable
  { 
	 System.out.println("Page Title :::::"+driver.getTitle());
	 Assert.assertTrue("FaceBook Page Opened Successfully", driver.getTitle().contentEquals("Facebook – log in or sign up")); 
  }
  @When("^Enter my valid (.*) and (.*)$")
  public void credentials(String ID, String Pass) throws Throwable
  {	  
	  PwdEncryption pwd = new PwdEncryption(driver);
	  System.out.println("User entered USER ID and Password");
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(ID);
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pwd.passwordDecoder(Pass));
	  //throw new PendingException();
  }
  @Then("^Click on Login Button$")
  public void login() throws Throwable
  {
	  System.out.println("Clicking on Login Button....");
	  driver.findElement(By.xpath(".//*[@id='loginbutton']")).click();
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }
  @And("^HomePage is displayed$")
  public void homepage() throws Throwable
  {
	  System.out.println(driver.findElement(By.xpath("//*[@class='_4kny']//*[text()='Facebook']")).getText()+": Main Logo is Present");
	  System.out.println("Home Page is Displayed");
  }
  @But("^LogOff Should not be missing$")
  public void checkLogoff() throws Throwable
  {	
	  @SuppressWarnings("unused")
	boolean logoff=false;
	  try
	  {
	  driver.findElement(By.xpath(".//*[@id='BLUE_BAR_ID_DO_NOT_USE']/div/div/div[1]/div/div/ul/li[16]/a/span/span")).isEnabled();
	  logoff= true;
	  }
	  catch(Exception e)
	  {
		e.getMessage(); 
	  }
	  finally
	  {
		  System.out.println(" \nlogoff Option Exist");
	  }
  }
  @Then("^I click on LogOff$")
  public void signout() throws Throwable
  {
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='userNavigationLabel']")).click();
	  WebElement LogOut = driver.findElement(By.xpath("//*[@class='_54nf']//*[text()='Log out']"));
	  System.out.println(LogOut.getText()+ ": Text is displaying.");
	  System.out.println("Clicking on Logout Button ");
	  LogOut.click();
	  
  }
  @When("^Enter my Invalid \"([^\"]*)\" and \"([^\"]*)\"$")
  public void invalidCred(String id, String pass) throws Throwable
  {
	  System.out.println("User entered Invalid USER ID and Password");
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(id);
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pass);
  }
  @And("^Close the Browser after LogOff$")
  public void closeFB() throws Throwable
  {
	  System.out.println("Browser Closing...");
  }
  @Then("^Recover your account option will Display$")
  public void recoverAcct() throws Throwable
  {
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  WebElement toolTip = driver.findElement(By.xpath("//*[@class='_li']//*[contains(text(),'is incorrect')]"));
	  if (driver.findElement(By.xpath(".//*[@id='loginform']//*[text()='Recover Your Account']")).isDisplayed())
		{ 
		  System.out.println(driver.findElement(By.xpath(".//*[@id='loginform']//*[text()='Recover Your Account']")).getText()+": Invalid User Tried Login");
		  System.out.println("ToolTip :"+toolTip.getText());
		}
		else
		{
			System.out.print("Valid User ID");
		}
  }
  @Then("^Close the Browser$")
  public void closeBrowser() throws Throwable
  {
	  System.out.println("Browser Closing...");
  }
}
