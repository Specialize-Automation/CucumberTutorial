package com.cucumber.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MozilaFirefox 
{	
	WebDriver driver;
	
	@Given("^Firefox browser only validation$")
	public void testEnvSetup() throws Throwable 
	{
		  driver = new FirefoxDriver();
		  System.out.println("Mozila test Environment Set up ");
		  System.out.println("----------------------------------------------------------------------");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	}

	@When("^facebook home page display$")
	public void facebook_home_page_display() throws Throwable 
	{
		driver.navigate().to("https://www.facebook.com");
		Thread.sleep(2000);
		System.out.println("Navigated to :"+driver.getTitle());
	}

	@Then("^I click on forgot account link$")
	public void i_click_on_forgot_password_link() throws Throwable 
	{
		driver.findElement(By.xpath("//*[contains(text(),'Forgotten')]")).click();
		Thread.sleep(2000);
		System.out.println("Navigated to:"+driver.getTitle());
		
	}
	
	@And("^Forgot password page should be displayed$")
	public void forgot_password_page_should_be_displayed() throws Throwable 
	{
		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'Find Your Account')]"));
		System.out.println(ele.getText()+" is Displayed");
	}

	@Then("^I close my browser$")
	public void tearDown() throws Throwable 
	{
		System.out.println("Test Environment Destroyed ");
		System.out.println("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}
}
