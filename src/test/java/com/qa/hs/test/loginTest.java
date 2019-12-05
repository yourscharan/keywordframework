package com.qa.hs.test;

import org.testng.annotations.Test;

import com.qa.hs.engine.keywordengine;

public class loginTest {
	public keywordengine kw;
	 
	@Test
	public void login(){
		kw=new keywordengine();
		kw.startexecution("login");
		System.out.println("new changes");
		
	}
}
