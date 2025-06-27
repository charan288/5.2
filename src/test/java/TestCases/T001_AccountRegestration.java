package TestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Accountregistartion;
import PageObject.Homepage;
import Testbase.Baseclass;

public class T001_AccountRegestration extends Baseclass {

		@Test(groups = {"Regression","Master"} )
	public	void verify_accounregistration  ()
	{
		 try {
		
			logger.info(" **** starting T001_AccountRegestration");
			
				
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		logger.info("***** clicked on my account ***");
		hp.clickRegister();
		logger.info("***** clicked on register ***");
		Accountregistartion regpage = new Accountregistartion(driver);
		logger.info("***** provide customer details  ***");
		regpage.setfirstname(randomstring ().toUpperCase());
		regpage.setlastname(randomstring ().toLowerCase());
		regpage.setemail(randomstring () +"@gmail.com");
		regpage.settelephone(randomnumber ());
		
		String password = randomalphanumeri();
		regpage.setpassword(password);
		regpage.setconfirm(password);
		regpage.setradiobtn();
		regpage.setagree();
		regpage.clickcontinue();
		logger.info("***** validating expected msg  ***");
	String cnfmsg =	regpage.getconfirmmsg();
	if(cnfmsg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		logger.error(" test is failed ");
		logger.debug(" debug logs");
		Assert.assertTrue(false);
	}
	
	
	//Assert.assertEquals(cnfmsg , "Your Account Has Been Created!");
			}
	
	
catch (Exception e)
{

Assert.fail();
}
		 logger.info("**** Test Completed Successfully ****");

}
}