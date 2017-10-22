package com.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import java.io.File;


@RunWith(Cucumber.class)
@CucumberOptions( strict = false
				 ,features = {"src/test/resource/mercuryflight.feature"}
				 ,plugin = {"pretty:STDOUT","html:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-pretty",
						    "json:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-json\\cucumber.json",
						    "com.cucumber.listener.ExtentCucumberFormatter:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-extent\\report.html"
						   }
				 ,tags = {"@Registration3,~@Registration2,@Registration1"}
				 ,monochrome = true
			     ,glue = {"com.cucumber.test"}
				//dryRun = true
				)

public class TestRunnerFB {
	 @AfterClass
	    public static void reportSetup() 
	 	{
	        Reporter.loadXMLConfig(new File("src/test/resource/extent-config.xml"));
	        /*Properties p = System.getProperties();
	        p.list(System.out);*/
	        
	        Reporter.setSystemInfo("User Name",System.getProperty("user.name"));
	        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	        Reporter.setSystemInfo("64 Bit", "Windows 10");
	        Reporter.setSystemInfo("2.53.0", "Selenium");
	        Reporter.setSystemInfo("3.3.9", "Maven");
	        Reporter.setSystemInfo("1.8.0_66", "Java Version");
	        Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
	 	}
}

/*,plugin = {"pretty:STDOUT","html:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-pretty",
"json:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-json\\cucumber.json"}*/