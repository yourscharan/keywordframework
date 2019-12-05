package com.qa.hs.test;

import org.testng.annotations.Test;

import com.qa.hs.engine.keyworddrivenengine1;

public class loginTest1 {
	
	public keyworddrivenengine1 kw;
	 
	@Test
	public void login(){
		kw=new keyworddrivenengine1();
		kw.startexecution("login1");
		
	}

}
