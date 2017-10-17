package com.cucumber.pages;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MMTpage 
{
	WebDriver driver;
	public MMTpage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public By MMTLogo = By.className("mmt_header_logo");
	public By MMTLogin = By.cssSelector("#ch_login_icon.ch__login.cho_login");
    public By FBiframe = By.cssSelector("span>iframe[title*=Facebook][src*='https://www.facebook.com']");
	public By MMTLoginFB = By.cssSelector("body>div>div>div>div>div>div>table>tbody>tr>td>div>div[id=u_0_2]");
	public By MMTLoginGoogle = By.id("ch_login_google");
	public By Email = By.id("email");
	public By Password = By.id("pass");
	public By Login = By.id("u_0_2");
	public By loginText = By.cssSelector("p.ch__socilaSignup");
	public By loggedUser = By.cssSelector("span.ch__marL5");
	public By menu = By.cssSelector("span.ch__marL5.o-i-down_arrow");
	public By logout = By.cssSelector("a#ch_logged-inlogout");

// footer eliment for which long ans will be use as a doctring
	
	public By footer_1 = By.cssSelector("section.cfoot__container>h5:nth-child(1)");
	public By ans_1 = By.cssSelector("section.cfoot__container>p:nth-child(2)");
	public By footer_2 = By.cssSelector("section.cfoot__container>h5:nth-child(3)");
	public By ans_2 = By.cssSelector("section.cfoot__container>p:nth-child(4)");
	public By footer_3 = By.cssSelector("section.cfoot__container>h5:nth-child(5)");
	public By ans_3 = By.cssSelector("section.cfoot__container>p:nth-child(6)");
	
	public WebElement footer_1()
	{
		WebElement a = driver.findElement(footer_1);
		return a;	
	}
	public WebElement footer_2()
	{
		WebElement a = driver.findElement(footer_2);
		return a;	
	}
	public WebElement footer_3()
	{
		WebElement a = driver.findElement(footer_3);
		return a;	
	}
	public WebElement ans_1()
	{
		WebElement a = driver.findElement(ans_1);
		return a;	
	}
	public WebElement ans_2()
	{
		WebElement a = driver.findElement(ans_2);
		return a;	
	}
	public WebElement ans_3()
	{
		WebElement a = driver.findElement(ans_3);
		return a;	
	}
	
	public void switchToFBFrame()
	{
		List<WebElement> frameList = driver.findElements(FBiframe);
//there are two iframes ( one hidden )available
			driver.switchTo().frame(frameList.get(1));
	         try
	          {
	        	 if(MMTLoginFB().isDisplayed())
	        	 {System.out.println(MMTLoginFB().getText()+" :Displayed");}
	          }
	         catch(NoSuchElementException e)
	         {
	        	 System.out.println("Not found");
	        	 e.printStackTrace();
	         }
	}

	public WebElement MMTlogo()
	{
		WebElement a = driver.findElement(MMTLogo);
		return a;	
	}
	public WebElement MMTLogin()
	{
		WebElement b = driver.findElement(MMTLogin);
		return b;
	}
	public WebElement MMTLoginFB()
	{
		WebElement d = driver.findElement(MMTLoginFB);
		return d;
	}
	public WebElement MMTLoginGoogle()
	{
		WebElement e = driver.findElement(MMTLoginGoogle);
		return e;
	}
	public void enterEmail(String email)
	{
		driver.findElement(Email).clear();
		driver.findElement(Email).sendKeys(email);
	}
	public void enterPass(String pass)
	{
		driver.findElement(Password).clear();
		driver.findElement(Password).sendKeys(pass);
	}

		
		
		
		
		
		
	
}
