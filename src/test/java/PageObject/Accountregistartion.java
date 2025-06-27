package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Accountregistartion extends Basepage  {
 WebDriver driver;
 
	public Accountregistartion (WebDriver driver)
	{
		super(driver);
	}
	

@FindBy(xpath = "//input[@id='input-lastname']")
WebElement txtlastname;
@FindBy(xpath ="//input[@id='input-email']")
WebElement txtemail;
@FindBy(xpath ="//input[@id='input-telephone']")
WebElement txttelephone;
@FindBy(xpath ="//input[@id='input-firstname']")
WebElement txtfirstname;
@FindBy(xpath ="//input[@id='input-password']")
WebElement txtpassword;
@FindBy(xpath ="//input[@id='input-confirm']")
WebElement txtconfirm;
@FindBy(xpath ="//input[@value='0']")
WebElement txtradiobtn;
@FindBy(xpath = "//input[@name='agree']")
WebElement txtagree;
@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']" )
WebElement txtAccreated;	
@FindBy(xpath = "//input[@value='Continue']" )
WebElement txtcontinue;	



public void setfirstname(String fname)
{
	txtfirstname.sendKeys(fname);
	
}
public void setlastname(String lname)
{
	txtlastname.sendKeys(lname);
	
}
public void setemail(String email)
{
	txtemail.sendKeys(email);
	
}

public void settelephone(String number)
{
	txttelephone.sendKeys(number);
	
}

public void setpassword(String pW)
{
	txtpassword.sendKeys(pW);
	
}

public void setconfirm(String pW)
{
	txtconfirm.sendKeys(pW);
	
}

public void setradiobtn()
{
	txtradiobtn.click();;
	
}

public void setagree()
{
	txtagree.click();;
	
}


	
public void clickcontinue()
{
	txtcontinue.click();;
	
}

public String getconfirmmsg()
{
	      try 
	      {
	    	  return (txtAccreated.getText() );
	      }
	      catch( Exception e )
	      {
	    	  return (e.getMessage());
	      }
	      
	      
	      
}




}
