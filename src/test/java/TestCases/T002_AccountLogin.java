package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Homepage;
import PageObject.Myaccountpage;
import PageObject.accountlogin;
import Testbase.Baseclass;

public class T002_AccountLogin  extends Baseclass {

	
	@Test(groups = {"sanity","Master"})
	public void verify_login ()
	{
		logger.info(" *******T002_AccountLogin started*****");
	try {	
		
		//homepage
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginpage
		accountlogin logi = new accountlogin(driver);
		logi.enteremail(p.getProperty("email"));
		logi.enterpassword(p.getProperty("password"));
		logi.clicklogin();
		//myaccountpage
		
		Myaccountpage myACCP =new Myaccountpage(driver);
		
boolean 	targetpage =	myACCP.myaccexist();
		Assert.assertEquals(targetpage, true, " login failed");
	}
	catch ( Exception e)
	{
		Assert.fail();
	}
		logger.info(" *******T002_AccountLogin finished*****");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
