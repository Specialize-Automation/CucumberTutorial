package com.cucumber.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InternetExplorer 
{
	WebDriver driver;
	
	@Given("^IE browser only validation$")
	public void testEnvSetup() throws Throwable 
	{

		System.out.println("IE Browser Test Environment created");
		System.setProperty("webdriver.ie.driver","D:/Browser Driver/IE Driver/IEDriverServer340X32bit.exe");
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(dc);
		System.out.println("Test Environment Set up");
		System.out.println("----------------------------------------------------------------------");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Navigated to facebook login$")
	public void navigated_to_facebook_login() throws Throwable 
	{
		driver.navigate().to("https://en-gb.facebook.com");
		Thread.sleep(2000);
		System.out.println("Navigated to :"+driver.getTitle());
	}

	@Then("^I click on create a page$")
	public void i_click_on_create_a_page() throws Throwable 
	{
		driver.findElement(By.cssSelector("#reg_pages_msg > a")).click();
		Thread.sleep(2000);
		System.out.println("Navigated to :"+driver.getTitle());
	}

	@And("^Create page should be displayed$")
	public void create_page_should_be_displayed() throws Throwable 
	{
		WebElement ele1 = driver.findElement(By.cssSelector("#contentArea > div._4e29 > div._4e2a > div:nth-child(1)"));
		WebElement ele2 = driver.findElement(By.cssSelector("#contentArea > div._4e29 > div.uiHeader.uiHeaderWithImage.uiHeaderPage > div > div:nth-child(2) > h2"));
		
		System.out.println(ele1.getText()+" is Displayed");
		System.out.println(ele2.getText()+" is Displayed");
	}

	@Then("^Validate and close$")
	public void validate_and_close() throws Throwable 
	{
		System.out.println("Test Environment Destroyed ");
		System.out.println("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}

}
