package com.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions( strict = true
				 ,features = {"src/test/resource/Demo.feature"}
				 ,plugin = {"pretty:STDOUT","html:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-pretty",
						    "json:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-json\\cucumber.json",
						    "com.cucumber.listener.ExtentCucumberFormatter:E:\\Eclipse_selenium\\Cucumber_Maven_Project\\Reports\\cucumber-extent\\report.html"
						   }
				 ,tags = {"@Admin,@User"}
				 ,monochrome = true
			     ,glue = {"com.cucumber.test"}
				 ,dryRun = false
				)

public class TestRunnerDemo extends AbstractTestNGCucumberTests{}

//Strict : it will try to find the pending steps or missing steps, and fail if not find
//feature :need to provide path of feature
//plugin : will contains the reporting features
//tag : for tagged hookes for scenarios
//monochrome : enhance the readbility
//glue : provide your stepdefinition class path
// dryrun : will check if u have pending or missing steps, but will not fail scripts