package com.N11;

import javax.xml.xpath.XPath;

import org.testng.annotations.Test;

@Test()
public class TestN11 extends TestBase {

	Xpaths xpath = new Xpaths();
	N11page np = new N11page();
	
	public void N11ProductSearch () {
		
		if (np.uyeGirisi()) {
			
			if(np.searchProduct()) {
				
				np.processTheProduct();
			}
		} 
	}

}