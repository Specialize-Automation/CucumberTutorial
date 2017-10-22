package com.cucumber.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions( strict = true
				 ,features = {"src/test/resource/GoogleChrome.feature"}
				 ,plugin = {"pretty:STDOUT","html:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-pretty",
						    "json:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-json\\cucumber.json"}
				 ,tags = {"@chrome"}
				 ,monochrome = true
			     ,glue = {"com.cucumber.test"}
				//dryRun = true
				)

public class ChromeBrowserRunner {}