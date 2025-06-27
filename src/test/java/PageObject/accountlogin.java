package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountlogin extends Basepage {

	public accountlogin(WebDriver driver) {
		super(driver);
		
	}

	@FindBy (xpath = "//input[@id='input-email']")
	WebElement  inputemail;
	@FindBy (xpath ="//input[@id='input-password']" )
	WebElement  inputpassword;
	@FindBy (xpath = "//input[@value='Login']")
	WebElement  loginbtn;


	public void enteremail( String mail)
	{
		inputemail.sendKeys(mail);
	}
	
	public void enterpassword( String PW2)
	{
		inputpassword.sendKeys(PW2);
	}
	
	public void clicklogin()
	{
		loginbtn.click();
	}
	
	
	
	
	
	
}
