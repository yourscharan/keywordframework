package com.qa.hs.test;

import org.testng.annotations.Test;

import com.qa.hs.engine.keywordengine2;

public class loginTest2 {
	
	public keywordengine2 kw;
	 
	@Test
	public void login(){
		kw=new keywordengine2();
		kw.startexecution("login2");
		
	}
	
	
	@Test(priority=1)
	public void signup(){
		kw = new keywordengine2();
        kw.startexecution("signup");
	}

}
