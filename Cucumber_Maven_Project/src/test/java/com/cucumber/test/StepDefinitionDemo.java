package com.cucumber.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepDefinitionDemo 
{
	WebDriver driver;
	Scenario scenario;
	
	@Before({"@User,@Admin"}) 
	public void testSetUp(Scenario scenario)throws NullPointerException
	{
		this.scenario = scenario;
		System.out.println("Test Environment set up");
		System.out.println("__________________________________________________");
		System.out.println("Executing Scenario :"+scenario.getName());
		
		System.out.println("Chrome Browser Test Environment created");
		System.setProperty("webdriver.chrome.driver", "D:/Browser Driver/ChromeDriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--start-maximized");
	    options.addArguments("--disable-web-security");
	    options.addArguments("--no-proxy-server");
	    
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.default_content_setting_values.notifications", 2);
	    options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.navigate().to("http://newtours.demoaut.com/mercurywelcome.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}	
	
	@After({"@User,@Admin"})
	public void tearDown(Scenario scenario)throws NullPointerException
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
	
	@Given("^I've a valid set of data and access$")
	public void ValidUserCheck() throws Throwable 
	{	
		System.out.println("User Having Valid set of Data");
	}

	@When("^Registration page Display$")
	public void registrationDisplay() throws Throwable 
	{
		WebDriverWait wait = new WebDriverWait(driver,10);	 	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER"))); 

	    WebElement register = driver.findElement(By.linkText("REGISTER"));	   
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('target','_self');",register);
	    register.click();  
	}

	@Then("^Enter Valid Data$")
	public void enterValidData(DataTable userdetails) throws Throwable 
	{
		List<List<String>> data = userdetails.raw();
		for(int i=0;i<data.size();i++)
		{
			System.out.println(data.get(i).toString());
		}
		
		driver.findElement(By.xpath("//input[@id='email'][@name='email']")).sendKeys(data.get(0).get(1).toString());
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(data.get(1).get(1).toString());
	    driver.findElement(By.xpath("//input[starts-with(@name,'confirmP')]")).sendKeys(data.get(2).get(1).toString());	
	}

	@Then("^Click on Submit$")
	public void click_on_Submit() throws Throwable 
	{
		driver.findElement(By.xpath("//input[contains(@src,'submit.gif')]")).click();
	}

	@Then("^Click Signoff$")
	public void click_Signoff() throws Throwable 
	{
	    System.out.println("\tClicking on ::"+driver.findElement(By.xpath("//*[contains(text(),'SIGN-OFF')]")).getText());
		driver.findElement(By.xpath("//*[contains(text(),'SIGN-OFF')]")).click();
	}

	@And("^Close$")
	public void close() throws Throwable 
	{
		System.out.println("Closing the browser");
	}
}
