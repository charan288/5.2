package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Homepage;
import PageObject.Myaccountpage;
import PageObject.accountlogin;
import Testbase.Baseclass;
import Utilities.dataprovider;

public class T003_LoginDDT extends Baseclass {
@Test(dataProvider = "logindata",dataProviderClass = dataprovider.class, groups ="datadriven" )//if in another class,mention class
	public void verify_loginDDT  (String email, String pwd, String exp )
	{
	logger.info(" *******T003_AccountLogin DDT started*****");
	
		//homepage
	try {
				Homepage hp = new Homepage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//loginpage
				accountlogin logi = new accountlogin(driver);
				logi.enteremail(email);
				logi.enterpassword(pwd);
				logi.clicklogin();
				//myaccountpage
				
				Myaccountpage myACCP =new Myaccountpage(driver);
				boolean targetpage = myACCP.myaccexist();

				if (exp.equalsIgnoreCase("valid")) {
				    if (targetpage == true) {
				        logger.info("✅ Login passed as expected.");
				        Assert.assertTrue(true);
				        myACCP.clicklogout();
				    } else {
				        logger.warn("❌ Login failed but was expected to pass.");
				        Assert.assertTrue(false);
				    }
				} else if (exp.equalsIgnoreCase("invalid")) {
				    if (targetpage == true) {
				        logger.warn("❌ Login passed but was expected to fail.");
				        myACCP.clicklogout();
				        Assert.assertTrue(false);
				    } else {
				        logger.info("✅ Login failed as expected.");
				        Assert.assertTrue(true);
				    }
				}

				
				
				
				/*data is valid - login success- testpass - logout
				 *                login fail- test fail 
				 *data is invalid - login failed - testpass - 
				 *                login succes - test fail - logout*/
		
		
				
	} catch ( Exception e)	
	{
		Assert.fail();
	}
				
				
				
				
				
				
		logger.info(" *******T003_AccountLogin DDT finished*****");		
				
	}
	
	
	
	
}
