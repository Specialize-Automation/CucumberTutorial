package com.cucumber.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( strict = true
				 ,features = {"src/test/resource/makemyTrip.feature"}
				 ,plugin = {"pretty:STDOUT","html:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-pretty",
						    "json:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-json\\cucumber.json",
						    "com.cucumber.listener.ExtentCucumberFormatter:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-extent\\report.html"
						   }
				 ,tags = {"@makemytrip-signin,@makemytrip-footerValidationAUTH,@makemytrip-footerValidation"}
				 ,monochrome = true
			     ,glue = {"com.cucumber.test"}
                 ,dryRun = false
				)
public class TestRunnerMMT
{
	 @AfterClass
	    public static void reportSetup() 
	 	{
	        Reporter.loadXMLConfig(new File("src/test/resource/extent-config.xml"));
	        /*Properties p = System.getProperties();
	        p.list(System.out);*/
	        
	        Reporter.setSystemInfo("User Name",System.getProperty("user.name"));
	        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	        Reporter.setSystemInfo("64 Bit", 	"Windows 10");
	        Reporter.setSystemInfo("2.53.0", "Selenium");
	        Reporter.setSystemInfo("3.3.9", "Maven");
	        Reporter.setSystemInfo("1.8.0_66", "Java Version");
	        Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
	 	}
}



//@makemytrip-signin,@makemytrip-footerValidationAUTH