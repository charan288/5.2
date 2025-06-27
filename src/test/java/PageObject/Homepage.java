package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage  extends Basepage {

	
	 public Homepage(WebDriver driver) {
		super(driver);
		
	}

@FindBy( xpath = "//span[normalize-space()='My Account']")	
	WebElement linkmyaccount;

@FindBy( xpath = "//a[normalize-space()='Register']")	
WebElement linkregister;
@FindBy(xpath = "//a[normalize-space()='Login']" )
WebElement linklogin;
	
 public void clickMyAccount()
 {
	 
	 linkmyaccount.click();
 }
	
	
 public void clickRegister()
 {
	 
	 linkregister.click();
 }
	
 public void clickLogin()
 {
	 
	 linklogin.click();
 }	
	
	
	
	
}
