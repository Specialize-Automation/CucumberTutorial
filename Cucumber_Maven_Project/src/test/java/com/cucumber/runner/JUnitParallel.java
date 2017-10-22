package com.cucumber.runner;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import com.cucumber.test.GoogleChrome;
import com.cucumber.test.InternetExplorer;
import com.cucumber.test.MozilaFirefox;

public class JUnitParallel 
{
	@SuppressWarnings("rawtypes")
	@Test
	public void runParallelTests()
	{
		Class[] testclass = {GoogleChrome.class,InternetExplorer.class,MozilaFirefox.class};
		JUnitCore.runClasses(ParallelComputer.classes(), testclass);
		
		//JUnitCore.runClasses(new ParallelComputer(true, true), testclass);  
	}

}


/**
run only class parallel manner
JUnitCore.runClasses(ParallelComputer.classes(), cls);  

run methods in class parallel manner 
JUnitCore.runClasses(ParallelComputer.methods(), cls);  

run all methods in all class parallel way 
JUnitCore.runClasses(new ParallelComputer(true, true), cls);
**/