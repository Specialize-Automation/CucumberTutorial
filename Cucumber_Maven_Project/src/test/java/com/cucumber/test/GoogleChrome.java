package com.cucumber.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleChrome 
{
	
	WebDriver driver;
	@Given("^Chrome only validation$")
	public void chrome_only_validation() throws Throwable 
	{
		System.out.println("Chrome Browser Test Environment created");
		System.out.println("__________________________________________________");	
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^Amazon Home page display$")
	public void amazon_Home_page_display() throws Throwable 
	{
		driver.navigate().to("http://www.amazon.in");
		Thread.sleep(2000);
		System.out.println("Navigated to :"+driver.getTitle());
	}

	@Then("^I click on Signin option$")
	public void i_click_on_Signin_option() throws Throwable 
	{
		driver.findElement(By.xpath("//*[contains(text(),'Hello. Sign in')]")).click();
		Thread.sleep(2000);
	}

	@And("^Amazon sign in page display$")
	public void amazon_sign_in_page_display() throws Throwable 
	{
		System.out.println("Navigated to :"+driver.getTitle());
	}

	@Then("^I validate the sign in page$")
	public void i_validate_the_sign_in_page() throws Throwable 
	{
		WebElement ele1 = driver.findElement(By.xpath("//*[contains(text(),'Email or mobile phone number')]"));
		WebElement ele2 = driver.findElement(By.xpath("//*[contains(text(),'Password')]"));
		
		System.out.println(ele1.getText()+" is Displayed");
		System.out.println(ele2.getText()+" is Displayed");
	}

	@Then("^Close the Amazon site$")
	public void close_the_Amazon_site() throws Throwable 
	{
		System.out.println("Test Environment Destroyed ");
		System.out.println("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}

}
